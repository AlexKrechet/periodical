package ua.andrii.project_19.factory;

import org.apache.log4j.Logger;
import ua.andrii.project_19.dao.*;
import ua.andrii.project_19.dao.impl.OrderDaoImpl;
import ua.andrii.project_19.dao.impl.PeriodicalDaoImpl;
import ua.andrii.project_19.dao.impl.PublisherDaoImpl;
import ua.andrii.project_19.dao.impl.UserDaoImpl;
import ua.andrii.project_19.entity.Order;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.Publisher;
import ua.andrii.project_19.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

    private static final Logger logger = Logger.getLogger(JdbcDaoFactory.class);
    private static JdbcDaoFactory instance;
    private UserDao<User> userDao;
    private ItemsDao<Periodical> periodicalDao;
    private ItemsDao<Order> orderDao;
    private ItemsDao<Publisher> publisherDao;

    private JdbcDaoFactory() {
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            DataSource datasource = (DataSource) context.lookup("jdbc/MySQLDataSource");
            userDao = new UserDaoImpl(datasource);
            periodicalDao = new PeriodicalDaoImpl(datasource);
            publisherDao = new PublisherDaoImpl(datasource);
            orderDao = new OrderDaoImpl(datasource);

        } catch (NamingException e) {
            logger.error("Failed to initialize context: " + e.getMessage());
        }
    }

    public static JdbcDaoFactory getInstance() {
        synchronized (JdbcDaoFactory.class) {
            if (instance == null) {
                instance = new JdbcDaoFactory();
            }
        }
        return instance;
    }

    @Override
    public UserDao<User> getUserDao() {
        return userDao;
    }

    @Override
    public ItemsDao getPeriodicalDao() {
        return periodicalDao;
    }

    @Override
    public ItemsDao getOrderDao() {
        return orderDao;
    }

    @Override
    public ItemsDao getPublisherDao() { return publisherDao; }
}
