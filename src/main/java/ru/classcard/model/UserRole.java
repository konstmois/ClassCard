package ru.classcard.model;

public enum UserRole {

    ADMIN("/admin/classList"),
    CLASS_MANAGER("/main/card"),
    CLASS_MEMBER("/main/card");

    private String startingPage;

    UserRole(String startingPage) {
        this.startingPage = startingPage;
    }

    public String getStartingPage() {
        return startingPage;
    }
}
