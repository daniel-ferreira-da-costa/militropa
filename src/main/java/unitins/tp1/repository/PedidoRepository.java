package unitins.tp1.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp1.model.Pedido;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {   
    public List<Pedido> findByCliente (Long clienteId){
        return find("cliente.id", clienteId).list();
    }
}