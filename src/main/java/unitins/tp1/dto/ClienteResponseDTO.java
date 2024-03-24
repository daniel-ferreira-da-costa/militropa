package unitins.tp1.dto;

import java.util.List;

import unitins.tp1.model.Cliente;

public record ClienteResponseDTO(
                Long id,
                String nome,
                String cpf,
                String email,
                List<String> listaTelefones,
                List<EnderecoResponseDTO> enderecos,
                UsuarioResponseDTO usuario) {

        public static ClienteResponseDTO valueOf(Cliente cliente) {
                return new ClienteResponseDTO(
                                cliente.getId(),
                                cliente.getNome(),
                                cliente.getCpf(),
                                cliente.getEmail(),
                                cliente.getListaTelefones().stream()
                                                .map(t -> String.valueOf(t)).toList(),
                                cliente.getEnderecos().stream()
                                                .map(e -> EnderecoResponseDTO.valueOf(e)).toList(),
                                UsuarioResponseDTO.valueOf(cliente.getUsuario()));

        }
}
