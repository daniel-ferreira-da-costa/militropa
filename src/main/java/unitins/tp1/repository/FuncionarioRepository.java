package unitins.tp1.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp1.model.Funcionario;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
    public List<Funcionario> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%" + nome + "%").list();
    }

    public List<Funcionario> findByMatricula(String matricula) {
        return find("UPPER(matricula) LIKE UPPER(?1) ", "%" + matricula + "%").list();
    }
}
