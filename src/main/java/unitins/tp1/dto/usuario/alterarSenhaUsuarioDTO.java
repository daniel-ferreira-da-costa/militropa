package unitins.tp1.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record alterarSenhaUsuarioDTO(
        @NotBlank(message = "O campo nome não pode ser nulo.")
        String senha
) {
}
