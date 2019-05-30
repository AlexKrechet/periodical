package ua.andrii.project_19.dao;


import ua.andrii.project_19.entity.Client;
import ua.andrii.project_19.entity.User;

import java.util.List;

public interface UserDao {
    Long create(User user);

    User read(Long id);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User getUser(String login, String password);

    boolean hasUser(String login);

    List<Client> getClients();
}