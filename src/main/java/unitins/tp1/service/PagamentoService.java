package unitins.tp1.service;


import unitins.tp1.dto.CartaoDTO;
import unitins.tp1.dto.PagamentoResponseDTO;

public interface PagamentoService {

    public PagamentoResponseDTO pagarCartao(CartaoDTO dto);
}