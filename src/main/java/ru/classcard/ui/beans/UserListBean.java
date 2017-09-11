package ru.classcard.ui.beans;

import org.primefaces.model.LazyDataModel;
import ru.classcard.dao.UserDAO;
import ru.classcard.model.User;
import ru.classcard.ui.datamodels.UserLazyModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ViewScoped
@ManagedBean(name = "users")
public class UserListBean implements Serializable {

    private LazyDataModel<User> userList;

    @ManagedProperty(value = "#{userDAO}")
    private UserDAO userDAO;

    public LazyDataModel<User> getList() {
        if (userList == null) {
            userList = new UserLazyModel(userDAO);
        }
        return userList;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
