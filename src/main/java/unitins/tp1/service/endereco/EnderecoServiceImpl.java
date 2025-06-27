package unitins.tp1.service.endereco;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.model.Endereco;
import unitins.tp1.repository.ClienteRepository;
import unitins.tp1.repository.EnderecoRepository;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Inject
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public EnderecoResponseDTO insert(EnderecoDTO dto) {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setNome(dto.nome());
        novoEndereco.setEstado(dto.estado());
        novoEndereco.setCidade(dto.cidade());
        novoEndereco.setLogradouro(dto.logradouro());
        novoEndereco.setNumero(dto.numero());
        novoEndereco.setBairro(dto.bairro());
        novoEndereco.setComplemento(dto.complemento());
        novoEndereco.setCep(dto.cep());
        repository.persist(novoEndereco);
        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(EnderecoDTO dto, Long id) {
        Endereco endereco = repository.findById(id);
        if (endereco != null) {
            endereco.setNome(dto.nome());
            endereco.setEstado(dto.estado());
            endereco.setCidade(dto.cidade());
            endereco.setLogradouro(dto.logradouro());
            endereco.setNumero(dto.numero());
            endereco.setBairro(dto.bairro());
            endereco.setComplemento(dto.complemento());
            endereco.setCep(dto.cep());
        } else
            throw new NotFoundException();

        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        Endereco e = repository.findById(id);
        if (e == null) {
            throw new NotFoundException("Endereço não encontrado: " + id);
        }
        return EnderecoResponseDTO.valueOf(e);
    }

    @Override
    public List<EnderecoResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EnderecoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

}