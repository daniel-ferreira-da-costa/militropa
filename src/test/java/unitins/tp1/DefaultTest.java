package unitins.tp1;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import java.util.List;

import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.cliente.ClienteResponseDTO;
import unitins.tp1.dto.funcionario.FuncionarioDTO;
import unitins.tp1.dto.funcionario.FuncionarioResponseDTO;
import unitins.tp1.service.cliente.ClienteService;
import unitins.tp1.service.funcionario.FuncionarioService;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.service.jwt.JwtService;

@QuarkusTest
public abstract class DefaultTest {

    @Inject protected ClienteService clienteService;
    @Inject protected FuncionarioService funcionarioService;
    @Inject protected HashService hashService;
    @Inject protected JwtService jwtService;

    protected Long clienteId;
    protected Long funcionarioId;

    protected String loginUser;
    protected String senhaUser;
    protected String tokenUser;

    protected String loginAdmin;
    protected String senhaAdmin;
    protected String tokenAdmin;

    @BeforeEach
    @Transactional
    public void setUpDefault() {
        // limpa o que sobrou de qualquer teste anterior
        if (funcionarioId != null) {
            try { funcionarioService.delete(funcionarioId); } catch (Exception ignored) {}
            funcionarioId = null;
        }
        if (clienteId != null) {
            try { clienteService.delete(clienteId);   } catch (Exception ignored) {}
            clienteId = null;
        }

        // cria de novo o CLIENTE
        loginUser = "cliente_rafaela";
        senhaUser = "senha_cliente";
        EnderecoDTO endCli = new EnderecoDTO(
            "Casa", "Rua A", "123", "Apto 1",
            "Centro", "12345-678", "CidadeX", "EstadoY"
        );
        ClienteDTO dtoCli = new ClienteDTO(
            "Cliente Rafaela",
            "111.222.111-44",
            "cliente@teste.com",
            "REG123",
            List.of("999999999"),
            List.of(endCli),
            loginUser,
            senhaUser
        );
        ClienteResponseDTO cResp = clienteService.insert(dtoCli);
        clienteId = cResp.id();
        tokenUser = jwtService.generateJwt(cResp.usuario());

        // cria de novo o FUNCIONÁRIO/ADMIN
        loginAdmin = "2025111111";
        senhaAdmin = "senha_funcionario";
        EnderecoDTO endFun = new EnderecoDTO(
            "Casa Func", "Rua B", "456", "Casa 2",
            "BairroX", "98765-432", "CidadeY", "EstadoZ"
        );
        FuncionarioDTO dtoFun = new FuncionarioDTO(
            "Funcionario Pedro",
            "555.666.777-88",
            "funcionario@teste.com",
            List.of("888888888"),
            List.of(endFun),
            loginAdmin,
            senhaAdmin
        );
        FuncionarioResponseDTO fResp = funcionarioService.insert(dtoFun);
        funcionarioId = fResp.id();
        tokenAdmin    = jwtService.generateJwt(fResp.usuario());
    }

    @AfterEach
    @Transactional
    public void tearDownDefault() {
        if (funcionarioId != null) {
            try { funcionarioService.delete(funcionarioId); } catch (Exception ignored) {}
            funcionarioId = null;
        }
        if (clienteId != null) {
            try { clienteService.delete(clienteId);   } catch (Exception ignored) {}
            clienteId = null;
        }
    }

}