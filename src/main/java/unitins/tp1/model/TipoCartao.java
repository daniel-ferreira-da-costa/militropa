package unitins.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCartao {
    CREDITO(1, "Credito"),
    DEBITO(2, "Debito"),
    CREDITO_E_DEBITO(3, "Credito e Debito");

    private final Integer id;
    private final String label;

    private TipoCartao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static TipoCartao valueOf(Integer id) {
        for (TipoCartao tc : values()) {
            if (tc.id == id) {
                return tc;
            }
        }
        throw new IllegalArgumentException("Nenhum TipoCartao encontrado com o ID: " + id);
    }
}
