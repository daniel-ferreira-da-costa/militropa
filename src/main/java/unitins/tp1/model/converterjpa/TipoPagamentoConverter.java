package unitins.tp1.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import unitins.tp1.model.TipoPagamento;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPagamentoConverter implements AttributeConverter<TipoPagamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoPagamento tp) {
        return (tp == null ? null : tp.getId());
    }

    @Override
    public TipoPagamento convertToEntityAttribute(Integer id) {
        return TipoPagamento.valueOf(id);
    }

}
