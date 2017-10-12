package ru.classcard.ui.beans;

import ru.classcard.services.access.AccessService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

import static ru.classcard.services.access.Page.CLASS_MEMBER_MENU;
import static ru.classcard.services.access.Page.CLASS_MANAGER_MENU;
import static ru.classcard.services.access.Page.ADMIN_MENU;

@ManagedBean(name = "access")
@SessionScoped
public class AccessBean implements Serializable {

    @ManagedProperty(value="#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value="#{accessService}")
    private AccessService accessService;


    public boolean isAccessibleClassMemberMenu() {
        return accessService.checkIsAccessible(currentUserBean.getUser(), CLASS_MEMBER_MENU);
    }

    public boolean isAccessibleAdminMenu() {
        return accessService.checkIsAccessible(currentUserBean.getUser(), ADMIN_MENU);
    }

    public boolean isAccessibleClassManagerMenu() {
        return accessService.checkIsAccessible(currentUserBean.getUser(), CLASS_MANAGER_MENU);
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setAccessService(AccessService accessService) {
        this.accessService = accessService;
    }
}
