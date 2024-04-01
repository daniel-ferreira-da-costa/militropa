package unitins.tp1.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import unitins.tp1.model.Endereco;

public record EnderecoDTO(
        @NotBlank(message = "insira o nome corretamente")
        String nome,
        @NotBlank(message = "insira o logradouro corretamente")
        String logradouro,
        @NotBlank(message = "insira o numero corretamente")
        String numero,
        @NotBlank(message = "insira o complemento corretamente")
        String complemento,
        @NotBlank(message = "insira o bairro corretamente")
        String bairro,
        @NotBlank(message = "insira o cep corretamente")
        String cep,
        @NotBlank(message = "insira o cidade corretamente")
        String cidade,
        @NotBlank(message = "insira o estado corretamente")
        String estado) {
    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
            endereco.getNome(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getBairro(),
            endereco.getCep(),
            endereco.getCidade(),
            endereco.getEstado());
    }
}