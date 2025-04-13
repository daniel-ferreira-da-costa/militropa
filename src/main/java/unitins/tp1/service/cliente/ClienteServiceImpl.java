package unitins.tp1.service.cliente;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp1.model.Endereco;
import unitins.tp1.model.Perfil;
import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.cliente.ClienteResponseDTO;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.model.Cliente;
import unitins.tp1.model.Usuario;
import unitins.tp1.repository.EnderecoRepository;
import unitins.tp1.repository.ClienteRepository;
import unitins.tp1.repository.UsuarioRepository;
import unitins.tp1.service.hash.HashService;
import unitins.tp1.validation.ValidationException;
import io.quarkus.security.identity.SecurityIdentity;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

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
    public ClienteResponseDTO insert(ClienteDTO dto) {
        validarEmailCliente(dto.email());
        validarCpfCliente(dto.cpf());
        validarLoginCliente(dto.login());

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(dto.nome());
        novoCliente.setCpf(dto.cpf());
        novoCliente.setEmail(dto.email());
        novoCliente.setNumeroRegistro_posse_porte(dto.numeroRegistro_posse_porte());
        if (dto.listaEnderecos() != null &&
                !dto.listaEnderecos().isEmpty()) {
            novoCliente.setListaEnderecos(new ArrayList<Endereco>());
            for (EnderecoDTO end : dto.listaEnderecos()) {
                Endereco endereco = new Endereco();
                endereco.setNome(end.nome());
                endereco.setLogradouro(end.logradouro());
                endereco.setNumero(end.numero());
                endereco.setBairro(end.bairro());
                endereco.setComplemento(end.complemento());
                endereco.setCidade(end.cidade());
                endereco.setEstado(end.estado());
                novoCliente.getListaEnderecos().add(endereco);
            }
        }

        if (dto.listaTelefones() != null &&
                !dto.listaTelefones().isEmpty()) {
            novoCliente.setListaTelefones(new ArrayList<String>());
            for (String telefone : dto.listaTelefones()) {
                novoCliente.getListaTelefones().add(telefone);
            }
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setPerfil(Perfil.USER);
        
        usuarioRepository.persist(usuario);
        novoCliente.setUsuario(usuario);

        clienteRepository.persist(novoCliente);

        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {
        
        Cliente clienteUpdate = clienteRepository.findById(id);
        if (clienteUpdate == null) {
            throw new NotFoundException("Cliente não encontrado");
        }

        Usuario usuario = clienteUpdate.getUsuario();
        clienteUpdate.setUsuario(usuario);

        List<Endereco> listaEnd = clienteUpdate.getListaEnderecos();
        clienteUpdate.setListaEnderecos(listaEnd);

        List<String> listaTel = clienteUpdate.getListaTelefones();
        clienteUpdate.setListaTelefones(listaTel);
        
        if (clienteUpdate != null) {
            clienteUpdate.setNome(dto.nome());
            clienteUpdate.setCpf(dto.cpf());
            clienteUpdate.setEmail(dto.email());
            clienteUpdate.setNumeroRegistro_posse_porte(dto.numeroRegistro_posse_porte());

            clienteRepository.persist(clienteUpdate);
        }

        return ClienteResponseDTO.valueOf(clienteUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!clienteRepository.deleteById(id))
            throw new NotFoundException();
    }

    public void validarEmailCliente(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            throw new ValidationException("email", "O email " + email + " já foi cadastrado");
        }
    }

    public void validarCpfCliente(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente != null) {
            throw new ValidationException("cpf", "O cpf " + cpf + " ja foi cadastrado");
        }
    }

    public void validarLoginCliente(String login) {
        Cliente cliente = clienteRepository.findByLogin(login);
        if (cliente != null) {
            throw new ValidationException("Login", "O Login " + login + " já foi cadastrado");
        }
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        return ClienteResponseDTO.valueOf(clienteRepository.findById(id));
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return clienteRepository.findByNome(nome).stream()
                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByAll() {
        return clienteRepository.listAll().stream()
                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public ClienteResponseDTO findByUsuario(String login) {
        return ClienteResponseDTO.valueOf(clienteRepository.findByLogin(login));
   }

    @Override
    @Transactional
    public EnderecoResponseDTO insetEndereco(EnderecoDTO dto, Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new ValidationException("cliente", "O Cliente " + cliente + " não encontrado, tente novamente.");
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

        cliente.getListaEnderecos().add(novoEndereco);
        clienteRepository.persist(cliente);

        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public String insetTelefone(String telefone, Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if(cliente == null) {
            throw new ValidationException("cliente", "O Cliente " + cliente + " não encontrado, tente novamente.");
        }
        if (cliente.getListaTelefones() == null) {
            cliente.setListaTelefones(new ArrayList<>());
        }
        cliente.getListaTelefones().add(telefone);
        clienteRepository.persist(cliente);

        return telefone;
    }
}