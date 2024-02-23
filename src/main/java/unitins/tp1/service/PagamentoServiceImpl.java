package unitins.tp1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.tp1.dto.CartaoDTO;
import unitins.tp1.dto.PagamentoResponseDTO;
import unitins.tp1.model.FormaDePagamento;
import unitins.tp1.model.Pagamento;
import unitins.tp1.model.Pedido;
import unitins.tp1.repository.PagamentoRepository;
import unitins.tp1.repository.PedidoRepository;

import java.time.LocalDate;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService{

    @Inject
    PagamentoRepository repository;

    @Inject
    PedidoRepository pedidoRepository;
    @Override
    @Transactional
    public PagamentoResponseDTO pagarCartao(CartaoDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setConfirmacaoPagamento(true);
        pagamento.setDataConfirmacaoPagamento(LocalDate.now());
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido());
        pagamento.setPedido(pedido);
        pagamento.setFormaDePagamento(FormaDePagamento.PIX);
        pagamento.setValor(pedido.getTotalPedido());

        repository.persist(pagamento);

        return PagamentoResponseDTO.valueOf(pagamento);
    }
}
