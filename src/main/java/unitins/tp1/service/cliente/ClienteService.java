package unitins.tp1.service.cliente;

import java.util.List;

import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.cliente.ClienteResponseDTO;



public interface ClienteService {
    
    public ClienteResponseDTO insert(ClienteDTO dto);

    public ClienteResponseDTO update(ClienteDTO dto, Long id);

    public void delete(Long id);

    public ClienteResponseDTO findById(Long id);

    public List<ClienteResponseDTO> findByNome(String nome);

    public List<ClienteResponseDTO> findByAll(); 

}