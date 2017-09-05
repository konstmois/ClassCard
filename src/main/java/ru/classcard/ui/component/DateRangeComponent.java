package ru.classcard.ui.component;

import ru.classcard.types.DateRange;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@FacesComponent("dateRange")
public class DateRangeComponent extends UIInput implements NamingContainer, Serializable {


    private static final String SHOW_TIME_ATTR_NAME = "showTime";

    public DateRangeComponent() {
        if (getValue() == null) {
            setValue(new DateRange());
        }
    }

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }


    @Override
    public void decode(FacesContext context) {
        if (getAttributes() != null
            && getAttributes().containsKey(SHOW_TIME_ATTR_NAME)
            && (Boolean) getAttributes().get(SHOW_TIME_ATTR_NAME)) {

            ((DateRange) getValue()).setIgnoreTime(false);
        }
        super.decode(context);
    }

    @Override
    public Object getSubmittedValue() {
        return super.getSubmittedValue();
    }
}
