package ru.classcard.ui.beans;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.chart.DonutChartModel;
import ru.classcard.comparator.SelectItemComparator;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.dao.StudentDAO;
import ru.classcard.dao.TargetDAO;
import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;
import ru.classcard.model.StudentClass;
import ru.classcard.services.operations.ExpenseService;
import ru.classcard.ui.datamodels.CardOperationLazyModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static ru.classcard.model.OperationType.EXPENSE;
import static ru.classcard.model.OperationType.INCOME;

@ViewScoped
@ManagedBean(name = "cardBean")
public class CardBean {

    private static final String NO_EXPENSES = "Расходы отсутствуют";

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{operationDAO}")
    private CardOperationDAO operationDao;

    @ManagedProperty(value = "#{studentDAO}")
    private StudentDAO studentDao;

    @ManagedProperty(value = "#{targetDAO}")
    private TargetDAO targetDao;

    @ManagedProperty(value = "#{expenseService}")
    private ExpenseService expenseService;

    private Card card;
    private CardOperationLazyModel incomeList;
    private CardOperationLazyModel expenseList;
    private Date expenseGraphDate = new Date();
    private DonutChartModel expenseGraphModel;
    private List<SelectItem> studentFilterList;
    private List<SelectItem> targetFilterList;

    public Card getCard() {
        if (card == null) {
            card = getCurrentClass().getCard();
        }
        return card;
    }

    public List<SelectItem> getStudentFilterItems() {
        if (studentFilterList == null) {
            studentFilterList = studentDao.findBy(getCurrentClass())
                    .stream()
                    .map(s -> new SelectItem(s.getId(), s.getLastName() + " " + s.getName()))
                    .collect(toList());
            studentFilterList.sort(new SelectItemComparator());
        }
        return studentFilterList;
    }

    public List<SelectItem> getTargetFilterItems() {
        if (targetFilterList == null) {
            targetFilterList = targetDao.findBy(getCurrentClass())
                    .stream()
                    .map(s -> new SelectItem(s.getId(), s.getName()))
                    .collect(toList());
        }
        return targetFilterList;
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
            expenseGraphModel.addCircle(customizeExpenseMap(expenseService.getExpenseMapping(getCard(), getExpenseGraphDate())));
            expenseGraphModel.setLegendPosition("e");
            expenseGraphModel.setSliceMargin(3);
            expenseGraphModel.setShowDataLabels(true);
            expenseGraphModel.setDataFormat("value");
            expenseGraphModel.setShadow(true);
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

    public void setOperationDao(CardOperationDAO operationDao) {
        this.operationDao = operationDao;
    }

    public void setExpenseService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public void setStudentDao(StudentDAO studentDao) {
        this.studentDao = studentDao;
    }

    public void setTargetDao(TargetDAO targetDao) {
        this.targetDao = targetDao;
    }
}
