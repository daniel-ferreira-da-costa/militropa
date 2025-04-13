package unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends DefaultEntity {

    @Column(length = 60)
    private String nome;

    @Column(length = 20)
    private String cpf;
    
    @Column(length = 100)
    private String email;
    
    @Column(length = 20)
    private String telefone;
    
    @Column(length = 10)
    private String matricula;
    
    @OneToOne
    @JoinTable(
        name = "funcionario_endereco",
        joinColumns = @JoinColumn(name = "id_funcionario"),
        inverseJoinColumns = @JoinColumn(name = "id_endereco")
    )
    private Endereco endereco;

    @OneToOne
    @JoinTable(
        name = "funcionario_usuario",
        joinColumns = @JoinColumn(name = "id_funcionario"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Usuario usuario;
}
