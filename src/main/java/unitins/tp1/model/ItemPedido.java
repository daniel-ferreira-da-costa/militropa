package unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemPedido extends DefaultEntity {

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_arma")
    private Arma arma;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

}