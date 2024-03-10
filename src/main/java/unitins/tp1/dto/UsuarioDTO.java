package unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import unitins.tp1.model.Pessoa;

public record UsuarioDTO(
        @NotBlank(message = "O campo login não pode ser nulo.")
        String login,
        @NotBlank(message = "O campo senha não pode ser nulo")
        String senha,
        Integer idPerfil,
        Pessoa dadosPessoais
) {        

}