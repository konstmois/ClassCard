package ru.classcard.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import ru.classcard.dao.UserDAO;
import ru.classcard.model.User;
import ru.classcard.services.exceptions.AuthorizationException;

public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDAO dao;

    @Override
    public User authorize(String login, String password) {
        User user = dao.findUserBy(login);
        if (user != null) {
            checkUserPassword(user, password);
            return user;
        }
        throw new AuthorizationException();
    }

    private void checkUserPassword(User user, String password) {
        //TODO Hash pass
        if (!user.getPassword().equals(password)) {
            throw new AuthorizationException();
        }
    }
}
