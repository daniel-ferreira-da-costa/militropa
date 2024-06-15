package unitins.tp1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import unitins.tp1.dto.arma.ArmaDTO;
import unitins.tp1.dto.arma.ArmaResponseDTO;
import unitins.tp1.dto.usuario.LoginDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.model.TipoArma;
import unitins.tp1.service.arma.ArmaService;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.service.hash.HashServiceImpl;
import unitins.tp1.service.jwt.JwtService;
import unitins.tp1.service.usuario.UsuarioService;

@QuarkusTest
public class ArmaResourceTest {
    @Inject
    ArmaService armaService;

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
        LoginDTO loginDTO = new LoginDTO("musk", "senha1");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenUser = jwtService.generateJwt(result);

        given()
                .when().get("/armas")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() {
        ArmaDTO arma = new ArmaDTO(
                "Glock",
                20,
                15000,
                "Bom dia princesa, pfvr sente na glock",
                2,
                "princesa",
                "madeira rustica",
                "9mm",
                "curto",
                100,
                "adf9999",
                "40028922",
                "ajajaj999",
                "0000011111");
        ArmaResponseDTO armaResponse = armaService.insert(arma);
        assertThat(armaResponse, notNullValue());
        assertThat(armaResponse.nome(), is("Glock"));
        assertThat(armaResponse.qtdNoEstoque(), is(20));
        assertThat(armaResponse.preco(), is(15000.0));
        assertThat(armaResponse.descricao(), is("Bom dia princesa, pfvr sente na glock"));
        assertThat(armaResponse.tipo(), is(TipoArma.valueOf(2)));
        assertThat(armaResponse.marca(), is("princesa"));
        assertThat(armaResponse.acabamento(), is("madeira rustica"));
        assertThat(armaResponse.calibre(), is("9mm"));
        assertThat(armaResponse.comprimentoDoCano(), is("curto"));
        assertThat(armaResponse.capacidadeDeTiro(), is(100));
        assertThat(armaResponse.numeroSigma(), is("adf9999"));
        assertThat(armaResponse.numeroDaArma(), is("40028922"));
        assertThat(armaResponse.modelo(), is("ajajaj999"));
        assertThat(armaResponse.rna(), is("0000011111"));
    }

    @Test
    public void testUpdate() {
        ArmaDTO arma = new ArmaDTO(
                "3oiao",
                100,
                8000,
                "chegar no forró dar tiro pra riba, e negada correndo, sonho de qlqr um",
                1,
                "duvidosa",
                "de má qualidade",
                ".38",
                "cano longo",
                5,
                "dadaad000",
                "999jjjj99",
                "gg777",
                "000001111");
        Long id = armaService.insert(arma).id();

        ArmaDTO armaUpdate = new ArmaDTO(
                "Smith & Wesson Modelo 686",
                100,
                8500,
                "Revólver calibre .38 especial, cano longo, capacidade para 5 tiros",
                1,
                "Smith & Wesson",
                "Aço Inoxidável",
                ".38 Special",
                "4 polegadas",
                5,
                "SW123456",
                "123456",
                "M686",
                "RN123456");

        ArmaResponseDTO armaAtualizada = armaService.update(armaUpdate, id);
        assertThat(armaAtualizada, notNullValue());

        ArmaResponseDTO armaResponse = armaService.findById(id);
        assertThat(armaResponse.nome(), is("Smith & Wesson Modelo 686"));
        assertThat(armaResponse.qtdNoEstoque(), is(100));
        assertThat(armaResponse.preco(), is(8500.0));
        assertThat(armaResponse.descricao(), is("Revólver calibre .38 especial, cano longo, capacidade para 5 tiros"));
        assertThat(armaResponse.tipo(), is(TipoArma.valueOf(1)));
        assertThat(armaResponse.marca(), is("Smith & Wesson"));
        assertThat(armaResponse.acabamento(), is("Aço Inoxidável"));
        assertThat(armaResponse.calibre(), is(".38 Special"));
        assertThat(armaResponse.comprimentoDoCano(), is("4 polegadas"));
        assertThat(armaResponse.capacidadeDeTiro(), is(5));
        assertThat(armaResponse.numeroSigma(), is("SW123456"));
        assertThat(armaResponse.numeroDaArma(), is("123456"));
        assertThat(armaResponse.modelo(), is("M686"));
        assertThat(armaResponse.rna(), is("RN123456"));
    }

    @Test
    public void testDelete(){
        ArmaDTO arma = new ArmaDTO(
                "3oiao",
                100,
                8000,
                "chegar no forró dar tiro pra riba, e negada correndo, sonho de qlqr um",
                1,
                "duvidosa",
                "de má qualidade",
                ".38",
                "cano longo",
                5,
                "dadaad000",
                "999jjjj99",
                "gg777",
                "000001111");
        Long id = armaService.insert(arma).id();

        armaService.delete(id);

        assertThrows(Exception.class, () -> armaService.findById(id));
    }

    @Test
    public void testFindById(){
        ArmaDTO arma = new ArmaDTO(
                "3oiao",
                100,
                8000,
                "chegar no forró dar tiro pra riba, e negada correndo, sonho de qlqr um",
                1,
                "duvidosa",
                "de má qualidade",
                ".38",
                "cano longo",
                5,
                "dadaad000",
                "999jjjj99",
                "gg777",
                "000001111");
        Long id = armaService.insert(arma).id();

        given()
            .pathParam("id", id)
            .when().get("/armas/{id}")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindByNome(){
        ArmaDTO arma = new ArmaDTO(
                "3oiao boladão brecado",
                100,
                8000,
                "chegar no forró dar tiro pra riba, e negada correndo, sonho de qlqr um",
                1,
                "duvidosa",
                "de má qualidade",
                ".38",
                "cano longo",
                5,
                "dadaad000",
                "999jjjj99",
                "gg777",
                "000001111");
        armaService.insert(arma);

        given()
            .pathParam("nome", arma.getNome())
            .when().get("/armas/search/nome/{nome}")
            .then()
            .statusCode(200);
    }
}
