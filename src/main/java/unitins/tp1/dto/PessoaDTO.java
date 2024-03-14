package unitins.tp1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import unitins.tp1.model.TipoPessoa;

public record PessoaDTO(
        @NotBlank(message = "O campo nome_fantasia não pode ser nulo") 
        String nome_fantasia,
        @NotBlank(message = "O campo CPF/CNPJ não pode ficar em branco")
        String cpf_cnpj,
        @NotBlank(message = "O campo email não pode ser nulo")
        String email,
        @NotBlank(message = "O campo tipoPessoa não pode ser nulo")
        TipoPessoa tipoPessoa,
        @NotBlank(message = "O campo telefone não pode ser nulo")
        String telefone,
        @NotBlank(message = "O campo enderecos não pode ser nulo")
        List<EnderecoDTO> enderecos) {
}
