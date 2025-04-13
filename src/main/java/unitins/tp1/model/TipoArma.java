package unitins.tp1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoArma {

    REVOLVER(1, "Revolver"),
    PISTOLA(2, "Pistola"),
    ESPINGARDA(3, "Espingarda"),
    RIFLE(4, "Rifle"),
    CARABINA(5, "Carabina"),
    METRALHADORA(6, "Metralhadora"),
    ESCOPETA(7, "Escopeta"),
    FUZIL(8, "Fuzil");

    private final Integer id;
    private final String label;

    private TipoArma(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static TipoArma valueOf(Integer id) {
        for (TipoArma tipoArma : values()) {
            if (tipoArma.id == id) {
                return tipoArma;
            }
        }
        throw new IllegalArgumentException("Nenhum TipoArma encontrado com o ID: " + id);
    }

    @Override
    public String toString() {
        return "TipoArma{id=" + id + ", label='" + label + "'}";
    }

}
