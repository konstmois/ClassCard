package ru.classcard.dao;


import java.io.Serializable;
import java.util.Collection;

public interface AbstractEntityDAO {

    <T> void save(T entity);

    <T> void saveList(Collection<T> entities);

    <T> void delete(T entity);

    <T> T findEntityById(Class<T> classEntity, Serializable id);

}
