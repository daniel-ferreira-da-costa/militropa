package unitins.tp1.dto;

import unitins.tp1.model.Perfil;
import unitins.tp1.model.Pessoa;
import unitins.tp1.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String senha,
        Perfil perfil,
        Pessoa dadosPessoais) {
            
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getPerfil(),
                usuario.getDadosPessoais());
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Long getId() {
        return id;
    }
    

}
