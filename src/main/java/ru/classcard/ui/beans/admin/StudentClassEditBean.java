package ru.classcard.ui.beans.admin;

import ru.classcard.dao.UserDAO;
import ru.classcard.model.*;
import ru.classcard.services.studentclass.StudentClassService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static ru.classcard.ui.locale.Messages.UTF8_CONTROL;

@ViewScoped
@ManagedBean(name = "classEdit")
public class StudentClassEditBean implements Serializable {

    private static final String CARD_BUNDLE = "ru.classcard.ui.locale.card";

    private StudentClassContainer entities;

    private List<SelectItem> cardTypeList;
    private boolean isEditMode;

    @ManagedProperty(value = "#{userDAO}")
    private UserDAO userDAO;

    @ManagedProperty(value = "#{classService}")
    private StudentClassService classService;

    public void createNew() {
        entities = new StudentClassContainer();
        isEditMode = false;
    }

    public void setStudentClass(StudentClass studentClass) {
        entities = new StudentClassContainer(studentClass, userDAO);
        this.isEditMode = true;
    }

    public StudentClass getStudentClass() {
        return entities != null ? entities.getStudentClass() : null;
    }

    public void save() {
        classService.save(entities);
    }

    public StudentClassContainer getEntities() {
        return entities;
    }

    public void setEntities(StudentClassContainer entities) {
        this.entities = entities;
    }

    public boolean isEdit() {
        return isEditMode;
    }

    public List<SelectItem> getCardTypeList() {
        if (cardTypeList == null) {
            cardTypeList = new ArrayList<>();
            ResourceBundle messages = getBundle(CARD_BUNDLE, getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL);
            for (CardType type : CardType.values()) {
                cardTypeList.add(new SelectItem(type, messages.getString(type.toString())));
            }
        }
        return cardTypeList;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setClassService(StudentClassService classService) {
        this.classService = classService;
    }
}
