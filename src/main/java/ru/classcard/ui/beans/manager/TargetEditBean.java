package ru.classcard.ui.beans.manager;

import ru.classcard.dao.TargetDAO;
import ru.classcard.model.StudentClass;
import ru.classcard.model.Target;
import ru.classcard.ui.beans.CurrentUserBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "targetEdit")
public class TargetEditBean {

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{targetDAO}")
    private TargetDAO dao;

    private Target target;

    public Target getTarget() {
        return target;
    }

    public void createNew() {
        target = new Target();
        target.setStudentClass(getCurrentClass());
        target.setActive(true);
    }

    public void save() {
        dao.save(target);
    }

    private StudentClass getCurrentClass() {
        return currentUserBean.getUser().getStudentClass();
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setDao(TargetDAO dao) {
        this.dao = dao;
    }
}
