package ru.classcard.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "access")
@SessionScoped
public class AccessBean implements Serializable {

    @ManagedProperty(value="#{currentUser}")
    private CurrentUserBean currentUserBean;

    public boolean checkAccessViewMainClientPage() {
        //TODO сделать проверку по ролям
        return true;
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }
}
