package unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import unitins.tp1.model.TipoArma;

@Getter
@Setter
@EqualsAndHashCode
public class ArmaDTO extends ProdutoDTO {
    @NotBlank(message = "insira o tipo de arma corretamente")
    TipoArma tipo;
    @NotBlank(message = "insira o tipo de munição corretamente")
    String tipoMunicao;
    @NotBlank(message = "insira o peso corretamente")
    Double peso;
    @NotBlank(message = "insira o rna corretamente")
    String rna;

    public ArmaDTO(@NotBlank(message = "insira o nome corretamente") String nome,
            @NotBlank(message = "insira a qtdNoEstoque corretamente") int qtdNoEstoque,
            @NotBlank(message = "insira o preco corretamente") double preco,
            @NotBlank(message = "insira a descrição corretamente") String descricao,
            @NotBlank(message = "insira o tipo de arma corretamente") TipoArma tipo,
            @NotBlank(message = "insira o tipo de munição corretamente") String tipoMunicao,
            @NotBlank(message = "insira o peso corretamente") Double peso,
            @NotBlank(message = "insira o rna corretamente") String rna) {
        super(nome, qtdNoEstoque, preco, descricao);
        this.tipo = tipo;
        this.tipoMunicao = tipoMunicao;
        this.peso = peso;
        this.rna = rna;
    }
}
