package unitins.tp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produto extends DefaultEntity{
    
    @Column(length = 60)
    private String nome;

    @Column(length = 60)
    private int qtdNoEstoque;

    //valor é qnt o produto vale
    @Column(length = 60)
    private Double valor;
    
    //preço é qnt o produto custou
    @Column(length = 60)
    private Double preco;

    @Column(length = 400)
    private String descricao;

}