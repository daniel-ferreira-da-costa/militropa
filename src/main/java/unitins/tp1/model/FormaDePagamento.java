package unitins.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FormaDePagamento {

    DEBITO(1, "Débito"),
    CREDITO(2, "Crédito");

    private final Integer id;
    private final String label;

    private FormaDePagamento(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
    
    public static FormaDePagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (FormaDePagamento fp : FormaDePagamento.values()) {
            if (fp.getId().equals(id))
                return fp;
        }

        throw new IllegalArgumentException("Id inválida" + id);
    }
}
