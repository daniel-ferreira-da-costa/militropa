package unitins.tp1;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import unitins.tp1.service.funcionario.FuncionarioService;

@QuarkusTest
public class FuncionarioResourceTest {
    @Inject
    FuncionarioService funcionarioService;

    @Test
    public void testGetAll() {
        given()
                .when().get("/funcionarios")
                .then()
                .statusCode(200);
    }
}
