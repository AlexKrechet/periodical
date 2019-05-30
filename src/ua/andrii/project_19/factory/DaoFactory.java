package ua.andrii.project_19.factory;


import ua.andrii.project_19.dao.OrderDao;
import ua.andrii.project_19.dao.PeriodicalDao;
import ua.andrii.project_19.dao.PublisherDao;
import ua.andrii.project_19.dao.UserDao;
import ua.andrii.project_19.enums.DaoType;

public abstract class DaoFactory {
    public abstract UserDao getUserDao();
    public abstract PeriodicalDao getPeriodicalDao();
    public abstract OrderDao getOrderDao();
    public abstract PublisherDao getPublisherDao();


    public static DaoFactory getFactory(DaoType type) {
        if (type == DaoType.JDBC) {
            return JdbcDaoFactory.getInstance();
        } else {
            return null;
        }
    }
}
