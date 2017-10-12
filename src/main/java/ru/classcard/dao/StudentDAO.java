package ru.classcard.dao;

import ru.classcard.model.Student;
import ru.classcard.model.StudentClass;

import java.util.List;

public interface StudentDAO extends AbstractEntityDAO {

    List<Student> findBy(StudentClass clazz);
}
