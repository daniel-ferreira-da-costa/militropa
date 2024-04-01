package unitins.tp1.dto;
import java.util.List;

import unitins.tp1.dto.endereco.EnderecoDTO;

public record PedidoDTO (
    // FormaPagamento pagamento,
    EnderecoDTO endereco,
    List<ItemPedidoDTO> itens
) {

}
