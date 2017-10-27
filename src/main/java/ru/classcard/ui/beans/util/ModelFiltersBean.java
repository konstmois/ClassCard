package ru.classcard.ui.beans.util;

import ru.classcard.comparator.SelectItemComparator;
import ru.classcard.dao.StudentDAO;
import ru.classcard.dao.TargetDAO;
import ru.classcard.model.StudentClass;
import ru.classcard.ui.beans.CurrentUserBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;
import static java.util.stream.Collectors.toList;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static ru.classcard.dao.AbstractEntityDAOImpl.ENTITY_FIELD_IS_NULL_FILTER;
import static ru.classcard.ui.locale.Messages.UTF8_CONTROL;

@ViewScoped
@ManagedBean(name = "modelFilters")
public class ModelFiltersBean {

    private static final String MESSAGES_BUNDLE = "ru.classcard.ui.locale.messages";

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{studentDAO}")
    private StudentDAO studentDao;

    @ManagedProperty(value = "#{targetDAO}")
    private TargetDAO targetDao;

    private List<SelectItem> studentFilterList;
    private List<SelectItem> targetFilterList;

    public List<SelectItem> getStudentFilterItems() {
        if (studentFilterList == null) {
            studentFilterList = studentDao.findBy(getCurrentClass())
                    .stream()
                    .map(s -> new SelectItem(s.getId(), s.getLastName() + " " + s.getName()))
                    .collect(toList());
            studentFilterList.sort(new SelectItemComparator());
            studentFilterList.add(getSelectItemForNullFieldValue());
        }
        return studentFilterList;
    }

    public List<SelectItem> getTargetFilterItems() {
        if (targetFilterList == null) {
            targetFilterList = targetDao.findActiveBy(getCurrentClass())
                    .stream()
                    .map(s -> new SelectItem(s.getId(), s.getName()))
                    .collect(toList());
            targetFilterList.add(getSelectItemForNullFieldValue());
        }
        return targetFilterList;
    }

    private SelectItem getSelectItemForNullFieldValue() {
        ResourceBundle messages = getBundle(MESSAGES_BUNDLE, getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL);
        return new SelectItem(ENTITY_FIELD_IS_NULL_FILTER, messages.getString("field.is.null"));
    }

    private StudentClass getCurrentClass() {
        return currentUserBean.getUser().getStudentClass();
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setStudentDao(StudentDAO studentDao) {
        this.studentDao = studentDao;
    }

    public void setTargetDao(TargetDAO targetDao) {
        this.targetDao = targetDao;
    }
}
