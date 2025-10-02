package unitins.tp1;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import unitins.tp1.dto.usuario.*;
import unitins.tp1.service.usuario.UsuarioService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioResourceTest extends DefaultTest {

    @Inject
    protected UsuarioService usuarioService; // para regenerar JWT

    private Long adminCreatedId;
    private Long userSelfId;

    @Test
    @Order(1)
    public void testInsertUsuarioAsAdmin() {
        var dto = new UsuarioDTO("testAdminInsert", "senhaIns1", 1);
        adminCreatedId = given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .contentType("application/json")
                .body(dto)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("login", is("testAdminInsert"))
                .extract()
                .jsonPath().getLong("id"); // <<< getLong em vez de path
    }

    @Test
    @Order(2)
    public void testFindAllUsuarios() {
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }

    @Test
    @Order(3)
    public void testFindByIdUsuario() {
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .when()
                .get("/usuarios/{id}", adminCreatedId)
                .then()
                .statusCode(200)
                .body("id", is(adminCreatedId.intValue()))
                .body("login", is("testAdminInsert"));
    }

    @Test
    @Order(4)
    public void testFindByLoginUsuario() {
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .when()
                .get("/usuarios/search/login/{login}", "testAdminInsert")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1))
                .body("[0].login", is("testAdminInsert"));
    }

    @Test
    @Order(5)
    public void testUpdateUsuarioAsAdmin() {
        var updateDto = new UsuarioDTO("testAdminUpdated", "senhaUpd1", 1);
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .contentType("application/json")
                .body(updateDto)
                .when()
                .put("/usuarios/{id}", adminCreatedId)
                .then()
                .statusCode(204);

        // confirma via GET
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .when()
                .get("/usuarios/{id}", adminCreatedId)
                .then()
                .statusCode(200)
                .body("login", is("testAdminUpdated"));
    }

    @Test
    @Order(6)
    public void testUpdateUsuarioAsUser() {
        // busca o próprio ID
        userSelfId = given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenUser)
                .when()
                .get("/usuarios/my-user")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getLong("id"); // <<< getLong

        // atualiza login e senha (perfil 2 = User)
        var updateDto = new UsuarioDTO("userUpdated", senhaUser, 2);
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenUser)
                .contentType("application/json")
                .body(updateDto)
                .when()
                .put("/usuarios/{id}", userSelfId)
                .then()
                .statusCode(204);
        // Regenera o token para o novo login
        var updated = usuarioService.findByLogin("userUpdated");
        tokenUser = jwtService.generateJwt(updated);

        // confirma via /usuarios/{id} (como User não tem permissão, usa Admin)
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .when()
                .get("/usuarios/{id}", userSelfId)
                .then()
                .statusCode(200)
                .body("login", is("userUpdated"));
    }

    @Test
    @Order(7)
    public void testAlterarLoginUsuario() {
        var dto = new alterarLoginUsuarioDTO(senhaUser, "userLoginChanged");
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenUser)
                .contentType("application/json")
                .body(dto)
                .when()
                .patch("/usuarios/alterarlogin")
                .then()
                .statusCode(204);

        // Regenera o token para o novo login
        var updated = usuarioService.findByLogin("userLoginChanged");
        tokenUser = jwtService.generateJwt(updated);
    }

    @Test
    @Order(8)
    public void testAlterarSenhaUsuario() {
        var dto = new alterarSenhaUsuarioDTO(senhaUser, "newPassword1", "newPassword1");
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenUser)
                .contentType("application/json")
                .body(dto)
                .when()
                .patch("/usuarios/alterarsenha")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(9)
    public void testFindMyUser() {
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenUser)
                .when()
                .get("/usuarios/my-user")
                .then()
                .statusCode(200)
                .body("login", is("cliente_rafaela"))
                .body("perfil.id", is(1)) // perfil User
                .body("perfil.label", is("User"));
    }

    @Test
    @Order(10)
    public void testDeleteUsuarioAsAdmin() {
        // 1) Cria um usuário temporário para deletar
        UsuarioDTO criaDto = new UsuarioDTO("tempToDelete", "senhaTemp1", 1);
        Long tempId = given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .contentType("application/json")
                .body(criaDto)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().getLong("id");

        // 2) Executa DELETE nesse usuário recém-criado
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .when()
                .delete("/usuarios/{id}", tempId)
                .then()
                .statusCode(204);

        // 3) Confirma que o usuário não existe mais
        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenAdmin)
                .when()
                .get("/usuarios/{id}", tempId)
                .then()
                .statusCode(404);
    }
}