package ru.classcard.services.access;


/**
 * Список страниц для ограничения доступа.
 */
public enum Page {

    CLASS_MEMBER_MENU("Просмотр основного меню для клиента", "/main/.*"),
    CLASS_MANAGER_MENU("Меню управляющего классом", "/classmanager/.*"),
    ADMIN_MENU("Просмотр меню администрирования классов", "/admin/.*");


    private String desc;
    private String urlPattern;

    Page(String desc, String urlPattern) {
        this.desc = desc;
        this.urlPattern = urlPattern;
    }
    public String getUrlPattern() {
        return urlPattern;
    }

}
