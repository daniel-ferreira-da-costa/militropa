package unitins.tp1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.tp1.dto.usuario.LoginDTO;
import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.model.Perfil;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.service.jwt.JwtService;
import unitins.tp1.service.usuario.UsuarioService;

@QuarkusTest
public class UsuarioResourceTest {
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
                                .when().get("/usuarios")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {

                UsuarioDTO usuario = new UsuarioDTO(
                                "daniel",
                                "senha123",
                                1);

                UsuarioResponseDTO usuarioResponse = usuarioService.insert(usuario);
                assertThat(usuarioResponse, notNullValue());
                assertThat(usuarioResponse.login(), is("daniel"));
                assertThat(usuarioResponse.senha(),
                                is("mvJiSec3uZLUqe825+2/3gsotQ5Ar+XalO7KWvyv/3KJaUVQQ3o7aiPHavKLuSxUitM/BVb3c4SQ00XCH61DcQ=="));
                assertThat(usuarioResponse.perfil(), is(Perfil.valueOf(1)));
        }

        @Test
        public void testUpdate() {

                UsuarioDTO usuario = new UsuarioDTO(
                                "danielOjaca",
                                "senha123",
                                2);
                Long id = usuarioService.insert(usuario).id();

                UsuarioDTO usuarioUpdate = new UsuarioDTO(
                                "danieljacarildo",
                                "senha123",
                                2);

                UsuarioResponseDTO usuarioAtualizado = usuarioService.update(usuarioUpdate, id);
                assertThat(usuarioAtualizado, notNullValue());

                UsuarioResponseDTO usuarioResponse = usuarioService.findById(id);
                assertThat(usuarioResponse.login(), is("danieljacarildo"));
                assertThat(usuarioResponse.senha(),
                                is("mvJiSec3uZLUqe825+2/3gsotQ5Ar+XalO7KWvyv/3KJaUVQQ3o7aiPHavKLuSxUitM/BVb3c4SQ00XCH61DcQ=="));
                assertThat(usuarioResponse.perfil(), is(Perfil.valueOf(2)));
        }

        @Test
        public void testDelete() {
                UsuarioDTO usuario = new UsuarioDTO(
                                "Jacomilao",
                                "senha123",
                                2);
                Long id = usuarioService.insert(usuario).id();

                usuarioService.delete(id);
                assertThrows(Exception.class, () -> usuarioService.findById(id));
        }

        @Test
        public void testFindByLogin() {
                LoginDTO loginDTO = new LoginDTO("funcionario_jacare", "senha_funcionario_jacare");
                String hashSenha = hashService.getHashSenha(loginDTO.senha());
                UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
                String token = jwtService.generateJwt(result);
                given()
                                .headers("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .pathParam("login", loginDTO.login())
                                .when().get("/usuarios/search/login/{login}")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testFindById() {
                UsuarioDTO usuario = new UsuarioDTO(
                                "flinstons",
                                "senha123",
                                2);
                Long id = usuarioService.insert(usuario).id();
                LoginDTO loginDTO = new LoginDTO("funcionario_jacare", "senha_funcionario_jacare");
                String hashSenha = hashService.getHashSenha(loginDTO.senha());
                UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
                String token = jwtService.generateJwt(result);
                given()
                                .headers("Authorization", "Bearer " + token)
                                .contentType(ContentType.JSON)
                                .pathParam("id", id)
                                .when().get("/usuarios/{id}")
                                .then()
                                .statusCode(200);
        }
}
