package unitins.tp1.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido extends DefaultEntity {

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<ItemPedido> itens;

    private Double totalPedido;

}
