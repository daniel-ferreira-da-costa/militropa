package unitins.tp1.dto.cliente;

import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.model.Cliente;

public record ClienteResponseDTO(
                Long id,
                String nome,
                String cpf,
                String numeroRegistro_posse_porte,
                UsuarioResponseDTO usuario) {

        public static ClienteResponseDTO valueOf(Cliente cliente) {
                return new ClienteResponseDTO(
                                cliente.getId(),
                                cliente.getNome(),
                                cliente.getCpf(),
                                cliente.getNumeroRegistro_posse_porte(),
                                UsuarioResponseDTO.valueOf(cliente.getUsuario()));

        }
}