package ru.classcard.services.auth;

import org.apache.myfaces.shade.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.classcard.dao.UserDAO;
import ru.classcard.model.User;
import ru.classcard.services.exceptions.AuthorizationException;

import static org.apache.myfaces.shade.commons.codec.digest.DigestUtils.*;

public class AuthService {

    @Autowired
    private UserDAO dao;

    public User authorize(String login, String password) {
        User user = dao.findUserBy(login);
        if (user != null) {
            checkUserPassword(user, password);
            return user;
        }
        throw new AuthorizationException();
    }

    private void checkUserPassword(User user, String password) {
        if (!user.getPassword().equalsIgnoreCase(shaHex(password))) {
            throw new AuthorizationException();
        }
    }
}
