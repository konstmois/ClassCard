package ru.classcard.ui.beans;

import ru.classcard.dao.CardDAO;
import ru.classcard.model.Card;
import ru.classcard.model.CardType;
import ru.classcard.model.User;
import ru.classcard.model.UserRole;
import ru.classcard.services.user.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.*;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static ru.classcard.model.UserRole.ADMIN;
import static ru.classcard.ui.locale.Messages.UTF8_CONTROL;

@ViewScoped
@ManagedBean(name = "userEdit")
public class UserEditBean implements Serializable {

    private static final String CARD_BUNDLE = "ru.classcard.ui.locale.card";
    private static final String USERS_BUNDLE = "ru.classcard.ui.locale.users";
    private User user;
    private Card card;
    private List<SelectItem> userRoleList;
    private List<SelectItem> cardTypeList;
    private boolean isEditMode;
    private boolean isAdmin;

    @ManagedProperty(value = "#{cardDAO}")
    private CardDAO cardDAO;

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    public void initNew() {
        user = new User();
        card = new Card();
        isEditMode = false;
        isAdmin = true;
    }

    public void setUser(User user) {
        this.isEditMode = true;
        this.user = user;
        this.card = cardDAO.getCardBy(user);
        isAdmin = isUserAdmin(user);
    }

    public void save() {
       userService.save(user, card);
    }

    private boolean isUserAdmin(User user) {
        return ADMIN.equals(user.getRole());
    }

    public void userRoleChanged() {
       isAdmin = isUserAdmin(user);
    }

    public User getUser() {
        return user;
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
            ResourceBundle messages = getBundle(USERS_BUNDLE, getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL);
            for (UserRole role : UserRole.values()) {
                userRoleList.add(new SelectItem(role, messages.getString(role.toString())));
            }
        }
        return userRoleList;
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

    public boolean isAdmin(){
        return isAdmin;
    }

    public void setCardDAO(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
