package unitins.tp1.dto;

import java.time.LocalDate;

import unitins.tp1.model.Pessoa;

public record PessoaResponseDTO(
                String nome,
                String cpf,
                LocalDate dataNascimento,
                String listaTelefone,
                EnderecoResponseDTO endereco,
                UsuarioResponseDTO usuario) {
        public static PessoaResponseDTO valueOf(Pessoa pessoa) {
                return new PessoaResponseDTO(
                                pessoa.getNome(),
                                pessoa.getCpf(),
                                pessoa.getDataNascimento(),
                                pessoa.getTelefone(),
                                EnderecoResponseDTO.valueOf(pessoa.getEndereco()),
                                UsuarioResponseDTO.valueOf(pessoa.getUsuario()));
        }
}
