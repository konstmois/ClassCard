package ru.classcard.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import ru.classcard.types.DateRange;
import ru.classcard.util.DateTransformer;

import javax.swing.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import static org.hibernate.criterion.Restrictions.*;

public class AbstractEntityDAOImpl {

    @Autowired
    SessionFactory sessionFactory;

    private DateTransformer dateTransformer = new DateTransformer();

    public <T> void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public <T> void saveList(Collection<T> entities) {
        for (T e : entities) {
            getSession().saveOrUpdate(e);
        }
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


    protected <T> Criteria buildCriteria(Class<T> classEntity, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Criteria criteria = getSession().createCriteria(classEntity);
        criteria = withFilters(filters,
                        withSortOrder(sortField, sortOrder,
                                withPagingLimits(first, pageSize, criteria)));
        return criteria;
    }

    private Criteria withPagingLimits(int first, int pageSize, Criteria criteria) {
        if (first != -1) {
            criteria = criteria.setFirstResult(first);
        }
        if (pageSize != -1) {
            criteria = criteria.setMaxResults(pageSize);
        }
        return criteria;
    }

    private Criteria withFilters(Map<String, Object> filters, Criteria criteria) {
        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (entry.getValue() instanceof Date) {
                    withDateFilter(criteria, entry);
                } else if (entry.getValue() instanceof Object[]) {
                    criteria = withArrayFilter(criteria, entry);
                } else if (entry.getValue() instanceof DateRange) {
                    criteria = withDateRangeFilter(criteria, entry);
                } else {
                    criteria = criteria.add(eq(entry.getKey(), entry.getValue()));
                }
            }
        }
        return criteria;
    }

    private Criteria withSortOrder(String sortField, SortOrder sortOrder, Criteria criteria) {
        if (sortField != null && !sortField.isEmpty()) {
            if (SortOrder.ASCENDING.equals(sortOrder)) {
                criteria = criteria.addOrder(Order.asc(sortField));
            } else if (SortOrder.DESCENDING.equals(sortOrder)) {
                criteria = criteria.addOrder(Order.desc(sortField));
            }
        }
        return criteria;
    }

    private Criteria withDateRangeFilter(Criteria criteria, Map.Entry<String, Object> entry) {
        DateRange value = (DateRange) entry.getValue();
        String fieldName = entry.getKey();
        if (value.getTo() == null && value.getFrom() != null) {
            criteria = criteria.add(gt(fieldName, value.getFrom()));
        } else if (value.getFrom() == null && value.getTo() != null) {
            criteria = criteria.add(le(fieldName, value.getTo()));
        } else if (value.getFrom() != null && value.getTo() != null) {
            criteria = criteria.add(gt(fieldName, value.getFrom()))
                               .add(le(fieldName, value.getTo()));
        }
        return criteria;
    }

    private Criteria withArrayFilter(Criteria criteria, Map.Entry<String, Object> entry) {
        if (((Object[]) entry.getValue()).length > 0) {
            criteria = criteria.add(in(entry.getKey(), (Object[]) entry.getValue()));
        }
        return criteria;
    }

    private Criteria withDateFilter(Criteria criteria, Map.Entry<String, Object> entry) {
        Date fromDate = dateTransformer.getDateWithoutTime((Date) entry.getValue());
        Date toDate = dateTransformer.getDateWithoutTime(dateTransformer.getTomorrowDate((Date) entry.getValue()));
        criteria.add(ge(entry.getKey(), fromDate));
        criteria.add(le(entry.getKey(), toDate));
        return criteria;
    }

}
