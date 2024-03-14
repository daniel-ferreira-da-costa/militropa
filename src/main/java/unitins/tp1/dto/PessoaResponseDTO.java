package unitins.tp1.dto;

import java.util.List;

import unitins.tp1.model.Pessoa;
import unitins.tp1.model.TipoPessoa;

public record PessoaResponseDTO(
                String nome_fantasia,
                String cpf_cnpj,
                String email,
                TipoPessoa tipoPessoa,
                String telefone,
                List<EnderecoDTO> enderecos) {

        public static PessoaResponseDTO valueOf(Pessoa pessoa) {
                return new PessoaResponseDTO(
                                pessoa.getNome_fantasia(),
                                pessoa.getCpf_cnpj(),
                                pessoa.getEmail(),
                                pessoa.getTipoPessoa(),
                                pessoa.getTelefone(),
                                pessoa.getEnderecos()
                                        .stream()
                                                .map(e -> EnderecoDTO.valueOf(e)).toList());

        }
}
