package unitins.tp1;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.ntlm;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import unitins.tp1.dto.cartao.CartaoDTO;
import unitins.tp1.dto.cartao.CartaoResponseDTO;
import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.cliente.ClienteResponseDTO;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.dto.usuario.AuthUsuarioDTO;
import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.model.BandeiraCartao;
import unitins.tp1.model.TipoCartao;
import unitins.tp1.service.cartao.CartaoService;
import unitins.tp1.service.cliente.ClienteService;
import unitins.tp1.service.endereco.EnderecoService;
import unitins.tp1.service.usuario.UsuarioService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class ClienteResourceTest {

        @Inject
        ClienteService clienteService;

        @Inject
        UsuarioService usuarioService;

        @Inject
        CartaoService cartaoService;

        @Test
        public void testGetAll() {
                given()
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

                LocalDate dataVencimento = LocalDate.now();
                CartaoDTO cartao = new CartaoDTO(
                                "santander",
                                "0000000000000003",
                                dataVencimento,
                                "080",
                                "kkkk",
                                3,
                                2);

                ArrayList<CartaoDTO> listaCartoes = new ArrayList<CartaoDTO>();
                listaCartoes.add(cartao);

                UsuarioDTO usuario = new UsuarioDTO(
                                "jacarvalho",
                                "senha123",
                                1);
                Long idUsuario = usuarioService.insert(usuario).id();

                ClienteDTO cliente = new ClienteDTO(
                                "Jac arvalho",
                                "321.123.111-66",
                                "jacaaa@gmail.com",
                                "9999988888", listaTelefones, listaCartoes, listaEnderecos, idUsuario);
                ClienteResponseDTO clienteResponse = clienteService.insert(cliente);

                assertThat(clienteResponse.id(), notNullValue());
                assertThat(clienteResponse.nome(), is("Jac arvalho"));
                assertThat(clienteResponse.cpf(), is("321.123.111-66"));
                assertThat(clienteResponse.email(), is("jacaaa@gmail.com"));
                assertThat(clienteResponse.numeroRegistro_posse_porte(), is("9999988888"));
                assertThat(clienteResponse.listaTelefones(), is(listaTelefones));
                assertThat(clienteResponse.listaCartoes(), is(listaCartoes));
                assertThat(clienteResponse.enderecos(), is(listaEnderecos));
                assertThat(clienteResponse.usuario(), is(idUsuario));
        }

}
