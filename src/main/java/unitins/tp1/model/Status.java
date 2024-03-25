package unitins.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status{

    PROCESSANDO(1, "Processando"),
    ACEITO(2, "Aceita"),
    RECUSADO(3, "Recusado"),
    EM_TRANSITO(3, "Em Transito"),
    ENTREGUE(3, "Entregue");

    private final Integer id;
    private final String label;

    Status(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
    
    public static Status valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Status s : Status.values()) {
            if (s.getId().equals(id))
                return s;
        }
        throw new IllegalArgumentException("Nenhum Status encontrado com o ID: " + id);
    }
}

