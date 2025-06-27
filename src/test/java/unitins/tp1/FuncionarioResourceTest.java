package unitins.tp1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import unitins.tp1.dto.funcionario.FuncionarioDTO;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.service.funcionario.FuncionarioService;

@QuarkusTest
public class FuncionarioResourceTest extends DefaultTest {

    @Inject
    FuncionarioService funcionarioService;

    @Test
    public void testInsertFuncionario() {
        FuncionarioDTO dto = new FuncionarioDTO(
            "Pedro Santos",
            "123.123.123-12",
            "pedro@example.com",
            List.of("31999990000"),
            List.of(new EnderecoDTO(
                "Casa",
                "Rua A",
                "10",
                "",
                "11111-111",
                "Bairro A",
                "Cidade A",
                "UF"
            )),
            "MAT-1001",
            "senhaFunc"
        );

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .post("/funcionarios")
        .then()
            .statusCode(201)
            .body("nome",      is(dto.nome()))
            .body("cpf",       is(dto.cpf()))
            .body("email",     is(dto.email()))
            .body("matricula", is(dto.matricula()))
            .body("listaTelefones[0]",       is(dto.listaTelefones().get(0)))
            .body("enderecos[0].logradouro", is(dto.listaEnderecos().get(0).logradouro()));
    }

    @Test
    public void testFindAllFuncionarios() {
        FuncionarioDTO dto = new FuncionarioDTO(
            "Ana Costa",
            "222.333.444-55",
            "ana@example.com",
            List.of("31988887777"),
            List.of(new EnderecoDTO(
                "Casa",
                "Rua B",
                "20",
                "",
                "22222-222",
                "Bairro B",
                "Cidade B",
                "UF"
            )),
            "MAT-1002",
            "senha123"
        );
        funcionarioService.insert(dto);

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
        .when()
            .get("/funcionarios")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    public void testFindByIdFuncionario() {
        FuncionarioDTO dto = new FuncionarioDTO(
            "Lucas Lima",
            "333.444.555-66",
            "lucas@example.com",
            List.of("31977776666"),
            List.of(new EnderecoDTO(
                "Apartamento",
                "Av C",
                "30",
                "Bloco C",
                "33333-333",
                "Bairro C",
                "Cidade C",
                "UF"
            )),
            "MAT-1003",
            "senha321"
        );
        Long id = funcionarioService.insert(dto).id();

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .get("/funcionarios/{id}")
        .then()
            .statusCode(200)
            .body("id",                is(id.intValue()))
            .body("nome",              is(dto.nome()))
            .body("matricula",         is(dto.matricula()))
            .body("listaTelefones[0]", is(dto.listaTelefones().get(0)));
    }

    @Test
    public void testFindByNomeFuncionario() {
        FuncionarioDTO dto = new FuncionarioDTO(
            "Mariana Rocha",
            "444.555.666-77",
            "mariana@example.com",
            List.of("31966665555"),
            List.of(new EnderecoDTO(
                "Cobertura",
                "Rua D",
                "40",
                "",
                "44444-444",
                "Bairro D",
                "Cidade D",
                "UF"
            )),
            "MAT-1004",
            "senhaabc"
        );
        funcionarioService.insert(dto);

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("nome", "Mariana")
        .when()
            .get("/funcionarios/search/nome/{nome}")
        .then()
            .statusCode(200)
            .body("size()",                greaterThanOrEqualTo(1))
            .body("[0].nome",              containsString("Mariana"))
            .body("[0].listaTelefones",    hasSize(1));
    }

    @Test
    public void testFindByMatriculaFuncionario() {
        FuncionarioDTO dto = new FuncionarioDTO(
            "Rafael Dias",
            "555.666.777-88",
            "rafael@example.com",
            List.of("31955554444"),
            List.of(new EnderecoDTO(
                "Loja",
                "Rua E",
                "50",
                "",
                "55555-555",
                "Bairro E",
                "Cidade E",
                "UF"
            )),
            "MAT-1005",
            "senha456"
        );
        funcionarioService.insert(dto);

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("matricula", dto.matricula())
        .when()
            .get("/funcionarios/search/matricula/{matricula}")
        .then()
            .statusCode(200)
            .body("matricula", is(dto.matricula()))
            .body("usuario",   notNullValue());
    }

    @Test
    public void testUpdateFuncionario() {
        FuncionarioDTO base = new FuncionarioDTO(
            "Bruna Almeida",
            "666.777.888-99",
            "bruna@example.com",
            List.of("31944443333"),
            List.of(new EnderecoDTO(
                "Apartamento",
                "Rua F",
                "60",
                "Apto 2",
                "66666-666",
                "Bairro F",
                "Cidade F",
                "UF"
            )),
            "MAT-1006",
            "senha789"
        );
        Long id = funcionarioService.insert(base).id();

        FuncionarioDTO upd = new FuncionarioDTO(
            "Bruna A. Atualizada",
            base.cpf(),
            "bruna.new@example.com",
            base.listaTelefones(),
            base.listaEnderecos(),
            base.matricula(),
            "novaSenha789"
        );

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .contentType(ContentType.JSON)
            .pathParam("id", id)
            .body(upd)
        .when()
            .put("/funcionarios/{id}")
        .then()
            .statusCode(204);

        // verifica atualização
        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .get("/funcionarios/{id}")
        .then()
            .statusCode(200)
            .body("nome",  is(upd.nome()))
            .body("email", is(upd.email()));
    }

    @Test
    public void testDeleteFuncionario() {
        FuncionarioDTO dto = new FuncionarioDTO(
            "Gabriel Souza",
            "777.888.999-00",
            "gabriel@example.com",
            List.of("31933332222"),
            List.of(new EnderecoDTO(
                "Palácio",
                "Rua G",
                "70",
                "",
                "77777-777",
                "Bairro G",
                "Cidade G",
                "UF"
            )),
            "MAT-1007",
            "senha000"
        );
        Long id = funcionarioService.insert(dto).id();

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .delete("/funcionarios/{id}")
        .then()
            .statusCode(204);

        // Confirma remoção
        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .get("/funcionarios/{id}")
        .then()
            .statusCode(404);
    }
}