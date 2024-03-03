package unitins.tp1.model;

public class Arma extends Produto{
    private TipoArma tipo;
    private String tipoMunicao;
    private Double peso;

    //get e set
    public TipoArma getTipo() {
        return tipo;
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
}
