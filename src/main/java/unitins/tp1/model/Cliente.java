package unitins.tp1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Cliente extends DefaultEntity{

    @Column(length = 60)
    private String nome;

    @Column(length = 20)
    private String cpf;
    
    @Column(length = 100)
    private String email;

    @Column(length = 10)
    private String numeroRegistro_posse_porte;

    @Column(length = 20)
    private List<String> listaTelefones;
        
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "cliente_endereco", 
        joinColumns = @JoinColumn(name = "id_cliente"), 
        inverseJoinColumns = @JoinColumn(name = "id_endereco")
    )
    private List<Endereco> enderecos;

    @OneToOne
    @JoinTable(
        name = "cliente_usuario", 
        joinColumns = @JoinColumn(name = "id_usuario"), 
        inverseJoinColumns = @JoinColumn(name = "id_cliente")
    )
    private Usuario usuario;
    
}
