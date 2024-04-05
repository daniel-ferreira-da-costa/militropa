package unitins.tp1;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.dto.usuario.AuthUsuarioDTO;
import unitins.tp1.service.endereco.EnderecoService;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class EnderecoResourceTest {

    @Inject
    EnderecoService enderecoService;

    @Test
    public void testGetAll() {
        given()
                .when().get("/enderecos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        EnderecoDTO endereco = new EnderecoDTO(
                "Minha Casa",
                "Quadra 404 Sul",
                "123",
                "Casa 1",
                "Plano Diretor Sul",
                "77021-550",
                "Palmas",
                "Tocantins");

        EnderecoResponseDTO enderecoResponse = enderecoService.insert(endereco);
        assertThat(enderecoResponse, notNullValue());
        assertThat(enderecoResponse.nome(), is("Minha Casa"));
        assertThat(enderecoResponse.logradouro(), is("Quadra 404 Sul"));
        assertThat(enderecoResponse.bairro(), is("Plano Diretor Sul"));
        assertThat(enderecoResponse.numero(), is("123"));
        assertThat(enderecoResponse.complemento(), is("Casa 1"));
        assertThat(enderecoResponse.cep(), is("77021-550"));
        assertThat(enderecoResponse.cidade(), is("Palmas"));
        assertThat(enderecoResponse.estado(), is("Tocantins"));
    }

    @Test
    public void testUpdate() {
        EnderecoDTO endereco = new EnderecoDTO(
                "Minha Casa",
                "Quadra 404 Sul",
                "123",
                "Casa 1",
                "Plano Diretor Sul",
                "77021-550",
                "Palmas",
                "Tocantins");
        Long id = enderecoService.insert(endereco).id();

        EnderecoDTO enderecoUpdate = new EnderecoDTO(
                "Nova Casa",
                "Quadra 603 Sul",
                "456",
                "Casa 2",
                "Plano Diretor Sul",
                "77022-660",
                "Palmas",
                "Tocantins");

        EnderecoResponseDTO enderecoAtualizado = enderecoService.update(enderecoUpdate, id);
        assertThat(enderecoAtualizado, notNullValue());

        EnderecoResponseDTO enderecoResponse = enderecoService.findById(id);
        assertThat(enderecoResponse.nome(), is("Nova Casa"));
        assertThat(enderecoResponse.logradouro(), is("Quadra 603 Sul"));
        assertThat(enderecoResponse.bairro(), is("Plano Diretor Sul"));
        assertThat(enderecoResponse.numero(), is("456"));
        assertThat(enderecoResponse.complemento(), is("Casa 2"));
        assertThat(enderecoResponse.cep(), is("77022-660"));
        assertThat(enderecoResponse.cidade(), is("Palmas"));
        assertThat(enderecoResponse.estado(), is("Tocantins"));
    }

    @Test
    public void testDelete() {
        EnderecoDTO endereco = new EnderecoDTO(
                "casa",
                "603 sul",
                "777",
                "alameda A",
                "QI 17 LT 30",
                "88888-864",
                "Palmas",
                "Tocantins");
        Long id = enderecoService.insert(endereco).id();

        enderecoService.delete(id);

        assertThrows(Exception.class, () -> enderecoService.findById(id));
    }

    @Test
    public void testFindById() {
        EnderecoDTO endereco = new EnderecoDTO(
            "casa", 
            "123 rua", 
            "123",
            "complemento1", 
            "bairro1", 
            "12345678", 
            "Palmas", 
            "Tocantins"
        );
        Long id = enderecoService.insert(endereco).id();
        
        given()
            .pathParam("id", id)
            .when().get("/enderecos/{id}")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindByNome() {
        EnderecoDTO endereco = new EnderecoDTO(
            "baixa da egua", 
            "123 rua", 
            "123",
            "complemento1", 
            "bairro1", 
            "12345678", 
            "Palmas", 
            "Tocantins"
        );
        enderecoService.insert(endereco);
        
        given()
            .pathParam("nome", endereco.nome())
            .when().get("/enderecos/search/nome/{nome}")
            .then()
            .statusCode(200);
    }
}