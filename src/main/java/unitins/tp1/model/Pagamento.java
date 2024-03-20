package unitins.tp1.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pagamento extends DefaultEntity{
    
    private Double valor;
    private Boolean confirmacaoPagamento;
    private FormaDePagamento formaDePagamento;

    private LocalDate dataConfirmacaoPagamento;

    @OneToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Pagamento(Double valorTotal) {
        this.valor = valorTotal;
        this.confirmacaoPagamento = true;
        this.dataConfirmacaoPagamento = LocalDate.now();
    }
    
    public Pagamento(){}
}