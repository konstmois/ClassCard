package ru.classcard.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.classcard.types.DateRange;
import ru.classcard.util.DateTransformer;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class AbstractEntityDAOImpl {

    @Autowired
    SessionFactory sessionFactory;

    private DateTransformer dateTransformer = new DateTransformer();


    public <T> void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public <T> void delete(T entity) {
        getSession().delete(entity);
    }

    public <T> T findEntityById(Class<T> classEntity, Serializable id) {
        return (T) getSession().get(classEntity, id);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    //TODO refactor
    protected  <T> Criteria getCriteria(Class<T> classEntity, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Criteria criteria = getSession().createCriteria(classEntity);

        if (sortField != null && !sortField.isEmpty()) {
            if (SortOrder.ASCENDING.equals(sortOrder)) {
                criteria = criteria.addOrder(Order.asc(sortField));
            } else if (SortOrder.DESCENDING.equals(sortOrder)) {
                criteria = criteria.addOrder(Order.desc(sortField));
            }
        }

        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (entry.getValue() instanceof Date) {
                    addDateFilter(criteria, entry);
                } else if (entry.getValue() instanceof Object[]) {
                    criteria = addArrayFilter(criteria, entry);
                } else if (entry.getValue() instanceof DateRange) {
                    criteria = addDateRangeFilter(criteria, entry);
                } else {
                    criteria = criteria.add(Restrictions.like(entry.getKey(), entry.getValue()));
                }
            }
        }

        if (first != -1) {
            criteria = criteria.setFirstResult(first);
        }
        if (pageSize != -1) {
            criteria = criteria.setMaxResults(pageSize);
        }

        return criteria;
    }

    private Criteria addDateRangeFilter(Criteria criteria, Map.Entry<String, Object> entry) {
        DateRange value = (DateRange) entry.getValue();
        if (value.getTo() == null && value.getFrom() != null) {
            criteria = criteria.add(Restrictions.gt(entry.getKey(), value.getFrom()));
        } else if (value.getFrom() == null && value.getTo() != null) {
            criteria = criteria.add(Restrictions.le(entry.getKey(), value.getTo()));
        } else if (value.getFrom() != null && value.getTo() != null) {
            criteria = criteria.add(Restrictions.gt(entry.getKey(), value.getFrom()))
                    .add(Restrictions.le(entry.getKey(), value.getTo()));
        }
        return criteria;
    }

    private Criteria addArrayFilter(Criteria criteria, Map.Entry<String, Object> entry) {
        if (((Object[]) entry.getValue()).length > 0) {
            criteria = criteria.add(Restrictions.in(entry.getKey(), (Object[]) entry.getValue()));
        }
        return criteria;
    }

    private void addDateFilter(Criteria criteria, Map.Entry<String, Object> entry) {
        Date fromDate = dateTransformer.getDateWithoutTime((Date) entry.getValue());
        Date toDate = dateTransformer.getDateWithoutTime(dateTransformer.getTomorrowDate((Date) entry.getValue()));
        criteria.add(Restrictions.ge(entry.getKey(), fromDate));
        criteria.add(Restrictions.le(entry.getKey(), toDate));
    }

}
