package unitins.tp1.dto.funcionario;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import unitins.tp1.dto.endereco.EnderecoDTO;


public record FuncionarioDTO(
        @NotBlank(message = "O campo nome não pode ficar em branco") 
        String nome,
        @NotBlank(message = "O campo 'CPF' não pode estar em branco")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
        String cpf,
        @NotBlank(message = "O campo 'e-mail' não pode estar em branco")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido")
        String email,
        @NotNull(message = "O campo telefone não pode ficar em branco")
        List<String> listaTelefones,
        @NotNull(message = "O campo enderecos não pode ficar em branco")
        List<EnderecoDTO> listaEnderecos,
        @NotBlank(message = "O campo 'matrícula' não pode estar em branco")
        String matricula,
        @NotNull(message = "O campo senha não pode ficar em branco")
        String senha) {
}