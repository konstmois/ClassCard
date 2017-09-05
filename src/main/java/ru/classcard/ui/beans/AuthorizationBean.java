package ru.classcard.ui.beans;

import org.jboss.logging.Logger;
import ru.classcard.model.User;
import ru.classcard.services.exceptions.AuthorizationException;
import ru.classcard.ui.beans.services.AuthorizationServiceBean;
import ru.classcard.ui.locale.Messages;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

import static ru.classcard.filters.LoginFilter.USER_SESSION_ATTR;

@ViewScoped
@ManagedBean(name = "authBean")
public class AuthorizationBean implements Serializable {
    private static final String RETURN_TO_LOGIN_PAGE = "login";
    private static final String REDIRECT_AFTER_LOGOUT = "/login?faces-redirect=true";
    private static final String REDIRECT_TO_MAIN_PAGE = "main/card?faces-redirect=true";

    private String login;
    private String password;
    private User user;
    private static final Logger LOGGER = Logger.getLogger(AuthorizationBean.class);

    @ManagedProperty(value = "#{authorizationService}")
    private AuthorizationServiceBean service;

    @PostConstruct
    public void init() {
    }

    public String loginAction() {
        try {
            user = service.authorize(login, password);
            setUserToCurrentSession(user);
            return REDIRECT_TO_MAIN_PAGE;
        } catch (AuthorizationException e) {
            LOGGER.error(e);
            return accessDenied();
        } catch (Exception e) {
            LOGGER.error(e);
            return authorizationError();
        }
    }

    private String authorizationError() {
        String message = new Messages().getString("auth.error");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
        return RETURN_TO_LOGIN_PAGE;
    }

    private String accessDenied() {
        String message = new Messages().getString("access.denied");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
        return RETURN_TO_LOGIN_PAGE;
    }

    private void setUserToCurrentSession(User user) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute(USER_SESSION_ATTR, user);

        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        CurrentUserBean currentUserBean = app.evaluateExpressionGet(context, "#{currentUser}", CurrentUserBean.class);
        currentUserBean.setUser(user);
    }

    public String logout() {
        user = null;
        setUserToCurrentSession(null);
        return REDIRECT_AFTER_LOGOUT;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setService(AuthorizationServiceBean service) {
        this.service = service;
    }
}
