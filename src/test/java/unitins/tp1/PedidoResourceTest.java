package unitins.tp1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.*;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.arma.ArmaDTO;
import unitins.tp1.dto.itemPedido.ItemPedidoDTO;
import unitins.tp1.dto.pedido.PedidoDTO;
import unitins.tp1.repository.PedidoRepository;
import unitins.tp1.service.arma.ArmaService;
import unitins.tp1.service.cliente.ClienteService;
import unitins.tp1.model.Status;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PedidoResourceTest extends DefaultTest {

    @Inject ClienteService clienteService;
    @Inject ArmaService    armaService;
    @Inject PedidoRepository pedidoRepository;

    private Long clienteId;
    private Long armaId;
    private Long pedidoId;

    @BeforeEach
    public void setupPedido() {
        EnderecoDTO endereco = new EnderecoDTO(
            "Casa", "Rua A", "123", "apt 1",
            "Centro", "12345-678", "CidadeX", "EstadoY"
        );
        ClienteDTO clienteDto = new ClienteDTO(
            "Pedido Cliente",
            "111.111.111-99",
            "pedido@teste.com",
            "REG123",
            List.of("999999999"),
            List.of(endereco),
            "cliente_pedido",
            "senha_pedido"
        );
        clienteId = clienteService.insert(clienteDto).id();

        ArmaDTO armaDto = new ArmaDTO(
            "Fuzil", 5, 1500.0, "Fuzil militar",
            1, "MarcaX", "Oxidado",
            "7.62mm", "60cm", 30,
            "ARM456", "ModeloY", "RNA789"
        );
        armaDto.setNumeroSigma("SIG123");
        armaId = armaService.insert(armaDto).id();
    }

    @AfterEach
    @Transactional
    public void teardownPedido() {
        // remove pedidos (FK)
        pedidoRepository.find("cliente.id", clienteId)
                       .list()
                       .forEach(pedidoRepository::delete);
        // remove cliente e arma
        clienteService.delete(clienteId);
        armaService.delete(armaId);
    }

    @Test
    @Order(1)
    public void testMeusPedidosSemPedido() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenUser)
        .when()
            .get("/pedidos/meusPedidos")
        .then()
            .statusCode(404)
            .body(containsString("Você ainda não fez nenhum pedido"));
    }

    @Test
    @Order(2)
    public void testPedidoLifecycle() {
        // 1) Cria Pedido (User)
        List<ItemPedidoDTO> itens = List.of(new ItemPedidoDTO(1, armaId));
        PedidoDTO dto = new PedidoDTO(itens, 1, clienteId.intValue());
        pedidoId = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenUser)
            .body(dto)
        .when()
            .post("/pedidos")
        .then()
            .statusCode(201)
            .body("cliente.id", equalTo(clienteId.intValue()))
            .body("itens[0].quantidade", equalTo(1))
            .body("pagamento.id", equalTo(1))
            .body("status.id", equalTo(Status.PENDENTE.getId()))
            .extract().jsonPath().getLong("id");

        // 2) Lista Todos (Admin)
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenAdmin)
        .when()
            .get("/pedidos")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));

        // 3) Busca por ID (Admin)
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenAdmin)
        .when()
            .get("/pedidos/{id}", pedidoId)
        .then()
            .statusCode(200)
            .body("id", equalTo(pedidoId.intValue()));

        // 4) Busca por ID (User) => 403
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenUser)
        .when()
            .get("/pedidos/{id}", pedidoId)
        .then()
            .statusCode(403);

        // 5) Busca por cliente (User)
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenUser)
        .when()
            .get("/pedidos/search/cliente/{id}", clienteId)
        .then()
            .statusCode(200)
            .body("[0].cliente.id", equalTo(clienteId.intValue()));

        // 6) Meus Pedidos (User)
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenUser)
        .when()
            .get("/pedidos/meusPedidos")
        .then()
            .statusCode(200)
            .body("[0].id", equalTo(pedidoId.intValue()));

        // 7) Altera Status Pagamento (User)
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenUser)
        .when()
            .patch("/pedidos/alterarStatusPagamento/{idPedido}", pedidoId)
        .then()
            .statusCode(204);

        // 8) Confirma Status Pago (Admin)
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .auth().oauth2(tokenAdmin)
        .when()
            .get("/pedidos/{id}", pedidoId)
        .then()
            .statusCode(200)
            .body("status.id", equalTo(Status.PAGO.getId()));
    }
}