package unitins.tp1.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record alterarLoginUsuarioDTO(
        @NotBlank(message = "O campo senha não pode ser nulo.")
        String senhaAtual,
        @NotBlank(message = "O campo nome não pode ser nulo.")
        String novoLogin
) {
    
}
