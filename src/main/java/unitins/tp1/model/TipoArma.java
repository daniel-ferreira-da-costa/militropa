package unitins.tp1.model;
import lombok.Getter;

@Getter
public enum TipoArma {

    REVOLVER(1, "Revolver"),
    PISTOLA(2, "Pistola"),
    ESPINGARDA(3, "Espingarda"),
    RIFLE(4, "Rifle"),
    CARABINA(5, "Carabina"),
    METRALHADORA(6, "Metralhadora");

    private final int id;
    private final String label;

    private TipoArma(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static TipoArma fromLabel(String label) {
        for (TipoArma tipoArma : TipoArma.values()) {
            if (tipoArma.getLabel().equalsIgnoreCase(label)) {
                return tipoArma;
            }
        }
        throw new IllegalArgumentException("Id inválido" + label);
    }

    public static TipoArma fromId(int id) {
        for (TipoArma tipoArma : TipoArma.values()) {
            if (tipoArma.getId() == id) {
                return tipoArma;
            }
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}
