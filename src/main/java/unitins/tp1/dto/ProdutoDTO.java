package unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProdutoDTO{
    @NotBlank(message = "insira o nome corretamente")
    String nome;
    @NotBlank(message = "insira a qtdNoEstoque corretamente")
    int qtdNoEstoque;
    @NotBlank(message = "insira o preco corretamente")
    Double preco;
    @NotBlank(message = "insira a descrição corretamente")
    String descricao;

    public ProdutoDTO(@NotBlank(message = "insira o nome corretamente") String nome,
            @NotBlank(message = "insira a qtdNoEstoque corretamente") int qtdNoEstoque,
            @NotBlank(message = "insira o preco corretamente") double preco,
            @NotBlank(message = "insira a descrição corretamente") String descricao) {
        this.nome = nome;
        this.qtdNoEstoque = qtdNoEstoque;
        this.preco = preco;
        this.descricao = descricao;
    }
}
