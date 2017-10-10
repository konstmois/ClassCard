package ru.classcard.dao;

import ru.classcard.model.StudentClass;
import ru.classcard.model.User;
import ru.classcard.model.UserRole;

public interface UserDAO extends AbstractEntityDAO {

    User findUserBy(String login);

    User findUserBy(StudentClass studentClass, UserRole role);
}
