package unitins.tp1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record PessoaDTO(
        @NotBlank(message = "O campo nome_fantasia não pode ficar em branco") 
        String nome,
        @NotBlank(message = "O campo CPF/CNPJ não pode ficar em branco")
        String cpf,
        @NotBlank(message = "O campo email não pode ficar em branco")
        String email,
        @NotBlank(message = "O campo telefone não pode ficar em branco")
        List<String> listaTelefones,
        @NotBlank(message = "O campo enderecos não pode ficar em branco")
        List<EnderecoDTO> enderecos,
        @NotBlank(message = "O campo usuario não pode ficar em branco")
        UsuarioDTO usuario) {

        public String nome() {
                return nome;
        }

        public String cpf() {
                return cpf;
        }

        public String email() {
                return email;
        }

        public List<String> listaTelefones() {
                return listaTelefones;
        }

        public List<EnderecoDTO> enderecos() {
                return enderecos;
        }

        public UsuarioDTO usuario() {
                return usuario;
        }

                
}
