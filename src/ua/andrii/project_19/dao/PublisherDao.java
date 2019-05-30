package ua.andrii.project_19.dao;


import ua.andrii.project_19.entity.Publisher;

import java.util.List;

public interface PublisherDao {
    Long create(Publisher publisher);
    Publisher read(Long id);
    boolean update(Publisher publisher);
    boolean delete(Publisher publisher);
    List<Publisher> findAll();
}
