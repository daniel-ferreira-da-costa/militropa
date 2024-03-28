package unitins.tp1.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cartao extends DefaultEntity {
    
    @Column(name = "banco")
    private String banco;
    
    @Column(name = "numero")
    private String numero;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "cod_verificacao")
    private String codVerificacao;

    @Column(name = "nome_titular")
    private String nomeTitular;


    @Column(name = "tipo_cartao")
    private TipoCartao tipoCartao;

    @Column(name = "bandeira_cartao")
    private BandeiraCartao bandeiraCartao;
}