package ru.classcard.ui.beans;

import ru.classcard.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Информация о текущем пользователе.
 */
@SessionScoped
@ManagedBean(name = "currentUser")
public class CurrentUserBean implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
