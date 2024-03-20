package unitins.tp1.dto;

import unitins.tp1.model.Perfil;
import unitins.tp1.model.Usuario;

public record UsuarioResponseDTO(
        String login,
        String senha,
        Perfil perfil) {
            
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getPerfil());
    }

    public Perfil getPerfil() {
        return perfil;
    }    

}
