package unitins.tp1;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

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
    protected EntityManager bd;

    @Inject
    protected UsuarioService usuarioService;

    @Inject
    protected HashService hashService;

    @Inject
    protected JwtService jwtService;

    protected String tokenUser;
    protected String tokenAdmin;

    /**
     * Foi necessario executar o TRUNCATE nas tabelas manualmente para evitar problemas de tokens nos testes,
     * pois o token é gerado com base no usuário que é inserido no setUp, com isso ao executar o teste e voltar para o
     * beforeEach, alega erro de Login ja existente.
     **/
    @BeforeEach
    @Transactional
    public void setUpDefault() {
        bd.createNativeQuery(
            "TRUNCATE TABLE cliente_usuario, cliente_endereco, arma, cliente, endereco, usuario " +
            "RESTART IDENTITY CASCADE"
        ).executeUpdate();

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
}