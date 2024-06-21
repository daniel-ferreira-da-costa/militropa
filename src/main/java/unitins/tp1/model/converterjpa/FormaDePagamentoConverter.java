package unitins.tp1.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import unitins.tp1.model.FormaDePagamento;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FormaDePagamentoConverter implements AttributeConverter<FormaDePagamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FormaDePagamento tp) {
        return (tp == null ? null : tp.getId());
    }

    @Override
    public FormaDePagamento convertToEntityAttribute(Integer id) {
        return FormaDePagamento.valueOf(id);
    }

}
