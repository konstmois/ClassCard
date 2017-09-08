package ru.classcard.ui.beans;

import ru.classcard.model.Card;
import ru.classcard.model.User;
import ru.classcard.model.UserRole;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javax.faces.context.FacesContext.getCurrentInstance;
import static ru.classcard.ui.locale.Messages.UTF8_CONTROL;

@ViewScoped
@ManagedBean(name = "userEdit")
public class UserEditBean implements Serializable {

    private User user;
    private Card card;
    private List<SelectItem> userRoleList;
    private boolean isEditMode;

    public void initEdit(User user) {
        this.user = user;
        isEditMode = true;
    }

    public void initNew() {
        user = new User();
        card = new Card();
        isEditMode = false;
    }

    public void save() {
        System.out.println("SAVEEEEEEE");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isEdit() {
        return isEditMode;
    }

    public List<SelectItem> getUserRoleList() {
        if (userRoleList == null) {
            userRoleList = new ArrayList<>();
            ResourceBundle messages = ResourceBundle.getBundle("ru.classcard.ui.locale.users", getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL);

            for (UserRole role : UserRole.values()) {
                userRoleList.add(new SelectItem(role, messages.getString(role.toString())));
            }
        }
        return userRoleList;
    }

}
