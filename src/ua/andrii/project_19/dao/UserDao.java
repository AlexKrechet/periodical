package ua.andrii.project_19.dao;


import ua.andrii.project_19.entity.Client;
import ua.andrii.project_19.entity.User;

import java.io.Serializable;
import java.util.List;

public interface UserDao<T> {
    Long create(T t);

    T read(Long id);

    boolean update(T t);

    boolean delete(T t);

    List<T> findAll();

    T getUser(String login, String password);

    boolean hasUser(String login);

    List<T> getClients();
}