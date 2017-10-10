package ru.classcard.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.StudentClass;

import java.util.List;
import java.util.Map;

import static org.hibernate.criterion.Projections.rowCount;

public class StudentClassDAOImpl extends AbstractEntityDAOImpl implements StudentClassDAO {

    @Override
    @Transactional
    public List<StudentClass> getList(int first, int pageSize, String sortField, javax.swing.SortOrder sortOrder, Map<String, Object> filters) {
        return buildCriteria(StudentClass.class, first, pageSize, sortField, sortOrder, filters).list();
    }

    @Override
    @Transactional
    public int getCountBy(Map<String, Object> filters) {
        return ((Long) buildCriteria(StudentClass.class, -1, -1, null, null, filters).setProjection(rowCount()).uniqueResult()).intValue();
    }

}
