package unitins.tp1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.usuario.LoginDTO;
import unitins.tp1.service.cliente.ClienteService;

@QuarkusTest
public class ClienteResourceTest extends DefaultTest {

    @Inject
    ClienteService clienteService;

    @Test
    public void testFindAll() {
        given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .contentType(ContentType.JSON)
                .when()
                .get("/clientes")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testFindById() {
        // Monta fixture
        List<String> telefones = List.of("11999998888");
        List<EnderecoDTO> enderecos = List.of(
                new EnderecoDTO(
                        "Residencial Principal", // nome
                        "Rua dos Testes", // logradouro
                        "100", // número
                        "Casa", // complemento
                        "12345-678", // cep
                        "Centro", // bairro
                        "Cidade X", // cidade
                        "UF" // estado
                ));
        ClienteDTO dto = new ClienteDTO(
                "Vitoria Moura",
                "123.456.789-01",
                "vitoria@example.com",
                "NRP-1234",
                telefones,
                enderecos,
                "vitoria_moura",
                "senha123");
        Long id = clienteService.insert(dto).id();

        // Chama o endpoint
        given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .pathParam("id", id)
                .when()
                .get("/clientes/{id}")
                .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nome", is("Vitoria Moura"))
                .body("listaTelefones", hasSize(1))
                .body("enderecos", hasSize(1))
                .body("enderecos[0].nome", is("Residencial Principal"))
                .body("enderecos[0].logradouro", is("Rua dos Testes"));
    }

    @Test
    public void testFindByNome() {
        // Insere cliente
        List<String> telefones = List.of("21988887777");
        List<EnderecoDTO> enderecos = List.of(
                new EnderecoDTO(
                        "Residencial Busca",
                        "Av. Exemplo",
                        "200",
                        "Apto 1",
                        "98765-432",
                        "Bairro Y",
                        "Cidade Y",
                        "UF"));
        ClienteDTO dto = new ClienteDTO(
                "Marineide Santana",
                "098.765.432-10",
                "marineide@example.com",
                "NRP-5678",
                telefones,
                enderecos,
                "marineide_santana",
                "senha456");
        clienteService.insert(dto);

        // Busca pelo nome (admin somente)
        given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .contentType(ContentType.JSON)
                .pathParam("nome", "Marineide")
                .when()
                .get("/clientes/search/nome/{nome}")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1))
                .body("[0].nome", containsString("Marineide"))
                .body("[0].listaTelefones", hasSize(1))
                .body("[0].enderecos", hasSize(1));
    }

    @Test
public void testUpdateAsAdmin() {
    ClienteDTO base = new ClienteDTO(
        "Gabriela Fernandes",
        "111.222.333-44",
        "gabriela@example.com",
        "NRP-0003",
        List.of("31977776666"),
        List.of(new EnderecoDTO(
            "Casa", "Rua Original", "300", "Bloco A",
            "55555-000", "Bairro Z", "Cidade Z", "UF"
        )),
        "gabriela_f",
        "senha789"
    );
    Long id = clienteService.insert(base).id();

    ClienteDTO upd = new ClienteDTO(
        "Gabriela F. Atualizada",
        base.cpf(),
        "gabriela.nova@example.com",
        base.numeroRegistro_posse_porte(),
        List.of("31900001111"),
        List.of(new EnderecoDTO(
            "Casa Atualizada", "Rua Atualizada", "400", "Apto 101",
            "66666-111", "Bairro Novo", "Cidade Novo", "UF"
        )),
        "gabriela_f_nova",
        "novaSenha789"
    );

    given()
      .header("Authorization","Bearer "+tokenAdmin)
      .contentType(ContentType.JSON)
      .pathParam("id", id)
      .body(upd)
    .when()
      .put("/clientes/{id}")
    .then()
      .statusCode(204);

    // verifica que a atualização realmente ocorreu
    given()
      .header("Authorization","Bearer "+tokenAdmin)
      .pathParam("id", id)
    .when()
      .get("/clientes/{id}")
    .then()
      .statusCode(200)
      .body("nome", is(upd.nome()))
      .body("email", is(upd.email()));
}

    @Test
    public void testUpdateSelfAsUser() {
        // 1) cria cliente via service
        ClienteDTO base = new ClienteDTO(
                "Usuario Teste",
                "222.333.444-55",
                "user@test.com",
                "NRP-0004",
                List.of("31955552222"),
                List.of(new EnderecoDTO(
                        "Casa",
                        "Rua Principal",
                        "1",
                        "",
                        "00000-000",
                        "Bairro X",
                        "Cidade X",
                        "UF")),
                "usuario_teste",
                "senhaTeste");
        Long id = clienteService.insert(base).id();

        // 2) autentica esse usuário contra /auth
        String userJwt = given()
                .contentType(ContentType.JSON)
                .body(new LoginDTO(base.login(), base.senha()))
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().header("Authorization");

        // 3) prepara DTO de self-update
        ClienteDTO upd = new ClienteDTO(
                "Usuario Teste X",
                base.cpf(),
                "userx@test.com",
                base.numeroRegistro_posse_porte(),
                List.of("31999990000"),
                List.of(new EnderecoDTO(
                        "Casa X",
                        "Rua X",
                        "20",
                        "Casa",
                        "22222-222",
                        "Bairro X",
                        "Cidade X",
                        "UF")),
                base.login(), // mantém o mesmo login
                "novaSenhaTeste");

        // 4) PUT /clientes/me como USER
        given()
                .header("Authorization", "Bearer " + userJwt)
                .contentType(ContentType.JSON)
                .body(upd)
                .when()
                .put("/clientes/me")
                .then()
                .statusCode(204);

        // 5) GET /clientes/{id} como ADMIN para conferir
        given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .pathParam("id", id)
                .when()
                .get("/clientes/{id}")
                .then()
                .statusCode(200)
                .body("nome", is(upd.nome()))
                .body("email", is(upd.email()));
    }

    @Test
    public void testDelete() {
        List<String> telefones = List.of("41966665555");
        List<EnderecoDTO> enderecos = List.of(
                new EnderecoDTO(
                        "Residencial Remover",
                        "Rua Para Excluir",
                        "500",
                        "Fundos",
                        "77777-222",
                        "Bairro X",
                        "Cidade X",
                        "UF"));
        ClienteDTO dto = new ClienteDTO(
                "Ana Lima",
                "555.666.777-88",
                "ana@example.com",
                "NRP-3456",
                telefones,
                enderecos,
                "ana_lima",
                "senha000");
        Long id = clienteService.insert(dto).id();

        given()
                .header("Authorization", "Bearer " + tokenAdmin)
                .pathParam("id", id)
                .when()
                .delete("/clientes/{id}")
                .then()
                .statusCode(204);
    }
}