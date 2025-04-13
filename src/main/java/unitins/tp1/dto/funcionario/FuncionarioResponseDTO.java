package unitins.tp1.dto.funcionario;

import java.util.List;

import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.model.Funcionario;

public record FuncionarioResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String matricula,
        List<String> listaTelefones,
        List<EnderecoResponseDTO> enderecos,
        UsuarioResponseDTO usuario) {

    public static FuncionarioResponseDTO valueOf(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getMatricula(),
                funcionario.getListaTelefones().stream()
                        .map(t -> String.valueOf(t)).toList(),
                funcionario.getListaEnderecos().stream()
                        .map(e -> EnderecoResponseDTO.valueOf(e)).toList(),
                UsuarioResponseDTO.valueOf(funcionario.getUsuario()));
    }
}
