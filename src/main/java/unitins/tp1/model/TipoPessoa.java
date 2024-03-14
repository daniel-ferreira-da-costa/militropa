package unitins.tp1.model;

public enum TipoPessoa {
    PESSOA_FISICA(1, "Pessoa Fisica"),
    PESSOA_JURIDICA(2, "Pessoa Juridica");

    private final Integer id;
    private final String label;
    
    private TipoPessoa(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

       
    public static TipoPessoa valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (TipoPessoa t : TipoPessoa.values()) {
            if (t.getId().equals(id))
                return t;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }


}
