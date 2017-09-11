package ru.classcard.ui.converters;

import ru.classcard.model.CardType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import static ru.classcard.model.CardType.VISA;

@FacesConverter("CardTypeConverter")
public class CardTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        return value != null ? CardType.valueOf(value) : VISA;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        return value.toString();
    }
}
