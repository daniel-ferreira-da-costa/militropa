package unitins.tp1.dto.cartao;


import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CartaoDTO(
    @NotBlank(message = "O campo banco não pode estar em branco")
    String banco,
    
    @NotBlank(message = "O campo número não pode estar em branco")
    @Pattern(regexp = "\\d{16}", message = "O número do cartão deve conter exatamente 16 dígitos")
    String numero,

    @NotNull(message = "O campo data de vencimento não pode ser nulo")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "O formato da data de vencimento deve ser YYYY-MM-DD")
    LocalDate dataVencimento,
    
    @NotBlank(message = "O campo código de verificação não pode estar em branco")
    @Pattern(regexp = "\\d{3}", message = "O código de verificação deve conter exatamente 3 dígitos")
    String codVerificacao,
    
    @NotBlank(message = "O campo nome do titular não pode estar em branco")
    String nomeTitular,
    
    Integer idTipoCartao,
    Integer idBandeiraCartao
) {}