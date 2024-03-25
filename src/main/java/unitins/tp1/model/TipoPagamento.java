package unitins.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPagamento {

    CREDITO(1, "Credito"),
    DEBITO(2, "Debito");

    private final Integer id;
    private final String label;

    TipoPagamento(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static TipoPagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoPagamento tp : TipoPagamento.values()) {
            if (tp.getId().equals(id))
                return tp;
        }
        throw new IllegalArgumentException("Nenhum TipoPagamento encontrado com o ID: " + id);
    }
}
