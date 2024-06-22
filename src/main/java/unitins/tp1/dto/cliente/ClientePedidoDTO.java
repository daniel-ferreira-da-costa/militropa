package unitins.tp1.dto.cliente;

import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.model.Cliente;

public record ClientePedidoDTO(
                Long id,
                String nome,
                String cpf,
                String numeroRegistro_posse_porte,
                UsuarioResponseDTO usuario) {

        public static ClientePedidoDTO valueOf(Cliente cliente) {
                return new ClientePedidoDTO(
                                cliente.getId(),
                                cliente.getNome(),
                                cliente.getCpf(),
                                cliente.getNumeroRegistro_posse_porte(),
                                UsuarioResponseDTO.valueOf(cliente.getUsuario()));

        }
}