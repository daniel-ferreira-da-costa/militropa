package unitins.tp1;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import io.quarkus.test.junit.QuarkusTest;

import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.service.jwt.JwtService;
import unitins.tp1.service.usuario.UsuarioService;

@QuarkusTest
public abstract class DefaultTest {

    @Inject
    protected UsuarioService usuarioService;

    @Inject
    protected HashService hashService;

    @Inject
    protected JwtService jwtService;

    protected String tokenUser;
    protected String tokenAdmin;


    @BeforeEach
    @Transactional
    public void setUpDefault() {
            // usuário comum
            usuarioService.insert(
                new UsuarioDTO("cliente_rafaela", "senha_cliente_rafaela", 1)
            );
            // usuário admin
            usuarioService.insert(
                new UsuarioDTO("172839", "senha_funcionario_rafaela", 2)
            );

            // gera token do cliente
            String hashUser = hashService.getHashSenha("senha_cliente_rafaela").toString();
            UsuarioResponseDTO respUser =
            usuarioService.findByLoginAndSenha("cliente_rafaela", hashUser);
            tokenUser = jwtService.generateJwt(respUser);
            
            // gera token do admin
            String hashAdmin = hashService.getHashSenha("senha_funcionario_rafaela").toString();
            UsuarioResponseDTO respAdmin =
            usuarioService.findByLoginAndSenha("172839", hashAdmin);
            tokenAdmin = jwtService.generateJwt(respAdmin);
    }

    @AfterEach
    public void tearDown() {
        usuarioService.delete(usuarioService.findByLogin("cliente_rafaela").id());
        usuarioService.delete(usuarioService.findByLogin("172839").id());
    }
}