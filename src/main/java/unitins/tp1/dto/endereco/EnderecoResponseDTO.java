package unitins.tp1.dto.endereco;

import unitins.tp1.model.Endereco;

public record EnderecoResponseDTO(
                Long id,
                String nome,
                String logradouro,
                String numero,
                String complemento,
                String bairro,
                String cep,
                String cidade,
                String estado
) {
        public static EnderecoResponseDTO valueOf(Endereco endereco) {
                return new EnderecoResponseDTO(
                                endereco.getId(),
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