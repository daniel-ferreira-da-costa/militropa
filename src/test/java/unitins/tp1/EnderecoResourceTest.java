package unitins.tp1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.service.endereco.EnderecoService;

@QuarkusTest
public class EnderecoResourceTest extends DefaultTest {

    @Inject
    EnderecoService enderecoService;

    @Test
    public void testFindAllEnderecos() {
        EnderecoDTO dto = new EnderecoDTO(
            "Casa A",
            "Rua A",
            "10",
            "",
            "Bairro A",
            "11111-111",
            "Cidade A",
            "UF"
        );
        enderecoService.insert(dto);

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
        .when()
            .get("/enderecos")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    public void testFindByIdEndereco() {
        EnderecoDTO dto = new EnderecoDTO(
            "Casa B",
            "Rua B",
            "20",
            "Bloco B",
            "Bairro B",
            "22222-222",
            "Cidade B",
            "UF"
        );
        Long id = enderecoService.insert(dto).id();

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .get("/enderecos/{id}")
        .then()
            .statusCode(200)
            .body("id",         is(id.intValue()))
            .body("nome",       is(dto.nome()))
            .body("logradouro", is(dto.logradouro()))
            .body("numero",     is(dto.numero()));
    }

@Test
public void testFindByNomeEndereco() {
    EnderecoDTO dto = new EnderecoDTO(
        "Chácara C",
        "Rua C",
        "30",
        "",
        "Bairro C",
        "33333-333",
        "Cidade C",
        "UF"
    );
    enderecoService.insert(dto);
    given()
        .header("Authorization", "Bearer " + tokenAdmin)
        .pathParam("nome", "C")
    .when()
        .get("/enderecos/search/nome/{nome}")
    .then()
        .statusCode(200)
        .body("size()",       greaterThanOrEqualTo(1))
        .body("nome",         hasItem(dto.nome()))
        .body("logradouro",   hasItem(dto.logradouro()));
}

    @Test
    public void testUpdateEnderecoAsAdmin() {
        EnderecoDTO base = new EnderecoDTO(
            "Apartamento D",
            "Rua D",
            "40",
            "Apto 1",
            "Bairro D",
            "44444-444",
            "Cidade D",
            "UF"
        );
        Long id = enderecoService.insert(base).id();

        EnderecoDTO upd = new EnderecoDTO(
            "Apartamento D2",
            "Rua D2",
            "42",
            "Apto 2",
            "Bairro D2",
            "44444-445",
            "Cidade D2",
            "UF"
        );

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .contentType(ContentType.JSON)
            .pathParam("id", id)
            .body(upd)
        .when()
            .put("/enderecos/{id}")
        .then()
            .statusCode(200)
            .body("nome",       is(upd.nome()))
            .body("logradouro", is(upd.logradouro()))
            .body("numero",     is(upd.numero()));
    }

    @Test
    public void testUpdateEnderecoAsUser() {
        EnderecoDTO base = new EnderecoDTO(
            "Loja E",
            "Rua E",
            "50",
            "",
            "Bairro E",
            "55555-555",
            "Cidade E",
            "UF"
        );
        Long id = enderecoService.insert(base).id();

        EnderecoDTO upd = new EnderecoDTO(
            "Loja E2",
            "Rua E2",
            "52",
            "",
            "Bairro E2",
            "55555-556",
            "Cidade E2",
            "UF"
        );

        given()
            .header("Authorization", "Bearer " + tokenUser)
            .contentType(ContentType.JSON)
            .pathParam("id", id)
            .body(upd)
        .when()
            .put("/enderecos/{id}")
        .then()
            .statusCode(200)
            .body("nome",       is(upd.nome()))
            .body("logradouro", is(upd.logradouro()));
    }

    @Test
    public void testDeleteEnderecoAsAdmin() {
        EnderecoDTO dto = new EnderecoDTO(
            "Galpão F",
            "Rua F",
            "60",
            "",
            "Bairro F",
            "66666-666",
            "Cidade F",
            "UF"
        );
        Long id = enderecoService.insert(dto).id();

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .delete("/enderecos/{id}")
        .then()
            .statusCode(204);

        // verifica se o endereço foi realmente removido
        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .get("/enderecos/{id}")
        .then()
            .statusCode(404);
    }
}