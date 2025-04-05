package unitins.tp1.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;

import unitins.tp1.dto.cliente.ClientePedidoDTO;
import unitins.tp1.dto.itemPedido.ItemPedidoResponseDTO;
import unitins.tp1.model.FormaDePagamento;
import unitins.tp1.model.Pedido;
import unitins.tp1.model.Status;

public record PedidoResponseDTO(
        Long id,
        ClientePedidoDTO cliente,
        LocalDateTime dataHora,
        Double totalPedido,
        FormaDePagamento pagamento,
        Status status,
        List<ItemPedidoResponseDTO> itens


) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        List<ItemPedidoResponseDTO> lista = pedido.getItens()
                                                        .stream()
                                                                .map(ItemPedidoResponseDTO::valueOf)
                                                                        .toList();
                return new PedidoResponseDTO(
                        pedido.getId(), 
                        ClientePedidoDTO.valueOf(pedido.getCliente()),
                        pedido.getDataHora(),
                        pedido.getTotalPedido(),
                        pedido.getFormaDePagamento(),
                        pedido.getStatus(),
                        lista
                );  
    }
}
