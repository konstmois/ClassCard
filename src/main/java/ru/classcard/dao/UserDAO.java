package ru.classcard.dao;

import ru.classcard.model.User;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface UserDAO extends AbstractEntityDAO {

    User findUserBy(String login);

    List<User> getList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    int getCount(Map<String, Object> filters);

}
