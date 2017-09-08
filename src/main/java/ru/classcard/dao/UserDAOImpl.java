package ru.classcard.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.User;

import java.util.List;

public class UserDAOImpl extends AbstractEntityDAOImpl implements UserDAO {

    @Override
    @Transactional
    public User findUserBy(String login) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login).ignoreCase())
                .uniqueResult();
    }

    @Override
    @Transactional
    public List<User> getList() {
        return (List<User>) getSession().createCriteria(User.class).list();
    }

}
