package unitins.tp1.model;
import lombok.Getter;

@Getter
public enum BandeiraCartao {

    VISA(1, "Visa"),
    MASTERCARD(2, "Mastercard"),
    ELO(3, "Elo");

    private int id;
    private String label;

    BandeiraCartao(int id, String label) {
        this.id = id;
        this.label = label;
    }

}