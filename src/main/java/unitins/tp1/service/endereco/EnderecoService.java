package unitins.tp1.service.endereco;

import java.util.List;

import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;



public interface EnderecoService {
    public EnderecoResponseDTO insert(EnderecoDTO dto);

    public EnderecoResponseDTO update(EnderecoDTO dto, Long id);

    public void delete(Long id);

    public EnderecoResponseDTO findById(Long id);
    
    public List<EnderecoResponseDTO> findByNome(String nome);

    public List<EnderecoResponseDTO> findByAll();
}