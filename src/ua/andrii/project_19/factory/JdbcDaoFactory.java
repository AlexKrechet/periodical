package ua.andrii.project_19.factory;

import org.apache.log4j.Logger;
import ua.andrii.project_19.dao.OrderDao;
import ua.andrii.project_19.dao.PeriodicalDao;
import ua.andrii.project_19.dao.PublisherDao;
import ua.andrii.project_19.dao.UserDao;
import ua.andrii.project_19.dao.impl.OrderDaoImpl;
import ua.andrii.project_19.dao.impl.PeriodicalDaoImpl;
import ua.andrii.project_19.dao.impl.PublisherDaoImpl;
import ua.andrii.project_19.dao.impl.UserDaoImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

    private static final Logger logger = Logger.getLogger(JdbcDaoFactory.class);
    private static JdbcDaoFactory instance;
    private UserDao userDao;
    private PeriodicalDao periodicalDao;
    private OrderDao orderDao;
    private PublisherDao publisherDao;

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
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public PeriodicalDao getPeriodicalDao() {
        return periodicalDao;
    }

    @Override
    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public PublisherDao getPublisherDao() { return publisherDao; }
}
