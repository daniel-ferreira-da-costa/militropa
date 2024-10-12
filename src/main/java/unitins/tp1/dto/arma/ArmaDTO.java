package unitins.tp1.dto.arma;

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
    int tipo;
    @NotBlank(message = "insira o 'marca' corretamente")
    String marca;
    @NotBlank(message = "insira o 'acabamento' corretamente")
    String acabamento;
    @NotBlank(message = "insira o 'calibre' corretamente")
    String calibre;
    @NotBlank(message = "insira o 'comprimento do cano' corretamente")
    String comprimentoDoCano;
    @NotNull(message = "insira o 'capacidade de tiro' corretamente")
    int capacidadeDeTiro;
    //@NotBlank(message = "insira o 'nomero sigma' corretamente")
    String numeroSigma;
    @NotBlank(message = "insira o 'numero da arma' corretamente")
    String numeroDaArma;
    @NotBlank(message = "insira o 'modelo' corretamente")
    String modelo;
    String rna;

    public ArmaDTO(@NotBlank(message = "insira o nome corretamente") String nome,
            @NotNull(message = "insira a qtdNoEstoque corretamente") int qtdNoEstoque,
            @NotNull(message = "insira o preco corretamente") double preco,
            @NotBlank(message = "insira a descrição corretamente") String descricao,
            @NotNull(message = "insira o tipo de arma corretamente") int tipo,
            @NotBlank(message = "insira o 'marca' corretamente") String marca,
            @NotBlank(message = "insira o 'acabamento' corretamente") String acabamento,
            @NotBlank(message = "insira o 'calibre' corretamente") String calibre,
            @NotBlank(message = "insira o 'comprimento do cano' corretamente") String comprimentoDoCano,
            @NotNull(message = "insira o 'capacidade de tiro' corretamente") int capacidadeDeTiro,
           // @NotBlank(message = "insira o 'nomero sigma' corretamente") String numeroSigma,
            @NotBlank(message = "insira o 'numero da arma' corretamente") String numeroDaArma,
            @NotBlank(message = "insira o 'modelo' corretamente") String modelo, String rna) {
        super(nome, qtdNoEstoque, preco, descricao);
        this.tipo = tipo;
        this.marca = marca;
        this.acabamento = acabamento;
        this.calibre = calibre;
        this.comprimentoDoCano = comprimentoDoCano;
        this.capacidadeDeTiro = capacidadeDeTiro;
        this.numeroSigma = numeroSigma;
        this.numeroDaArma = numeroDaArma;
        this.modelo = modelo;
        this.rna = rna;
    }

    
}

