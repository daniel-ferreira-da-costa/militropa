package unitins.tp1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.endereco.EnderecoDTO;

@QuarkusTest
public class CadastroClienteResourceTest {

    @Test
    public void testInsert() {
        List<String> telefones = List.of("11111111", "22222222");
        List<EnderecoDTO> enderecos = List.of(
                new EnderecoDTO("Casa", "Rua A", "100", "Bloco 1",
                        "Bairro X", "12345-678", "Cidade Y", "Estado Z"),
                new EnderecoDTO("Trabalho", "Av B", "200", "Sala 10",
                        "Bairro W", "87654-321", "Cidade V", "Estado U"));
        ClienteDTO dto = new ClienteDTO(
                "Soelicy Dias",
                "123.456.789-10",
                "soelicy.dias@example.com",
                "REG-1234",
                telefones,
                enderecos,
                "soelicydias",
                "minhaSenha123");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/cadastro")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body(
                        "id", notNullValue(),
                        "nome", is("Soelicy Dias"),
                        "cpf", is("123.456.789-10"),
                        "email", is("soelicy.dias@example.com"),
                        "numeroRegistro_posse_porte", is("REG-1234"),
                        "listaTelefones", hasSize(2),
                        "listaTelefones", hasItems("11111111", "22222222"),
                        "enderecos", hasSize(2),
                        "usuario.login", is("soelicydias"),
                        "usuario.perfil.id", is(1),
                        "usuario.perfil.label", is("User"));
    }

    @Test
    public void testErroDeInsert() {
        Map<String, Object> clienteErro = new HashMap<>();
        clienteErro.put("nome", "");
        clienteErro.put("cpf", "12345678910");
        clienteErro.put("email", "jose.silva@");
        clienteErro.put("numeroRegistro_posse_porte", "");
        clienteErro.put("listaTelefones", null);
        clienteErro.put("listaEnderecos", null);
        clienteErro.put("login", null);
        clienteErro.put("senha", null);
        given()
                .contentType(ContentType.JSON)
                .body(clienteErro)
                .when()
                .post("/cadastro")
                .then()
                .statusCode(400)
                // valida que existe o array "errors" e tem 8 entradas
                .body("errors", notNullValue())
                .body("errors.size()", is(8))
                // verifica todos os fieldName
                .body("errors.fieldName", hasItems(
                        "numeroRegistro_posse_porte",
                        "email",
                        "listaTelefones",
                        "senha",
                        "listaEnderecos",
                        "cpf",
                        "login",
                        "nome"))
                .body("errors.message", hasItems(
                        "O campo 'Numero de Registro de Posse ou Porte' não pode estar em branco",
                        "Email inválido",
                        "O campo telefone não pode ficar em branco",
                        "O campo senha não pode ficar em branco",
                        "O campo enderecos não pode ficar em branco",
                        "CPF inválido",
                        "O campo login não pode ficar em branco",
                        "O campo nome não pode ficar em branco"));
    }
}