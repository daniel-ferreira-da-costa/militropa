package unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ArmaDTO extends ProdutoDTO {
    @NotNull(message = "insira o tipo de arma corretamente")
    Integer tipo;
    
    @NotBlank(message = "insira o tipo de munição corretamente")
    String tipoMunicao;
    
    @NotNull(message = "insira o peso corretamente")
    double peso;
    
    @NotBlank(message = "insira o rna corretamente")
    String rna;

    public ArmaDTO(@NotBlank(message = "insira o nome corretamente") String nome,
            @NotNull(message = "insira a qtdNoEstoque corretamente") int qtdNoEstoque,
            @NotNull(message = "insira o preco corretamente") double preco,
            @NotBlank(message = "insira a descrição corretamente") String descricao,
            @NotNull(message = "insira o tipo de arma corretamente") Integer tipo,
            @NotBlank(message = "insira o tipo de munição corretamente") String tipoMunicao,
            @NotNull(message = "insira o peso corretamente") double peso,
            @NotBlank(message = "insira o rna corretamente") String rna) {
        super(nome, qtdNoEstoque, preco, descricao);
        this.tipo = tipo;
        this.tipoMunicao = tipoMunicao;
        this.peso = peso;
        this.rna = rna;
    }

}

