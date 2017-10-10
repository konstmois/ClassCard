package ru.classcard.dao;

import ru.classcard.model.StudentClass;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface StudentClassDAO extends AbstractEntityDAO {

    List<StudentClass> getList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    int getCountBy(Map<String, Object> filters);
}
