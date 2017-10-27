package ru.classcard.ui.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.ResourceBundle.*;
import static javax.faces.context.FacesContext.*;
import static ru.classcard.ui.locale.Messages.UTF8_CONTROL;


@FacesValidator("password.validator")
public class PasswordValidator implements Validator {

    private static final String BUNDLE_NAME = "ru.classcard.ui.locale.user";
    private static final String PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null && !"".equals(value.toString())) {
            Pattern pattern = Pattern.compile(PATTERN);
            Matcher matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {
                throw new ValidatorException(getValidationMessage());
            }
        }
    }

    private FacesMessage getValidationMessage() {
        FacesMessage msg = new FacesMessage(
                getBundle(BUNDLE_NAME, getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL).getString("password-rules"));
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }



}


