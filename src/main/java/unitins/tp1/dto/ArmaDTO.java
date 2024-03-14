package unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import unitins.tp1.model.TipoArma;

public class ArmaDTO extends ProdutoDTO {
    @NotBlank(message = "insira o tipo de arma corretamente")
    TipoArma tipo;
    @NotBlank(message = "insira o tipo de munição corretamente")
    String tipoMunicao;
    @NotBlank(message = "insira o peso corretamente")
    Double peso;
    @NotBlank(message = "insira o peso corretamente")
    String rna;

    public ArmaDTO(@NotBlank(message = "insira o nome corretamente") String nome,
            @NotBlank(message = "insira a qtdNoEstoque corretamente") int qtdNoEstoque,
            @NotBlank(message = "insira o valor corretamente") Double valor,
            @NotBlank(message = "insira o preco corretamente") Double preco,
            @NotBlank(message = "insira a descrição corretamente") String descricao,
            @NotBlank(message = "insira o tipo de arma corretamente") TipoArma tipo,
            @NotBlank(message = "insira o tipo de munição corretamente") String tipoMunicao,
            @NotBlank(message = "insira o peso corretamente") Double peso) {
        super(nome, qtdNoEstoque, valor, preco, descricao);
        this.tipo = tipo;
        this.tipoMunicao = tipoMunicao;
        this.peso = peso;
        this.rna = rna;
    }

    public TipoArma getTipo() {
        return tipo;
    }

    public String getTipoMunicao() {
        return tipoMunicao;
    }

    public Double getPeso() {
        return peso;
    }

    public String getRna() {
        return rna;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((tipoMunicao == null) ? 0 : tipoMunicao.hashCode());
        result = prime * result + ((peso == null) ? 0 : peso.hashCode());
        result = prime * result + ((rna == null) ? 0 : rna.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArmaDTO other = (ArmaDTO) obj;
        if (tipo != other.tipo)
            return false;
        if (tipoMunicao == null) {
            if (other.tipoMunicao != null)
                return false;
        } else if (!tipoMunicao.equals(other.tipoMunicao))
            return false;
        if (peso == null) {
            if (other.peso != null)
                return false;
        } else if (!peso.equals(other.peso))
            return false;
        if (rna == null) {
            if (other.rna != null)
                return false;
        } else if (!rna.equals(other.rna))
            return false;
        return true;
    }

     

}
