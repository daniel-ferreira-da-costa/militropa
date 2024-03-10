package unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;


@Entity
public class Usuario extends DefaultEntity {

    private String login;
    private String senha;

    //@Enumerated(EnumType.ORDINAL)
    private Perfil perfil;
    
    @OneToOne
    @JoinTable(name = "usuario_pessoa", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_pessoa"))
    private Pessoa dadosPessoais;

    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Pessoa getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(Pessoa dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    } 

}
