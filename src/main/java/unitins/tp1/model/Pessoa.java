package unitins.tp1.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pessoa extends DefaultEntity {

    @Column(length = 60)
    private String nome;

    @Column(length = 20)
    private String cpf;
    
    @Column(length = 20)
    private List<String> listaTelefones;
    
    @Column(length = 100)
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "pessoa_endereco", joinColumns = @JoinColumn(name = "id_pessoa"), inverseJoinColumns = @JoinColumn(name = "id_endereco"))
    private List<Endereco> enderecos;

    @OneToOne
    @JoinTable(name = "pessoa_usuario", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
    private Usuario usuario;

}