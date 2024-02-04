package unitins.tp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Produto extends DefaultEntity{
    
    @Column(length = 60)
    private String nome;

    @Column(length = 60)
    private int qtdNoEstoque;

    @Column(length = 60)
    private Double valor;
    
    @Column(length = 60)
    private Double preco;

    @Column(length = 400)
    private String descricao;

    //get e set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdNoEstoque() {
        return qtdNoEstoque;
    }

    public void setQtdNoEstoque(int qtdNoEstoque) {
        this.qtdNoEstoque = qtdNoEstoque;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}