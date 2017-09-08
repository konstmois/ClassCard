package ru.classcard.dao;

import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface CardOperationDAO extends AbstractEntityDAO {

    int getOperationsCountBy(Card card, Map<String, Object> filters);

    List<CardOperation> getOperationsBy(Card card, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
}
