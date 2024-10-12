package unitins.tp1.service.usuario;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.dto.usuario.alterarLoginUsuarioDTO;
import unitins.tp1.dto.usuario.alterarSenhaUsuarioDTO;
import unitins.tp1.model.Perfil;
import unitins.tp1.model.Usuario;
import unitins.tp1.repository.UsuarioRepository;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.validation.ValidationException;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {

        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");

        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        novoUsuario.setPerfil(Perfil.valueOf(dto.perfil()));

            repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {

        Usuario usuario = repository.findById(id);
        usuario.setLogin(dto.login());
        String hashSenha = hashService.getHashSenha(dto.senha());
        usuario.setSenha(hashSenha);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO alterarSenha(alterarSenhaUsuarioDTO alterarSenhaUsuarioDTO, String login) {
        Usuario usuario = repository.findByLogin(login);
        Log.info("Senha antiga: "+ usuario.getSenha());
        usuario.setSenha(hashService.getHashSenha(alterarSenhaUsuarioDTO.senha()));
        Log.info("Senha nova: "+ usuario.getSenha());
        Log.info("Senha alterada com sucesso!");
        repository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO alterarLogin(alterarLoginUsuarioDTO alterarLoginUsuarioDTO, String login) {
        Usuario usuario = repository.findByLogin(login);
        Log.info("Login antigo: "+ usuario.getLogin());
        usuario.setLogin(alterarLoginUsuarioDTO.login());
        Log.info("Login novo: "+ usuario.getLogin());
        Log.info("Login alterada com sucesso!");
        repository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Usuario usuario = repository.findById(id);

        if(usuario != null){
            repository.delete(usuario);
        }else {
            throw new NotFoundException("Usuário não encontrado!");
        }
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String login) {
        List<Usuario> usuarios = repository.find("UPPER(login) LIKE UPPER(?1)", "%" + login + "%").list();
        // Converte a lista de usuários para uma lista de DTOs de resposta
        return usuarios.stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        try {
            Usuario usuario = repository.findByLoginAndSenha(login, senha);
            if (usuario == null) {
                throw new ValidationException("login", "Login ou senha inválido");
            }
            return UsuarioResponseDTO.valueOf(usuario);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para imprimir a pilha de exceção no console
            throw new ValidationException("login", "Ocorreu um erro durante a autenticação. Consulte os logs para obter mais informações.");
        }
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
       Usuario usuario = repository.findByLogin(login);
       if (usuario == null)
        throw new ValidationException("login", "Login inválido");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findMyUser() {
        // Obtendo o login pelo token jwt
        String loginUsuarioLogado = jwt.getSubject();

        // Verificando se o usuário logado está tentando atualizar o próprio perfil
        Usuario usuarioLogado = repository.findByLogin(loginUsuarioLogado);
        return UsuarioResponseDTO.valueOf(usuarioLogado);
    }

}