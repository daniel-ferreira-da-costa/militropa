package unitins.tp1.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BandeiraCartao {

    VISA(1, "Visa"),
    MASTERCARD(2, "Mastercard"),
    ELO(3, "Elo");

    private Integer id;
    private String label;

    BandeiraCartao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static BandeiraCartao valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (BandeiraCartao bc : BandeiraCartao.values()) {
            if (bc.getId().equals(id))
                return bc;
        }
        throw new IllegalArgumentException("Nenhum TipoPagamento encontrado com o ID: " + id);
    }
}