package unitins.tp1.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Pagamento extends DefaultEntity{
    
    @Column()
    private Double valor;

    @Column()
    private Boolean confirmacaoPagamento;

    @Column
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

    public Pagamento() {

    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getConfirmacaoPagamento() {
        return confirmacaoPagamento;
    }

    public void setConfirmacaoPagamento(Boolean confirmacaoPagamento) {
        this.confirmacaoPagamento = confirmacaoPagamento;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public LocalDate getDataConfirmacaoPagamento() {
        return dataConfirmacaoPagamento;
    }

    public void setDataConfirmacaoPagamento(LocalDate dataConfirmacaoPagamento) {
        this.dataConfirmacaoPagamento = dataConfirmacaoPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}