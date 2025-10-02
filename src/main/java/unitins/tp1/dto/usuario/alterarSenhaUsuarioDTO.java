package unitins.tp1.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record alterarSenhaUsuarioDTO(
        @NotBlank(message = "O campo nome não pode ser nulo.")
        String senhaAntiga,
        @NotBlank(message = "O campo senha não pode ser nulo.")
        String novaSenha,
        @NotBlank(message = "O campo confirmação de senha não pode ser nulo.")
        String confirmacaoSenhaNova
) {
}
