package unitins.tp1;

import static io.restassured.RestAssured.given;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.cliente.ClienteResponseDTO;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.usuario.LoginDTO;
import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.service.cliente.ClienteService;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.service.jwt.JwtService;
import unitins.tp1.service.usuario.UsuarioService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;

@QuarkusTest
public class ClienteResourceTest {

        @Inject
        ClienteService clienteService;

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
                                .when().get("/clientes")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() {
                EnderecoDTO endereco = new EnderecoDTO(
                                "Casa dos manos",
                                "quadra 8",
                                "123",
                                "Casa 1",
                                "Vale do sol",
                                "77021-550",
                                "Palmas",
                                "TO");
                ArrayList<EnderecoDTO> listaEnderecos = new ArrayList<EnderecoDTO>();
                listaEnderecos.add(endereco);

                ArrayList<String> listaTelefones = new ArrayList<String>();
                listaTelefones.add("63 90000-1222");

                                UsuarioDTO usuario = new UsuarioDTO(
                                "jacarvalho",
                                "senha123",
                                1);
                Long idUsuario = usuarioService.insert(usuario).id();

                ClienteDTO cliente = new ClienteDTO(
                                "Jac arvalho",
                                "321.123.111-66",
                                "jacaaa@gmail.com",
                                "9999988888", listaTelefones, listaEnderecos, idUsuario);
                ClienteResponseDTO clienteResponse = clienteService.insert(cliente);

                assertThat(clienteResponse.id(), notNullValue());
                assertThat(clienteResponse.nome(), is("Jac arvalho"));
                assertThat(clienteResponse.cpf(), is("321.123.111-66"));
                assertThat(clienteResponse.email(), is("jacaaa@gmail.com"));
                assertThat(clienteResponse.numeroRegistro_posse_porte(), is("9999988888"));
                assertThat(clienteResponse.listaTelefones(), is(listaTelefones));
                assertThat(clienteResponse.enderecos(), is(listaEnderecos));
                assertThat(clienteResponse.usuario(), is(idUsuario));
        }

}
