package unitins.tp1.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp1.model.Pedido;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findAll(String login) {
        return find("pessoa.login = ?1", login).list();
    }
    
    public List<Pedido> findAll(Long idPessoa) {
        return find("pessoa.id = ?1", idPessoa).list();
    }
}