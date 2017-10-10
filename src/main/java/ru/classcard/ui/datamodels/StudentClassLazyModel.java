package ru.classcard.ui.datamodels;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ru.classcard.dao.StudentClassDAO;
import ru.classcard.model.StudentClass;

import java.util.List;
import java.util.Map;

public class StudentClassLazyModel extends LazyDataModel<StudentClass> {

    private static final long serialVersionUID = 2L;

    private List<StudentClass> result;

    private StudentClassDAO dao;

    public StudentClassLazyModel(StudentClassDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<StudentClass> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        result = dao.getList(first, pageSize, sortField, convertSortOrder(sortOrder), filters);
        this.setRowCount(dao.getCountBy(filters));
        return result;
    }

    private static javax.swing.SortOrder convertSortOrder(SortOrder sortOrder) {
        return sortOrder != null ? javax.swing.SortOrder.valueOf(sortOrder.name()) : null;
    }

    @Override
    public StudentClass getRowData(String rowKey) {
        for(StudentClass clazz : result) {
            if (rowKey.equals(clazz.getId().toString())) {
                return clazz;
            }
        }
        return null;
    }

}