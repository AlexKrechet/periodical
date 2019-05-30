package ua.andrii.project_19.dao;


import ua.andrii.project_19.entity.Periodical;
import java.util.List;

public interface PeriodicalDao {
    Long create(Periodical periodical);
    Periodical read(Long id);
    boolean update(Periodical periodical);
    boolean delete(Periodical periodical);
    List<Periodical> findAll();
}
