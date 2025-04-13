package unitins.tp1.dto.pedido;
import java.util.List;

import unitins.tp1.dto.itemPedido.ItemPedidoDTO;

    public record PedidoDTO (
    List<ItemPedidoDTO> itens,
    Integer idFormaDePagamento)
{ }