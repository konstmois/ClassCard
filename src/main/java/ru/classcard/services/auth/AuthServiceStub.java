package ru.classcard.services.auth;

import ru.classcard.model.User;

public class AuthServiceStub implements AuthService {

    @Override
    public User authorize(String login, String password) {
        User user = new User();
        user.setLogin("Login");
        user.setPassword("password");
        user.setDesc("desc");
        return user;
    }
}
