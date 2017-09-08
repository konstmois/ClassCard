package ru.classcard.ui.datamodels;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;
import ru.classcard.model.OperationType;

import java.util.List;
import java.util.Map;

public class CardOperationLazyModel extends LazyDataModel<CardOperation> {

    private static final long serialVersionUID = 1L;

    private CardOperationDAO dao;
    private Card card;
    private OperationType type;

    public CardOperationLazyModel(Card card, OperationType type, CardOperationDAO dao) {
        this.card = card;
        this.type = type;
        this.dao = dao;
    }

    @Override
    public List<CardOperation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filters.put("type", type);
        List<CardOperation> result = dao.getOperationsBy(card, first, pageSize, sortField, convertSortOrder(sortOrder), filters);
        this.setRowCount(dao.getOperationsCountBy(card, filters));
        return result;
    }

    public static javax.swing.SortOrder convertSortOrder(org.primefaces.model.SortOrder sortOrder) {
        return sortOrder != null ? javax.swing.SortOrder.valueOf(sortOrder.name()) : null;
    }

}