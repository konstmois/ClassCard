package ru.classcard.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.StudentClass;
import ru.classcard.model.User;
import ru.classcard.model.UserRole;

import static org.hibernate.criterion.Restrictions.eq;

public class UserDAOImpl extends AbstractEntityDAOImpl implements UserDAO {

    @Override
    @Transactional
    public User findUserBy(String login) {
        return (User) getSession().createCriteria(User.class).add(eq("login", login).ignoreCase()).uniqueResult();
    }

    @Override
    @Transactional
    public User findUserBy(StudentClass studentClass, UserRole role) {
        return (User) getSession().createCriteria(User.class)
                                  .add(eq("role", role))
                                  .add(eq("studentClass", studentClass)).uniqueResult();
    }
}
