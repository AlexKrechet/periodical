package ua.andrii.project_19.servlets;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import ua.andrii.project_19.dao.OrderDao;
import ua.andrii.project_19.dao.PeriodicalDao;
import ua.andrii.project_19.dao.PublisherDao;
import ua.andrii.project_19.dao.UserDao;
import ua.andrii.project_19.dao.impl.OrderDaoImpl;
import ua.andrii.project_19.dao.impl.PeriodicalDaoImpl;
import ua.andrii.project_19.dao.impl.PublisherDaoImpl;
import ua.andrii.project_19.dao.impl.UserDaoImpl;
import ua.andrii.project_19.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataSource datasource;

        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/eshop1?useUnicode=yes&characterEncoding=utf8&useSSL=false");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("kA500asvB1QtaiSDXrxT");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        datasource = new org.apache.tomcat.jdbc.pool.DataSource();
        datasource.setPoolProperties(p);

        Publisher publisher = new Publisher("Publisher");
        PublisherDao publisherDao = new PublisherDaoImpl(datasource);
        System.out.println(publisher);
        System.out.println(publisherDao.create(publisher));
        System.out.println(publisherDao.create(publisher));
        System.out.println(publisherDao.delete(publisher));
        System.out.println(publisherDao.read(1L));
        publisher = publisherDao.read(1L);
        System.out.println(publisherDao.findAll());
        publisher.setName("Publishing houses");
        System.out.println(publisherDao.update(publisher));
        System.out.println(publisherDao.findAll());


        UserDao userDao = new UserDaoImpl(datasource);
        System.out.println(userDao.findAll());
        User guest = new Client("guest", "1111", "Guest", "Guest", false);
        User guest2 = new Client("guest2", "2222", "Guest2", "Guest2", false);
        System.out.println(userDao.create(guest));
        System.out.println(userDao.create(guest2));
        System.out.println(userDao.hasUser("guest2"));
        guest2.setIsBlocked(true);
        System.out.println(userDao.update(guest2));
        System.out.println(userDao.findAll());
        System.out.println(userDao.delete(guest2));
        System.out.println(userDao.findAll());

        Periodical periodical = new Periodical("Maxim", publisher, new BigDecimal(120.99));
        Periodical periodical2 = new Periodical("Day", publisher, new BigDecimal(19.99));
        PeriodicalDao periodicalDao = new PeriodicalDaoImpl(datasource);
        System.out.println(periodicalDao.create(periodical));
        System.out.println(periodicalDao.create(periodical2));

        System.out.println(periodicalDao.read(1L));
        periodical = periodicalDao.read(1L);
        periodical.setName("Maxim");

        System.out.println(periodicalDao.update(periodical));
        System.out.println(periodicalDao.findAll());
//        System.out.println(periodicalDao.delete(periodical));
        System.out.println(periodicalDao.findAll());

        Order order = new Order();
        PeriodicalOrder periodicalOrder = new PeriodicalOrder(periodical, 1);
        PeriodicalOrder periodicalOrder2 = new PeriodicalOrder(periodical2, 2);
        List<PeriodicalOrder> periodicalOrders = new ArrayList<>();
        periodicalOrders.add(periodicalOrder);
        periodicalOrders.add(periodicalOrder2);
        order.setPeriodicalOrders(periodicalOrders);
        order.setUser(guest);
        order.setPaid(false);
        BigDecimal sum = new BigDecimal(300.95);

        order.setTotal_price(sum);

        OrderDao orderDao = new OrderDaoImpl(datasource);
        orderDao.create(order);
        System.out.println("*****************");
        //System.out.println(orderDao.delete(order));
        Order order2 = orderDao.read(1L);
        System.out.println(order2);


        datasource.close();
    }
}
