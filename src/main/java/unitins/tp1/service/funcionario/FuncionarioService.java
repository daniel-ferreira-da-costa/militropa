package unitins.tp1.service.funcionario;

import java.util.List;

import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.dto.funcionario.FuncionarioDTO;
import unitins.tp1.dto.funcionario.FuncionarioResponseDTO;


public interface FuncionarioService {
    
    public FuncionarioResponseDTO insert(FuncionarioDTO dto);

    public FuncionarioResponseDTO update(FuncionarioDTO dto, Long id);
    public EnderecoResponseDTO insetEndereco(EnderecoDTO dto, Long id);
    public String insetTelefone(String telefone, Long id);

    public void delete(Long id);

    public FuncionarioResponseDTO findById(Long id);

    public List<FuncionarioResponseDTO> findByNome(String nome);

    public List<FuncionarioResponseDTO> findByAll(); 

    public FuncionarioResponseDTO findByMatricula(String matricula);
}
