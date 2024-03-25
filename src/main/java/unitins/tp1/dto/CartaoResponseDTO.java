package unitins.tp1.dto;

import java.util.Date;

import unitins.tp1.model.BandeiraCartao;
import unitins.tp1.model.Cartao;
import unitins.tp1.model.TipoCartao;

public record CartaoResponseDTO(
        Long id,
        String numero,
        Date dataVencimento,
        String codVerificacao,
        String nomeTitular,
        TipoCartao tipoCartao,
        BandeiraCartao bandeiraCartao) {
    public static CartaoResponseDTO valueOf(Cartao cartao) {
        return new CartaoResponseDTO(
                cartao.getId(),
                cartao.getNumero(),
                cartao.getDataVencimento(),
                cartao.getCodVerificacao(),
                cartao.getNomeTitular(),
                cartao.getTipoCartao(),
                cartao.getBandeiraCartao());
    }
}
