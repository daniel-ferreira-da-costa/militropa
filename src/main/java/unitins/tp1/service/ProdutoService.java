package unitins.tp1.service;

import java.util.List;

import unitins.tp1.dto.ProdutoDTO;
import unitins.tp1.dto.ProdutoResponseDTO;

public interface ProdutoService {

    public ProdutoResponseDTO insert(ProdutoDTO dto);

    public ProdutoResponseDTO update(ProdutoDTO dto, Long id);

    public void delete(Long id);

    public ProdutoResponseDTO findById(Long id);

    public List<ProdutoResponseDTO> findByNome(String nome);

    public List<ProdutoResponseDTO> findByAll(); 

}