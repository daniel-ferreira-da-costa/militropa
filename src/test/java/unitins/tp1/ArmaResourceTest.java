package unitins.tp1;

import jakarta.inject.Inject;
import io.quarkus.test.junit.QuarkusTest;
import unitins.tp1.service.arma.ArmaService;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import unitins.tp1.dto.arma.ArmaDTO;

@QuarkusTest
public class ArmaResourceTest  extends DefaultTest {

    @Inject
    ArmaService    armaService;

    @Test
    public void testGetAll() {
        given()
            .header("Authorization", "Bearer " + tokenUser)
            .contentType(ContentType.JSON)
        .when()
            .get("/armas")
        .then()
            .statusCode(200);
    }

    @Test
    public void testInsert() {
        ArmaDTO arma = new ArmaDTO(
            "Glock", 20, 15000,
            "Glock 17, 9mm, cano curto, capacidade para 17 tiros",
            2, "Taurus", "madeira rustica",
            "9mm", "curto", 17,
            "40028922", "ajajaj999", "0000011111"
        );

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .contentType(ContentType.JSON)
            .body(arma)
        .when()
            .post("/armas")
        .then()
            .statusCode(201)
            .body(
                "id",                notNullValue(),
                "nome",              is("Glock"),
                "qtdNoEstoque",      is(20),
                "preco",             is(15000.0F),
                "descricao",         is("Glock 17, 9mm, cano curto, capacidade para 17 tiros"),
                "tipo.id",           is(2),
                "tipo.label",        is("Pistola"),
                "marca",             is("Taurus"),
                "acabamento",        is("madeira rustica"),
                "calibre",           is("9mm"),
                "comprimentoDoCano", is("curto"),
                "capacidadeDeTiro",  is(17),
                "numeroDaArma",      is("40028922"),
                "modelo",            is("ajajaj999"),
                "rna",               is("0000011111")
            );
    }

    @Test
    public void testUpdate() {
        ArmaDTO arma = new ArmaDTO(
            "3oiao", 100, 8000,
            "revolver calibre .38 especial, cano longo, capacidade para 5 tiros",
            1, "duvidosa", "de má qualidade",
            ".38", "cano longo", 5,
            "dadaad000", "999jjjj99", "gg777"
        );
        Long id = armaService.insert(arma).id();

        ArmaDTO armaUpdate = new ArmaDTO(
            "Smith & Wesson Modelo 686", 100, 8500,
            "Revólver calibre .38 especial, cano longo, capacidade para 5 tiros",
            1, "Smith & Wesson", "Aço Inoxidável",
            ".38 Special", "4 polegadas", 5,
            "SW123456", "123456", "M686"
        );

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .contentType(ContentType.JSON)
            .pathParam("id", id)
            .body(armaUpdate)
        .when()
            .put("/armas/{id}")
        .then()
            .statusCode(200)
            .body("nome", is("Smith & Wesson Modelo 686"));
    }

    @Test
    public void testFindById() {
        ArmaDTO arma = new ArmaDTO(
            "3oiao", 100, 8000,
            "revolver calibre .38 especial, cano longo, capacidade para 5 tiros",
            1, "duvidosa", "de má qualidade",
            ".38", "cano longo", 5,
            "dadaad000", "999jjjj99", "gg777"
        );
        Long id = armaService.insert(arma).id();

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .get("/armas/{id}")
        .then()
            .statusCode(200)
            .body("id", is(id.intValue()));
    }

    @Test
    public void testFindByNome() {
        ArmaDTO arma = new ArmaDTO(
            "3oiao", 100, 8000,
            "revolver calibre .38 especial, cano longo, capacidade para 5 tiros",
            1, "duvidosa", "de má qualidade",
            ".38", "cano longo", 5,
            "dadaad000", "999jjjj99", "gg777"
        );
        armaService.insert(arma);

        given()
            .header("Authorization", "Bearer " + tokenUser)
            .contentType(ContentType.JSON)
            .pathParam("nome", arma.getNome())
        .when()
            .get("/armas/search/nome/{nome}")
        .then()
            .statusCode(200);
    }

    @Test
    public void testDelete() {
        ArmaDTO arma = new ArmaDTO(
            "3oiao", 100, 8000,
            "revolver calibre .38 especial, cano longo, capacidade para 5 tiros",
            1, "duvidosa", "de má qualidade",
            ".38", "cano longo", 5,
            "dadaad000", "999jjjj99", "gg777"
        );
        Long id = armaService.insert(arma).id();

        given()
            .header("Authorization", "Bearer " + tokenAdmin)
            .pathParam("id", id)
        .when()
            .delete("/armas/{id}")
        .then()
            .statusCode(204);
    }
}