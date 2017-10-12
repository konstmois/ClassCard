package ru.classcard.ui.beans;

import ru.classcard.comparator.TargetIncomeComparator;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.dao.StudentDAO;
import ru.classcard.dao.TargetDAO;
import ru.classcard.model.StudentClass;
import ru.classcard.model.Target;
import ru.classcard.model.TargetIncome;
import ru.classcard.services.target.TargetService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.util.List;

@ViewScoped
@ManagedBean(name = "targets")
public class TargetListBean {

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{operationDAO}")
    private CardOperationDAO operationDao;

    @ManagedProperty(value = "#{studentDAO}")
    private StudentDAO studentDao;

    @ManagedProperty(value = "#{targetService}")
    private TargetService targetService;

    @ManagedProperty(value = "#{targetDAO}")
    private TargetDAO targetDao;

    private List<Target> targetList;

    public List<Target> getList() {
        if (targetList == null) {
            targetList = targetDao.findBy(getCurrentClass());
        }
        return targetList;
    }

    public List<TargetIncome> getTargetIncomeList(Target target) {
        List<TargetIncome> incomeByStudent = targetService.getIncomeGroupedByStudent(target);
        incomeByStudent.sort(new TargetIncomeComparator());
        return incomeByStudent;
    }

    public BigDecimal calcExpense(Target target) {
        return targetService.calcExpense(target);
    }

    public BigDecimal calcIncome(Target target) {
        return targetService.calcIncome(target);
    }

    private StudentClass getCurrentClass() {
        return currentUserBean.getUser().getStudentClass();
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

    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }
}
