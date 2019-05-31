package ua.andrii.project_19.factory;


import ua.andrii.project_19.dao.*;
import ua.andrii.project_19.entity.Order;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.enums.DaoType;

public abstract class DaoFactory {
    public abstract UserDao getUserDao();
    public abstract ItemsDao<Periodical> getPeriodicalDao();
    public abstract ItemsDao<Order> getOrderDao();
    public abstract ItemsDao<Publisher> getPublisherDao();


    public static DaoFactory getFactory(DaoType type) {
        if (type == DaoType.JDBC) {
            return JdbcDaoFactory.getInstance();
        } else {
            return null;
        }
    }
}
