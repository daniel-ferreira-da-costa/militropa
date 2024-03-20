package unitins.tp1.dto;

import unitins.tp1.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double preco,
        int qtdNoEstoque) {
    public static ProdutoResponseDTO valueOf(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQtdNoEstoque());
    }
}
