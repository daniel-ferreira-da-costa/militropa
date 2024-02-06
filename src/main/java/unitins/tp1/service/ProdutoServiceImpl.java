package unitins.tp1.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp1.dto.ProdutoDTO;
import unitins.tp1.dto.ProdutoResponseDTO;
import unitins.tp1.model.Produto;
import unitins.tp1.repository.ProdutoRepository;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService{
    @Inject
    ProdutoRepository repository;

    @Override
    @Transactional
    public ProdutoResponseDTO insert(ProdutoDTO dto) {
        Produto novoProduto = new Produto();
            novoProduto.setNome(dto.getNome());
            novoProduto.setDescricao(dto.getDescricao());
            novoProduto.setPreco(dto.getPreco());
            novoProduto.setValor(dto.getValor());
            novoProduto.setQtdNoEstoque(dto.getQtdNoEstoque());

        repository.persist(novoProduto);

        return ProdutoResponseDTO.valueOf(novoProduto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO update(ProdutoDTO dto, Long id) {
        
        Produto produto = repository.findById(id);
        if (produto != null) {
            produto.setNome(dto.getNome());
            produto.setDescricao(dto.getDescricao());
            produto.setPreco(dto.getPreco());
            produto.setValor(dto.getValor());
            produto.setQtdNoEstoque(dto.getQtdNoEstoque());
        } else 
            throw new NotFoundException();

        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) 
            throw new NotFoundException();
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        return ProdutoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(p -> ProdutoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<ProdutoResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(p -> ProdutoResponseDTO.valueOf(p)).toList();
    }
    
}