package ua.andrii.project_19.dao;


import ua.andrii.project_19.entity.Order;

import java.util.List;

public interface OrderDao {
    Long create(Order order);

    Order read(Long id);

    boolean update(Order order);

    boolean delete(Order order);

    List<Order> findAll();
}
