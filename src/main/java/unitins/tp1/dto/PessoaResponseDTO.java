package unitins.tp1.dto;

import java.util.List;

import unitins.tp1.model.Pessoa;

public record PessoaResponseDTO(
                Long id,
                String nome,
                String cpf,
                String email,
                List<String> listaTelefones,
                List<EnderecoResponseDTO> enderecos,
                UsuarioResponseDTO usuario) {

        public static PessoaResponseDTO valueOf(Pessoa pessoa) {
                return new PessoaResponseDTO(
                        pessoa.getId(),
                                pessoa.getNome(),
                                pessoa.getCpf(),
                                pessoa.getEmail(),
                                pessoa.getListaTelefones().stream()
                                .map(t -> String.valueOf(t)).toList(),
                                pessoa.getEnderecos().stream()
                                .map(e -> EnderecoResponseDTO.valueOf(e)).toList(),
                                UsuarioResponseDTO.valueOf(pessoa.getUsuario()));

        }
}
