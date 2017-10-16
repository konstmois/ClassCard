package ru.classcard.ui.beans.main;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.chart.DonutChartModel;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;
import ru.classcard.model.StudentClass;
import ru.classcard.services.target.TargetService;
import ru.classcard.ui.beans.CurrentUserBean;
import ru.classcard.ui.datamodels.CardOperationLazyModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static java.util.ResourceBundle.getBundle;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static ru.classcard.model.OperationType.EXPENSE;
import static ru.classcard.model.OperationType.INCOME;
import static ru.classcard.ui.locale.Messages.UTF8_CONTROL;

@ViewScoped
@ManagedBean(name = "classBean")
public class StudentClassBean {

    private static final String TARGET_BUNDLE = "ru.classcard.ui.locale.target";

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{operationDAO}")
    private CardOperationDAO operationDao;

    @ManagedProperty(value = "#{targetService}")
    private TargetService targetService;

    private Card card;
    private CardOperationLazyModel incomeList;
    private CardOperationLazyModel expenseList;
    private Date expenseGraphDate = new Date();
    private DonutChartModel expenseGraphModel;

    public Card getCard() {
        if (card == null) {
            card = getCurrentClass().getCard();
        }
        return card;
    }

    private StudentClass getCurrentClass() {
        return currentUserBean.getUser().getStudentClass();
    }

    public LazyDataModel<CardOperation> getIncomeList() {
        if (incomeList == null) {
            incomeList = new CardOperationLazyModel(getCard(), INCOME, operationDao);
        }
        return incomeList;
    }

    public LazyDataModel<CardOperation> getExpenseList() {
        if (expenseList == null) {
            expenseList = new CardOperationLazyModel(getCard(), EXPENSE, operationDao);
        }
        return expenseList;
    }

    public Date getExpenseGraphDate() {
        return expenseGraphDate;
    }

    public void setExpenseGraphDate(Date expenseGraphDate) {
        this.expenseGraphDate = expenseGraphDate;
    }

    public void decrementMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(getExpenseGraphDate());
        c.add(Calendar.MONTH, -1);
        setExpenseGraphDate(c.getTime());
        calculateExpenseGraphModel();
    }

    public void incrementMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(getExpenseGraphDate());
        c.add(Calendar.MONTH, +1);
        setExpenseGraphDate(c.getTime());
        calculateExpenseGraphModel();
    }

    public DonutChartModel getExpenseGraphModel() {
        if (expenseGraphModel == null) {
            calculateExpenseGraphModel();
        }
        return expenseGraphModel;
    }

    private void calculateExpenseGraphModel() {
        try {
            expenseGraphModel = new DonutChartModel();
            expenseGraphModel.addCircle(customizeExpenseMap(targetService.getExpenseToTargetMapping(getCard(), getExpenseGraphDate())));
            expenseGraphModel.setLegendPosition("e");
            expenseGraphModel.setSliceMargin(3);
            expenseGraphModel.setShowDataLabels(true);
            expenseGraphModel.setDataFormat("value");
            expenseGraphModel.setShadow(true);
        } catch (UnsupportedEncodingException e) {}
    }

    private Map<String, Number> customizeExpenseMap(Map<String, Number> expenseMap) throws UnsupportedEncodingException {
        if (expenseMap.isEmpty()) {
            expenseMap.put(getNoExpensesLabel(), 1);
        }
        return expenseMap;
    }

    private String getNoExpensesLabel() {
        return getBundle(TARGET_BUNDLE, getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL).getString("no.expenses");
    }

    public void setExpenseGraphModel(DonutChartModel expenseGraphModel) {
        this.expenseGraphModel = expenseGraphModel;
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setOperationDao(CardOperationDAO operationDao) {
        this.operationDao = operationDao;
    }

    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }

}
