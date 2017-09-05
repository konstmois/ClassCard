package ru.classcard.services.auth;


import ru.classcard.model.User;

public interface AuthService {

    User authorize(String login, String password);
}
