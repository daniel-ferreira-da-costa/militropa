package unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import unitins.tp1.model.TipoArma;

public class ArmaDTO {
    @NotBlank(message = "insira o nome corretamente")
    String nome;
    @NotBlank(message = "insira a qtdNoEstoque corretamente")
    int qtdNoEstoque;
    @NotBlank(message = "insira o valor corretamente")
    Double valor;
    @NotBlank(message = "insira o preco corretamente")
    Double preco;
    @NotBlank(message = "insira a descrição corretamente")
    String descricao;
    @NotBlank(message = "insira o tipo de arma corretamente")
    TipoArma tipo;
    @NotBlank(message = "insira o tipo de munição corretamente")
    String tipoMunicao;
    @NotBlank(message = "insira o peso corretamente")
    Double peso;

    public ArmaDTO(@NotBlank(message = "insira o nome corretamente") String nome,
            @NotBlank(message = "insira a qtdNoEstoque corretamente") int qtdNoEstoque,
            @NotBlank(message = "insira o valor corretamente") Double valor,
            @NotBlank(message = "insira o preco corretamente") Double preco,
            @NotBlank(message = "insira a descrição corretamente") String descricao,
            @NotBlank(message = "insira o tipo de arma corretamente") TipoArma tipo,
            @NotBlank(message = "insira o tipo de munição corretamente") String tipoMunicao,
            @NotBlank(message = "insira o peso corretamente") Double peso) {
        this.nome = nome;
        this.qtdNoEstoque = qtdNoEstoque;
        this.valor = valor;
        this.preco = preco;
        this.descricao = descricao;
        this.tipo = tipo;
        this.tipoMunicao = tipoMunicao;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdNoEstoque() {
        return qtdNoEstoque;
    }

    public Double getValor() {
        return valor;
    }

    public Double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + qtdNoEstoque;
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        result = prime * result + ((preco == null) ? 0 : preco.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((tipoMunicao == null) ? 0 : tipoMunicao.hashCode());
        result = prime * result + ((peso == null) ? 0 : peso.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArmaDTO other = (ArmaDTO) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (qtdNoEstoque != other.qtdNoEstoque)
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        if (preco == null) {
            if (other.preco != null)
                return false;
        } else if (!preco.equals(other.preco))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
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
        return true;
    }

    

}
