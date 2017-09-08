package ru.classcard.dao;

import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;

import java.util.List;
import java.util.Map;

public class CardOperationDAOImpl extends AbstractEntityDAOImpl implements CardOperationDAO {

    private static final String CARD_FIELD_NAME = "card";

    @Override
    @Transactional
    public List<CardOperation> getOperationsBy(Card card, int first, int pageSize, String sortField, javax.swing.SortOrder sortOrder, Map<String, Object> filters) {
        filters.put(CARD_FIELD_NAME, card);
        return buildCriteria(CardOperation.class, first, pageSize, sortField, sortOrder, filters).list();
    }

    @Override
    @Transactional
    public int getOperationsCountBy(Card card, Map<String, Object> filters) {
        filters.put(CARD_FIELD_NAME, card);
        return ((Long) buildCriteria(CardOperation.class, -1, -1, null, null, filters).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }
}
