package unitins.tp1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp1.dto.EnderecoDTO;
import unitins.tp1.dto.PessoaDTO;
import unitins.tp1.dto.PessoaResponseDTO;
import unitins.tp1.model.Endereco;
import unitins.tp1.model.Pessoa;
import unitins.tp1.model.Usuario;
import unitins.tp1.repository.EnderecoRepository;
import unitins.tp1.repository.PessoaRepository;
import unitins.tp1.repository.UsuarioRepository;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

    @Inject
    PessoaRepository repository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public PessoaResponseDTO insert(PessoaDTO dto) {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(dto.nome());
        novaPessoa.setCpf(dto.cpf());
        novaPessoa.setEmail(dto.email());
        if (dto.enderecos() != null &&
                !dto.enderecos().isEmpty()) {
            novaPessoa.setEnderecos(new ArrayList<Endereco>());
            for (EnderecoDTO end : dto.enderecos()) {
                Endereco endereco = new Endereco();
                endereco.setNome(end.nome());
                endereco.setLogradouro(end.logradouro());
                endereco.setNumero(end.numero());
                endereco.setBairro(end.bairro());
                endereco.setComplemento(end.complemento());
                endereco.setCidade(end.cidade());
                endereco.setEstado(end.estado());
                novaPessoa.getEnderecos().add(endereco);
            }
        }
        if (dto.listaTelefones() != null &&
                !dto.listaTelefones().isEmpty()) {
            novaPessoa.setListaTelefones(new ArrayList<String>());
            for (String telefone : dto.listaTelefones()) {
                novaPessoa.getListaTelefones().add(telefone);
            }
        }
        Usuario usuario = usuarioRepository.findById(dto.idUsuario());
        novaPessoa.setUsuario(usuario);

        repository.persist(novaPessoa);

        return PessoaResponseDTO.valueOf(novaPessoa);
    }

    @Override
    @Transactional
    public PessoaResponseDTO update(PessoaDTO dto, Long id) {

        Pessoa pessoaUpdate = repository.findById(id);
        if (pessoaUpdate != null) {
            pessoaUpdate.setNome(dto.nome());
            pessoaUpdate.setCpf(dto.cpf());
            pessoaUpdate.setEmail(dto.email());
            if (dto.enderecos() != null && !dto.enderecos().isEmpty()) {
                pessoaUpdate.setEnderecos(new ArrayList<Endereco>());
                for (EnderecoDTO enderecoDTO : dto.enderecos()) {
                    Endereco endereco = new Endereco();
                    endereco.setNome(enderecoDTO.nome());
                    endereco.setLogradouro(enderecoDTO.logradouro());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setCidade(enderecoDTO.cidade());
                    endereco.setEstado(enderecoDTO.estado());
                    pessoaUpdate.getEnderecos().add(endereco);
                }
            }
            if (dto.listaTelefones() != null && !dto.listaTelefones().isEmpty()) {
                pessoaUpdate.setListaTelefones(new ArrayList<String>());
                for (String tel : dto.listaTelefones()) {
                    pessoaUpdate.getListaTelefones().add(tel);
                }
            }

            Usuario usuario = usuarioRepository.findById(dto.idUsuario());
            pessoaUpdate.setUsuario(usuario);

            repository.persist(pessoaUpdate);
        } else {
            throw new NotFoundException();
        }

        return PessoaResponseDTO.valueOf(pessoaUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public List<PessoaResponseDTO> findByEnderecoId(Long enderecoId) {
        return repository.findByEnderecoId(enderecoId).stream()
                .map(PessoaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public PessoaResponseDTO findById(Long id) {
        return PessoaResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<PessoaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PessoaResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

}