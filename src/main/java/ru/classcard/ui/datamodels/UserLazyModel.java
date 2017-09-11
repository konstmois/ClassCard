package ru.classcard.ui.datamodels;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ru.classcard.dao.UserDAO;
import ru.classcard.model.User;

import java.util.List;
import java.util.Map;

public class UserLazyModel extends LazyDataModel<User> {

    private static final long serialVersionUID = 2L;

    private List<User> result;

    private UserDAO dao;

    public UserLazyModel(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        result = dao.getList(first, pageSize, sortField, convertSortOrder(sortOrder), filters);
        this.setRowCount(dao.getCountBy(filters));
        return result;
    }

    private static javax.swing.SortOrder convertSortOrder(SortOrder sortOrder) {
        return sortOrder != null ? javax.swing.SortOrder.valueOf(sortOrder.name()) : null;
    }

    @Override
    public User getRowData(String rowKey) {
        for(User user : result) {
            if (rowKey.equals(user.getId().toString())) {
                return user;
            }
        }
        return null;
    }

}