package ru.classcard.dao;

import ru.classcard.model.User;

import java.util.List;

public interface UserDAO extends AbstractEntityDAO {

    User findUserBy(String login);

    List<User> getList();

}
