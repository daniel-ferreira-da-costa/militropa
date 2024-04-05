package unitins.tp1;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.model.Perfil;
import unitins.tp1.service.cartao.CartaoService;
import unitins.tp1.service.usuario.UsuarioService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class UsuarioResourceTest {
    @Inject
    UsuarioService usuarioService;

    @Test
    public void testGetAll() {
        given()
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
        UsuarioDTO usuario = new UsuarioDTO(
                "Jacamencia",
                "senha123",
                2);

        usuarioService.insert(usuario);
        given()
                .pathParam("login", usuario.login())
                .when().get("/usuarios/search/login/{login}")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindById() {
        UsuarioDTO usuario = new UsuarioDTO(
                "Jacarivis",
                "senha123",
                2);
        Long id = usuarioService.insert(usuario).id();

        given()
                .pathParam("id", id)
                .when().get("/usuarios/{id}")
                .then()
                .statusCode(200);
    }
}
