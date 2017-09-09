package ru.classcard.ui.beans;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.chart.DonutChartModel;
import ru.classcard.dao.CardDAO;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;
import ru.classcard.services.operations.ExpenseService;
import ru.classcard.ui.datamodels.CardOperationLazyModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static ru.classcard.model.OperationType.EXPENSE;
import static ru.classcard.model.OperationType.INCOME;

@ViewScoped
@ManagedBean(name = "cardBean")
public class CardBean {

    private static final String CARD_NUMBER_MASK = "****   ****   ****   ";
    private static final String RUBLE_SIGN = " \u20BD";
    private static final String EXPENSE_GRAPH_HEADER = "Расходы";
    private static final String NO_EXPENSES = "Расходы отсутствуют";

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{cardDAO}")
    private CardDAO dao;

    @ManagedProperty(value = "#{operationDAO}")
    private CardOperationDAO operationDao;

    @ManagedProperty(value = "#{expenseService}")
    private ExpenseService expenseService;

    private Card card;
    private CardOperationLazyModel incomeList;
    private CardOperationLazyModel expenseList;
    private Date expenseGraphDate = new Date();
    private DonutChartModel expenseGraphModel;

    public Card getCard() {
        if (card == null) {
            card = dao.getCardBy(currentUserBean.getUser());
        }
        return card;
    }

    public String getFormattedNumber() {
        return CARD_NUMBER_MASK + getCard().getNumber();
    }

    public String getFormattedBalance() {
        return getCard().getBalance() + RUBLE_SIGN;
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
            expenseGraphModel.addCircle(customizeExpenseMap(expenseService.getExpenseMapping(getCard(), getExpenseGraphDate())));
            expenseGraphModel.setLegendPosition("e");
            expenseGraphModel.setSliceMargin(3);
            expenseGraphModel.setShowDataLabels(true);
            expenseGraphModel.setDataFormat("value");
            expenseGraphModel.setShadow(true);
            expenseGraphModel.setTitle(new String(EXPENSE_GRAPH_HEADER.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {}
    }

    private Map<String, Number> customizeExpenseMap(Map<String, Number> expenseMap) throws UnsupportedEncodingException {
        if (expenseMap.isEmpty()) {
            expenseMap.put(new String(NO_EXPENSES.getBytes(), "UTF-8"), 1);
        }
        return expenseMap;
    }

    public void setExpenseGraphModel(DonutChartModel expenseGraphModel) {
        this.expenseGraphModel = expenseGraphModel;
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setDao(CardDAO dao) {
        this.dao = dao;
    }

    public void setOperationDao(CardOperationDAO operationDao) {
        this.operationDao = operationDao;
    }

    public void setExpenseService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


}
