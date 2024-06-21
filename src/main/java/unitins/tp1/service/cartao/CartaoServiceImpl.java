package unitins.tp1.service.cartao;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp1.dto.cartao.CartaoDTO;
import unitins.tp1.dto.cartao.CartaoResponseDTO;
import unitins.tp1.model.BandeiraCartao;
import unitins.tp1.model.Cartao;
import unitins.tp1.model.Cliente;
import unitins.tp1.model.TipoCartao;
import unitins.tp1.repository.CartaoRepository;
import unitins.tp1.repository.ClienteRepository;

@ApplicationScoped
public class CartaoServiceImpl implements CartaoService {
    
    @Inject
    CartaoRepository repository;

    @Inject
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public CartaoResponseDTO insert(Long idCliente, CartaoDTO dto) {
        Cartao novoCartao = new Cartao();
        novoCartao.setTipoCartao(TipoCartao.valueOf(dto.idTipoCartao()));
        novoCartao.setBanco(dto.banco());
        novoCartao.setNumero(dto.numero());
        novoCartao.setCodVerificacao(dto.codVerificacao());
        novoCartao.setDataVencimento(dto.dataVencimento());
        novoCartao.setBandeiraCartao(BandeiraCartao.valueOf(dto.idBandeiraCartao()));
        novoCartao.setNomeTitular(dto.nomeTitular());
        
        repository.persist(novoCartao);

        Cliente cliente = clienteRepository.findById(idCliente);
        cliente.getListaCartoes().add(novoCartao);
        

        return CartaoResponseDTO.valueOf(novoCartao);
    }

    @Override
    @Transactional
    public CartaoResponseDTO update(CartaoDTO dto, Long id) {
        Cartao cartao = repository.findById(id);
        if(cartao != null){
        cartao.setTipoCartao(TipoCartao.valueOf(dto.idTipoCartao()));
        cartao.setBanco(dto.banco());
        cartao.setNumero(dto.numero());
        cartao.setCodVerificacao(dto.codVerificacao());
        cartao.setDataVencimento(dto.dataVencimento());
        cartao.setBandeiraCartao(BandeiraCartao.valueOf(dto.idBandeiraCartao()));
        cartao.setNomeTitular(dto.nomeTitular());
        }else
            throw new NotFoundException();

        return CartaoResponseDTO.valueOf(cartao);
    }

    @Override
    @Transactional
    public void delete(Long idCartao) {
        if (!repository.deleteById(idCartao))
        throw new NotFoundException();
    }

    @Override
    public CartaoResponseDTO findById(Long id) {
        return CartaoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<CartaoResponseDTO> findByBanco(String banco) {
        return repository.findByBanco(banco).stream()
                .map(b -> CartaoResponseDTO.valueOf(b)).toList();
    }

    @Override
    public List<CartaoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(c -> CartaoResponseDTO.valueOf(c)).toList();
    }

    @Override
    public List<CartaoResponseDTO> findByCliente(Long idCliente) {
        return repository.findByCliente(idCliente).stream()
                .map(b -> CartaoResponseDTO.valueOf(b)).toList();
    }
    
}
