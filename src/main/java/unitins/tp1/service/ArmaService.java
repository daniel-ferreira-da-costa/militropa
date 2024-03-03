package unitins.tp1.service;

import java.util.List;

import unitins.tp1.dto.ArmaDTO;
import unitins.tp1.dto.ArmaResponseDTO;

public interface ArmaService {
    public ArmaResponseDTO insert(ArmaDTO dto);

    public ArmaResponseDTO update(ArmaDTO dto, Long id);

    public void delete(Long id);

    public ArmaResponseDTO findById(Long id);

    public List<ArmaResponseDTO> findByNome(String nome);

    public List<ArmaResponseDTO> findByAll(); 
}
