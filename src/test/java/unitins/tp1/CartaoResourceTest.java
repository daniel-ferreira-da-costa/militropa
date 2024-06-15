
package unitins.tp1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.tp1.dto.cartao.CartaoDTO;
import unitins.tp1.dto.cartao.CartaoResponseDTO;
import unitins.tp1.dto.usuario.LoginDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.model.BandeiraCartao;
import unitins.tp1.model.TipoCartao;
import unitins.tp1.service.cartao.CartaoService;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.service.jwt.JwtService;
import unitins.tp1.service.usuario.UsuarioService;

@QuarkusTest 
public class CartaoResourceTest {

    @Inject
    CartaoService cartaoService;

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
        LoginDTO loginDTO = new LoginDTO("cliente_andre", "senha_cliente_andre");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String token = jwtService.generateJwt(result);
        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when().get("/cartoes")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        LocalDate dataVencimento = LocalDate.of(2024, 9, 1);
        CartaoDTO cartao = new CartaoDTO(
                "Inter",
                "0000000000000000",
                dataVencimento,
                "080",
                "daniel jacare",
                3,
                2);
        
        CartaoResponseDTO cartaoResponse = cartaoService.insert(cartao);
        assertThat(cartaoResponse, notNullValue());
        assertThat(cartaoResponse.banco(), is("Inter"));
        assertThat(cartaoResponse.numero(), is("0000000000000000"));
        assertThat(cartaoResponse.dataVencimento(), is(dataVencimento));
        assertThat(cartaoResponse.codVerificacao(), is("080"));
        assertThat(cartaoResponse.nomeTitular(), is("daniel jacare"));
        assertThat(cartaoResponse.tipoCartao(), is(TipoCartao.valueOf(3)));
        assertThat(cartaoResponse.bandeiraCartao(), is(BandeiraCartao.valueOf(2)));
    }

    @Test
    public void testUpdate(){
        LocalDate dataVencimento = LocalDate.of(2024, 9, 1);
        CartaoDTO cartao = new CartaoDTO(
                "nubank",
                "0000000000000000",
                dataVencimento,
                "080",
                "danieljacare",
                3,
                2);
        Long id = cartaoService.insert(cartao).id();

        LocalDate dataVencimento2 = LocalDate.of(2030, 9, 1);
        CartaoDTO cartaoUpdate = new CartaoDTO(
                "Inter",
                "0000000000000000",
                dataVencimento2,
                "080",
                "Daniel Ferreira da Costa",
                3,
                2);

                CartaoResponseDTO cartaoAtualizado = cartaoService.update(cartaoUpdate, id);
                assertThat(cartaoAtualizado, notNullValue());

                CartaoResponseDTO cartaoResponse = cartaoService.findById(id);
                assertThat(cartaoResponse.banco(), is("Inter"));
                assertThat(cartaoResponse.numero(), is("0000000000000000"));
                assertThat(cartaoResponse.dataVencimento(), is(dataVencimento2));
                assertThat(cartaoResponse.codVerificacao(), is("080"));
                assertThat(cartaoResponse.nomeTitular(), is("Daniel Ferreira da Costa"));
                assertThat(cartaoResponse.tipoCartao(), is(TipoCartao.valueOf(3)));
                assertThat(cartaoResponse.bandeiraCartao(), is(BandeiraCartao.valueOf(2)));
    }
    
    @Test
    public void testDelete(){
        LocalDate dataVencimento = LocalDate.of(2050, 9, 1);
        CartaoDTO cartao = new CartaoDTO(
                "picpay",
                "0000000000000000",
                dataVencimento,
                "080",
                "danieljacare",
                3,
                2);
        Long id = cartaoService.insert(cartao).id();

        cartaoService.delete(id);
        assertThrows(Exception.class, () -> cartaoService.findById(id));
    }

    @Test
    public void testFindById(){
        LocalDate dataVencimento = LocalDate.of(2050, 9, 1);
        CartaoDTO cartao = new CartaoDTO(
                "Itau",
                "0000000000000000",
                dataVencimento,
                "080",
                "danieljacare",
                3,
                2);
        Long id = cartaoService.insert(cartao).id();
        LoginDTO loginDTO = new LoginDTO("funcionario_jacare", "senha_funcionario_jacare");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String token = jwtService.generateJwt(result);
        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
            .pathParam("id", id)
            .when().get("/cartoes/{id}")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindByBanco(){
        LocalDate dataVencimento = LocalDate.of(2050, 9, 1);
        CartaoDTO cartao = new CartaoDTO(
                "Itau",
                "0000000000000000",
                dataVencimento,
                "080",
                "danieljacare",
                3,
                2);

        cartaoService.insert(cartao);

        LoginDTO loginDTO = new LoginDTO("funcionario_jacare", "senha_funcionario_jacare");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String token = jwtService.generateJwt(result);
        given()
                .headers("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
        .pathParam("banco", cartao.banco())
        .when().get("/cartoes/search/banco/{banco}")
        .then()
        .statusCode(200);
    }
}