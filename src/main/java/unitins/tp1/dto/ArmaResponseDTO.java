package unitins.tp1.dto;

import unitins.tp1.model.Arma;
import unitins.tp1.model.TipoArma;

public record ArmaResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double valor,
    Double preco,
    int qtdNoEstoque,
    TipoArma tipo,
    String tipoMunicao,
    Double peso
    ){
        public static ArmaResponseDTO valueOf(Arma arma){
            return new ArmaResponseDTO(
            arma.getId(),
            arma.getNome(),
            arma.getDescricao(),
            arma.getPreco(),
            arma.getValor(),
            arma.getQtdNoEstoque(),
            arma.getTipo(),
            arma.getTipoMunicao(),
            arma.getPeso());
        }
}
