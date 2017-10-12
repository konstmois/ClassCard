package ru.classcard.dao;

import ru.classcard.model.StudentClass;
import ru.classcard.model.Target;

import java.util.List;

public interface TargetDAO extends AbstractEntityDAO {

    List<Target> findBy(StudentClass clazz);
}
