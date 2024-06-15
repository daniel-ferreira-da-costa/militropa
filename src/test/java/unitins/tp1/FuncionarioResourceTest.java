package unitins.tp1;

import static io.restassured.RestAssured.given;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.tp1.dto.usuario.LoginDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.service.funcionario.FuncionarioService;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.service.jwt.JwtService;
import unitins.tp1.service.usuario.UsuarioService;

@QuarkusTest
public class FuncionarioResourceTest {
    @Inject
    FuncionarioService funcionarioService;

    @Inject
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken jwt;

    @Test
    public void testGetAll() {
        LoginDTO loginDTO = new LoginDTO("funcionario_jacare", "senha_funcionario_jacare");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String token = jwtService.generateJwt(result);
        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when().get("/funcionarios")
                .then()
                .statusCode(200);
    }
}
