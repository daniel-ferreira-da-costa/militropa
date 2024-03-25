package unitins.tp1.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import unitins.tp1.model.TipoArma;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoArmaConverter implements AttributeConverter<TipoArma, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoArma ta) {
        return (ta == null ? null : ta.getId());
    }

    @Override
    public TipoArma convertToEntityAttribute(Integer id) {
        return TipoArma.valueOf(id);
    }
    
}
