package unitins.tp1.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import unitins.tp1.model.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public List<Cliente> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%" + nome + "%").list();
    }

    public Cliente findByCpf(String cpf) {
        return find("UPPER(cpf) LIKE ?1", "%" + cpf.toUpperCase() + "%").firstResult();
    }

    public Cliente findByEmail(String email){
        return find("UPPER(email) LIKE ?1", "%" + email.toUpperCase() + "%").firstResult();
    }

    public Cliente findByLoginAndSenha(String login, String senha) {
        return find("usuario.login = ?1 AND usuario.senha = ?2", login, senha).firstResult();
    }

    public Cliente findByLogin(String login) {
        return find("usuario.login", login).firstResult();
    }
}
