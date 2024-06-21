package unitins.tp1.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp1.model.Cartao;

@ApplicationScoped
public class CartaoRepository implements PanacheRepository<Cartao> {
    public List<Cartao> findByBanco(String banco) {
        return find("UPPER(banco) LIKE UPPER(?1)", "%" + banco + "%").list();
    }
        public List<Cartao> findByCliente(Long idCliente) {
        return find("Cliente.id = ?1", idCliente).list();
    }
}
