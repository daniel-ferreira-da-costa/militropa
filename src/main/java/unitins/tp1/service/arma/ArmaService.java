package unitins.tp1.service.arma;

import java.util.List;

import unitins.tp1.dto.arma.ArmaDTO;
import unitins.tp1.dto.arma.ArmaResponseDTO;



public interface ArmaService {
    public ArmaResponseDTO insert(ArmaDTO dto);

    public ArmaResponseDTO update(ArmaDTO dto, Long id);

    public void delete(Long id);

    public ArmaResponseDTO updateNomeImagem(Long id, String nomeImagem) ;

    public ArmaResponseDTO findById(Long id);

    public List<ArmaResponseDTO> findByNome(String nome);

    public List<ArmaResponseDTO> findByAll(); 
}
