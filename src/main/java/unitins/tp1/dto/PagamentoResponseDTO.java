package unitins.tp1.dto;

import java.time.LocalDate;

import unitins.tp1.model.FormaDePagamento;
import unitins.tp1.model.Pagamento;

public record PagamentoResponseDTO(
        Double valor,
        Boolean confirmacaoPagamento,
        LocalDate dataConfirmacaoPagamento,

        FormaDePagamento formaDePagamento,
        PedidoResponseDTO pedidoResponseDTO
) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento){
        return new PagamentoResponseDTO(
                pagamento.getValor(),
                pagamento.getConfirmacaoPagamento(),
                pagamento.getDataConfirmacaoPagamento(),
                pagamento.getFormaDePagamento(),
                PedidoResponseDTO.valueOf(pagamento.getPedido())
        );
    }
}
