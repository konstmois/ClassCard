package ru.classcard.services.access;


/**
 * Список страниц для ограничения доступа.
 */
public enum Page {

    MAIN_CLIENT_MENU("Просмотр основного меню для клиента", "/main/.*"),
    UPLOAD_CLEINT_MENU("Загрузка выписки", "/upload/.*"),
    USERS_ADMIN_MENU("Просмотр меню администрирования пользователей", "/admin/.*");


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
