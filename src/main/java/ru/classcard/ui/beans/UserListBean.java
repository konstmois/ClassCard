package ru.classcard.ui.beans;

import ru.classcard.dao.UserDAO;
import ru.classcard.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean(name = "users")
public class UserListBean implements Serializable {

    private List<User> userList;

    @ManagedProperty(value = "#{userDAO}")
    private UserDAO userDAO;

    public List<User> getList() {
        if (userList == null) {
            userList = userDAO.getList();
        }
        return userList;
    }

    public void setList(List<User> userList) {
        this.userList = userList;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
