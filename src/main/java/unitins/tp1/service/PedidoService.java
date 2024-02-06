package unitins.tp1.service;

import java.util.List;

import unitins.tp1.dto.PedidoDTO;
import unitins.tp1.dto.PedidoResponseDTO;

public interface PedidoService {

        public PedidoResponseDTO insert(PedidoDTO dto, String login);
        public PedidoResponseDTO findById(Long id);
        public List<PedidoResponseDTO> findByAll();
        public List<PedidoResponseDTO> findByAll(String login);
}