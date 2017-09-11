package ru.classcard.dao;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.User;

import java.util.List;
import java.util.Map;

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
    public List<User> getList(int first, int pageSize, String sortField, javax.swing.SortOrder sortOrder, Map<String, Object> filters) {
        return buildCriteria(User.class, first, pageSize, sortField, sortOrder, filters).list();
    }

    @Override
    @Transactional
    public int getCountBy(Map<String, Object> filters) {
        return ((Long) buildCriteria(User.class, -1, -1, null, null, filters).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

}
