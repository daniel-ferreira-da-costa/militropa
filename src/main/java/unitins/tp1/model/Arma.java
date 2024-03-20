package unitins.tp1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
