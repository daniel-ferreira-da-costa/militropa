package unitins.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FormaDePagamento {

    DEBITO(1, "Débito"),
    CREDITO(2, "Crédito"),
    PIX(3, "Pix");
    private final Integer id;
    private final String label;

    private FormaDePagamento(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
    
    public static FormaDePagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (FormaDePagamento tipoDePagamento : FormaDePagamento.values()) {
            if (tipoDePagamento.getId().equals(id))
                return tipoDePagamento;
        }

        throw new IllegalArgumentException("Id inválida" + id);
    }
}
