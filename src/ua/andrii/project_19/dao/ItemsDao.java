package ua.andrii.project_19.dao;

import java.util.List;

public interface ItemsDao<T> {
    Long create(T t);

    T read(Long id);

    boolean update(T t);

    boolean delete(T t);

    List<T> findAll();
}
