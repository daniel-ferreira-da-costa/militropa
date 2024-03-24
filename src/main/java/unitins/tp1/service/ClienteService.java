package unitins.tp1.service;

import java.util.List;

import unitins.tp1.dto.ClienteDTO;
import unitins.tp1.dto.ClienteResponseDTO;

public interface ClienteService {
    
    public ClienteResponseDTO insert(ClienteDTO dto);

    public ClienteResponseDTO update(ClienteDTO dto, Long id);

    public void delete(Long id);

    public ClienteResponseDTO findById(Long id);

    public List<ClienteResponseDTO> findByNome(String nome);

    public List<ClienteResponseDTO> findByAll(); 

    public List<ClienteResponseDTO> findByEnderecoId(Long enderecoId);
    

}