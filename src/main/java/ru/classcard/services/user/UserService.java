package ru.classcard.services.user;

import org.apache.myfaces.shade.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.classcard.dao.CardDAO;
import ru.classcard.dao.UserDAO;
import ru.classcard.model.Card;
import ru.classcard.model.User;

import static ru.classcard.model.UserRole.ADMIN;

public class UserService {

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private UserDAO userDao;

    @Transactional
    public void save(User user, Card card) {
        saveUser(user);
        saveCard(user, card);
    }

    private void saveCard(User user, Card card) {
        if (!isAdmin(user)) {
            card.setOwner(user);
            cardDAO.save(card);
        }
    }

    private boolean isAdmin(User user) {
        return ADMIN.equals(user.getRole());
    }

    private void saveUser(User user) {
        String hash = DigestUtils.shaHex(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
    }

}
