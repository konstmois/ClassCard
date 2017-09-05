package ru.classcard.ui.beans.services;

import ru.classcard.model.User;
import ru.classcard.services.auth.AuthService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "authorizationService")
@SessionScoped
public class AuthorizationServiceBean implements Serializable {

    @ManagedProperty("#{authService}")
    AuthService service;

    public User authorize(String login, String password) {
        return service.authorize(login, password);
    }

    public AuthService getService() {
        return service;
    }

    public void setService(AuthService service) {
        this.service = service;
    }
}
