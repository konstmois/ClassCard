package ru.classcard.ui.beans.util;

import ru.classcard.model.Student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@ViewScoped
@ManagedBean(name = "formatter")
public class FormatterBean {

    private static final String CARD_NUMBER_MASK = "****   ****   ****   ";
    private static final String RUBLE_SIGN = " \u20BD";

    public String getCardNumber(String number) {
        return CARD_NUMBER_MASK + number;
    }

    public String getCurrencyAmount(BigDecimal amount) {
        return getMoneyFormatter().format(amount) + RUBLE_SIGN;
    }

    private DecimalFormat getMoneyFormatter() {
        DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance();
        fmt.setGroupingSize(3);
        fmt.setMaximumFractionDigits(2);
        fmt.setMinimumFractionDigits(2);
        return fmt;
    }

    public String getStudentName(Student s) {
        return s.getLastName() + " " + s.getName();
    }

}
