package unitins.tp1.dto.arma;

import unitins.tp1.model.Arma;
import unitins.tp1.model.TipoArma;

public record ArmaResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    int qtdNoEstoque,
    TipoArma tipo,
    String marca,
    String acabamento,
    String calibre,
    String comprimentoDoCano,
    int capacidadeDeTiro,
    String numeroSigma,
    String numeroDaArma,
    String modelo,
    String rna,
    String nomeImagem
    ){
        public static ArmaResponseDTO valueOf(Arma arma){
            return new ArmaResponseDTO(
                arma.getId(),
                arma.getNome(),
                arma.getDescricao(),
                arma.getPreco(),
                arma.getQtdNoEstoque(),
                arma.getTipo(),
                arma.getMarca(),
                arma.getAcabamento(),
                arma.getCalibre(),
                arma.getComprimentoDoCano(),
                arma.getCapacidadeDeTiro(),
                arma.getNumeroSigma(),
                arma.getNumeroDaArma(),
                arma.getModelo(),
                arma.getRna(),
                arma.getNomeImagem());                  
    }
}
