package ru.classcard.ui.beans.manager;

import org.primefaces.model.LazyDataModel;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.dao.StudentDAO;
import ru.classcard.dao.TargetDAO;
import ru.classcard.model.*;
import ru.classcard.ui.beans.CurrentUserBean;
import ru.classcard.ui.datamodels.CardOperationLazyModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

import static ru.classcard.model.OperationType.EXPENSE;
import static ru.classcard.model.OperationType.INCOME;

@ViewScoped
@ManagedBean(name = "classMgmt")
public class StudentClassManageBean {

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{operationDAO}")
    private CardOperationDAO operationDao;

    @ManagedProperty(value = "#{studentDAO}")
    private StudentDAO studentDao;

    @ManagedProperty(value = "#{targetDAO}")
    private TargetDAO targetDao;

    private Card card;
    private CardOperationLazyModel incomeList;
    private CardOperationLazyModel expenseList;

    public StudentClass getCurrentClass() {
        return currentUserBean.getUser().getStudentClass();
    }

    public Card getCard() {
        if (card == null) {
            card = getCurrentClass().getCard();
        }
        return card;
    }

    public List<Student> getStudentList() {
        return studentDao.findBy(getCurrentClass());
    }

    public List<Target> getTargetList() {
        return targetDao.findBy(getCurrentClass());
    }

    public List<Target> getActiveTargetList() {
        return targetDao.findActiveBy(getCurrentClass());
    }

    public LazyDataModel<CardOperation> getIncomeList() {
        if (incomeList == null) {
            incomeList = new CardOperationLazyModel(getCard(), INCOME, operationDao);
        }
        return incomeList;
    }

    public void updateCardOperation(CardOperation op) {
        operationDao.save(op);
    }

    public LazyDataModel<CardOperation> getExpenseList() {
        if (expenseList == null) {
            expenseList = new CardOperationLazyModel(getCard(), EXPENSE, operationDao);
        }
        return expenseList;
    }


    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setOperationDao(CardOperationDAO operationDao) {
        this.operationDao = operationDao;
    }

    public void setStudentDao(StudentDAO studentDao) {
        this.studentDao = studentDao;
    }

    public void setTargetDao(TargetDAO targetDao) {
        this.targetDao = targetDao;
    }
}
