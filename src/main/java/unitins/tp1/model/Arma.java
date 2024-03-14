package unitins.tp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Arma extends Produto{
    @Column(name="tipo_arma")
    private TipoArma tipo;
    @Column(length = 60)
    private String tipoMunicao;
    @Column
    private Double peso;
    @Column(name="registro_nacional_armas")
    private String rna;

    //get e set
    public TipoArma getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = TipoArma.fromLabel(tipo);
    }
    public void setTipo(int tipo) {
        this.tipo = TipoArma.fromId(tipo);
    }
    public void setTipo(TipoArma tipo) {
        this.tipo = tipo;
    }
    public String getTipoMunicao() {
        return tipoMunicao;
    }
    public void setTipoMunicao(String tipoMunicao) {
        this.tipoMunicao = tipoMunicao;
    }
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public String getRna() {
        return rna;
    }
    public void setRna(String rna) {
        this.rna = rna;
    }
    
}
