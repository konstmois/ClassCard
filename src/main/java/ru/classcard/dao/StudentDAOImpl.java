package ru.classcard.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.Student;
import ru.classcard.model.StudentClass;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

public class StudentDAOImpl extends AbstractEntityDAOImpl implements StudentDAO {

    @Override
    @Transactional
    public List<Student> findBy(StudentClass clazz) {
        return getSession().createCriteria(Student.class).add(eq("studentClass", clazz)).list();
    }
}
