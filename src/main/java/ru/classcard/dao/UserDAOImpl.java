package ru.classcard.dao;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ru.classcard.model.User;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class UserDAOImpl extends AbstractEntityDAOImpl implements UserDAO {

    @Override
    public User findUserBy(String login) {
        return (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login).ignoreCase())
                .uniqueResult();
    }

    @Override
    public List<User> getList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return getCriteria(User.class, first, pageSize, sortField, sortOrder, filters).list();
    }

    @Override
    public int getCount(Map<String, Object> filters) {
        return ((Long) getCriteria(User.class, -1, -1, null, null, filters)
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

}
