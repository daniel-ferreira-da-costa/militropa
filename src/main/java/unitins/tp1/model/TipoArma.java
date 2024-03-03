package unitins.tp1.model;

public enum TipoArma {
    
    REVOLVER(1, "Revolver"),
    PISTOLA(2, "Pistola"),
    ESPINGARDA(3, "Espingarda"),
    RIFLE(4, "Rifle"),
    CARABINA(5, "Carabina"),
    METRALHADORA(6, "Metralhadora");

    private final Integer id;
    private final String label;
    
    private TipoArma(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoArma valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoArma tipoArma : TipoArma.values()) {
            if (tipoArma.getId().equals(id))
                return tipoArma;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}
