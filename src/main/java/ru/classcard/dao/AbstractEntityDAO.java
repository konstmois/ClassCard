package ru.classcard.dao;


import java.io.Serializable;

public interface AbstractEntityDAO {

    <T> void save(T entity);

    <T> void delete(T entity);

    <T> T findEntityById(Class<T> classEntity, Serializable id);

}
