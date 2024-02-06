package unitins.tp1.dto;

import unitins.tp1.model.Perfil;
import unitins.tp1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String login,
    Perfil perfil,
    String telefone
) { 
    public static UsuarioResponseDTO valueOf(Usuario usuario){

        return new UsuarioResponseDTO(
            usuario.getId(), 
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getPerfil(),
            usuario.getTelefone()
        );
    }
}
