package unitins.tp1.dto;
import java.util.List;

import unitins.tp1.dto.itemPedido.ItemPedidoDTO;

    public record PedidoDTO (
    Long idCliente,
    List<ItemPedidoDTO> itens,
    Integer idFormaDePagamento)
{ }