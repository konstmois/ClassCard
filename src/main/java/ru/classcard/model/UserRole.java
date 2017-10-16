package ru.classcard.model;

public enum UserRole {

    ADMIN("/admin/classList"),
    CLASS_MANAGER("/main/class"),
    CLASS_MEMBER("/main/class");

    private String startingPage;

    UserRole(String startingPage) {
        this.startingPage = startingPage;
    }

    public String getStartingPage() {
        return startingPage;
    }
}
