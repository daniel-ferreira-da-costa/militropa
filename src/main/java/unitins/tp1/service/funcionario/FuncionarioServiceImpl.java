package unitins.tp1.service.funcionario;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.dto.funcionario.FuncionarioDTO;
import unitins.tp1.dto.funcionario.FuncionarioResponseDTO;
import unitins.tp1.model.Endereco;
import unitins.tp1.model.Funcionario;
import unitins.tp1.model.Perfil;
import unitins.tp1.model.Usuario;
import unitins.tp1.repository.EnderecoRepository;
import unitins.tp1.repository.FuncionarioRepository;
import unitins.tp1.repository.UsuarioRepository;
import unitins.tp1.service.hash.HashService;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    FuncionarioRepository repository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public FuncionarioResponseDTO insert(FuncionarioDTO dto) {
        if (dto == null) {
            throw new ValidationException("Dados inválidos");
        }
        validarEmail(dto.email());
        validarCpf(dto.cpf());
        validarMatricula(dto.matricula());

        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(dto.nome());
        novoFuncionario.setCpf(dto.cpf());
        novoFuncionario.setEmail(dto.email());
        novoFuncionario.setMatricula(dto.matricula());
        if (dto.listaTelefones() != null &&
                !dto.listaTelefones().isEmpty()) {
            novoFuncionario.setListaTelefones(new ArrayList<String>());
            for (String telefone : dto.listaTelefones()) {
                novoFuncionario.getListaTelefones().add(telefone);
            }

            if (dto.listaEnderecos() != null &&
                    !dto.listaEnderecos().isEmpty()) {
                novoFuncionario.setListaEnderecos(new ArrayList<Endereco>());
                for (EnderecoDTO end : dto.listaEnderecos()) {
                    Endereco endereco = new Endereco();
                    endereco.setNome(end.nome());
                    endereco.setLogradouro(end.logradouro());
                    endereco.setNumero(end.numero());
                    endereco.setBairro(end.bairro());
                    endereco.setComplemento(end.complemento());
                    endereco.setCep(end.cep());
                    endereco.setCidade(end.cidade());
                    endereco.setEstado(end.estado());
                    novoFuncionario.getListaEnderecos().add(endereco);
                }
            }

            Usuario usuario = new Usuario();
            usuario.setLogin(dto.matricula());
            usuario.setSenha(hashService.getHashSenha(dto.senha()));
            usuario.setPerfil(Perfil.ADMIN);

            usuarioRepository.persist(usuario);
            novoFuncionario.setUsuario(usuario);

            repository.persist(novoFuncionario);

        }
        return FuncionarioResponseDTO.valueOf(novoFuncionario);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO update(FuncionarioDTO dto, Long id) {
        Funcionario funcionarioUpdate = repository.findById(id);

        if (funcionarioUpdate == null) {
            throw new NotFoundException("Funcionário não encontrado");
        }

        Usuario usuario = funcionarioUpdate.getUsuario();
        funcionarioUpdate.setUsuario(usuario);

        List<Endereco> listaEnd = funcionarioUpdate.getListaEnderecos();
        funcionarioUpdate.setListaEnderecos(listaEnd);

        List<String> listaTel = funcionarioUpdate.getListaTelefones();
        funcionarioUpdate.setListaTelefones(listaTel);

        String matricula = funcionarioUpdate.getMatricula();
        funcionarioUpdate.setMatricula(matricula);

        if (funcionarioUpdate != null) {
            funcionarioUpdate.setNome(dto.nome());
            funcionarioUpdate.setCpf(dto.cpf());
            funcionarioUpdate.setEmail(dto.email());

            repository.persist(funcionarioUpdate);
        }

        return FuncionarioResponseDTO.valueOf(funcionarioUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {
        return FuncionarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(n -> FuncionarioResponseDTO.valueOf(n)).toList();
    }

    @Override
    public List<FuncionarioResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(m -> FuncionarioResponseDTO.valueOf(m)).toList();
    }

    @Override
    public FuncionarioResponseDTO findByMatricula(String matricula) {
        return FuncionarioResponseDTO.valueOf(repository.findByMatricula(matricula));
    }

    public void validarCpf(String cpf) {
        Funcionario funcionario = repository.findByCpf(cpf);
        if (funcionario != null) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
    }

    public void validarEmail(String email) {
        Funcionario funcionario = repository.findByEmail(email);
        if (funcionario != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
    }

    public void validarMatricula(String matricula) {
        Funcionario funcionario = repository.findByMatricula(matricula);
        if (funcionario != null) {
            throw new IllegalArgumentException("Matrícula já cadastrada");
        }
    }

    @Transactional
    public String alterarMatricula(String matricula) {
        String login = jwt.getName();
        Usuario usuario = usuarioRepository.findByLogin(login);
        Funcionario funcionario = repository.findById(usuario.getId());
        funcionario.setMatricula(matricula);
        repository.persist(funcionario);
        return "Matrícula alterada com sucesso!";
    }

    @Override
    @Transactional
    public EnderecoResponseDTO insetEndereco(EnderecoDTO dto, Long id) {
        Funcionario funcionario = repository.findById(id);
        if (funcionario == null) {
            throw new ValidationException("O Funcionario " + funcionario + " não encontrado, tente novamente.");
        }

        Endereco novoEndereco = new Endereco();
        novoEndereco.setNome(dto.nome());
        novoEndereco.setEstado(dto.estado());
        novoEndereco.setCidade(dto.cidade());
        novoEndereco.setLogradouro(dto.logradouro());
        novoEndereco.setNumero(dto.numero());
        novoEndereco.setBairro(dto.bairro());
        novoEndereco.setComplemento(dto.complemento());
        novoEndereco.setCep(dto.cep());
        enderecoRepository.persist(novoEndereco);


        funcionario.getListaEnderecos().add(novoEndereco);
        enderecoRepository.persist(novoEndereco);

        return EnderecoResponseDTO.valueOf(novoEndereco);    
    }

    @Override
    @Transactional
    public String insetTelefone(String telefone, Long id) {
        Funcionario funcionario = repository.findById(id);
        if (funcionario == null) {
            throw new ValidationException("O Funcionario " + funcionario + " não encontrado, tente novamente.");
        }

        if (funcionario.getListaTelefones() == null) {
            funcionario.setListaTelefones(new ArrayList<>());
        }
        funcionario.getListaTelefones().add(telefone);
        repository.persist(funcionario);

        return telefone;
    }
}
