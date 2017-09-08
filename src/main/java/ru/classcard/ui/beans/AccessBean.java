package ru.classcard.ui.beans;

import ru.classcard.services.access.AccessService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

import static ru.classcard.services.access.Page.MAIN_CLIENT_MENU;
import static ru.classcard.services.access.Page.UPLOAD_CLEINT_MENU;
import static ru.classcard.services.access.Page.USERS_ADMIN_MENU;

@ManagedBean(name = "access")
@SessionScoped
public class AccessBean implements Serializable {

    @ManagedProperty(value="#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value="#{accessService}")
    private AccessService accessService;


    public boolean isAccessibleClientMainMenu() {
        return accessService.checkIsAccessible(currentUserBean.getUser(), MAIN_CLIENT_MENU);
    }

    public boolean isAccessibleUserAdminMenu() {
        return accessService.checkIsAccessible(currentUserBean.getUser(), USERS_ADMIN_MENU);
    }

    public boolean isAccessibleClientUploadMenu() {
        return accessService.checkIsAccessible(currentUserBean.getUser(), UPLOAD_CLEINT_MENU);
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setAccessService(AccessService accessService) {
        this.accessService = accessService;
    }
}
