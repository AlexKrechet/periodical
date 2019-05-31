package ua.andrii.project_19.service;

import com.sun.istack.internal.NotNull;
import ua.andrii.project_19.dao.ItemsDao;
import ua.andrii.project_19.dao.UserDao;
import ua.andrii.project_19.entity.*;
import ua.andrii.project_19.exception.WrongOrderDataException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private UserDao userDao;
    private ItemsDao<Periodical> periodicalDao;
    private ItemsDao<Publisher> publisherDao;
    private ItemsDao<Order> orderDao;

    public ClientServiceImpl(@NotNull UserDao userDao, @NotNull ItemsDao periodicalDao, @NotNull ItemsDao publisherDao, @NotNull ItemsDao orderDao) {
        this.userDao = userDao;
        this.periodicalDao = periodicalDao;
        this.publisherDao = publisherDao;
        this.orderDao = orderDao;
    }

    @Override
    public Periodical getPeriodical(Long periodicalId) {
        return periodicalDao.read(periodicalId);
    }

    @Override
    public List<Periodical> getPeriodicals() {
        return periodicalDao.findAll();
    }

    @Override
    public boolean addNewOrder(List<PeriodicalOrder> periodicalOrder, User user, BigDecimal totalPrice) throws WrongOrderDataException {
        if (periodicalOrder.isEmpty()) {
            throw new WrongOrderDataException("List is empty!");
        }

        //Checking every periodical price for negative inappropriate value
        for (PeriodicalOrder periodical : periodicalOrder) {
            if (periodical.getPeriodical().getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new WrongOrderDataException("Price is invalid!");
            }
        }

        //Checking totalPrice for negative inappropriate value
        if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new WrongOrderDataException("Price is invalid!");
        }
        Order order = new Order(periodicalOrder, user, new Timestamp(new Date().getTime()), false, totalPrice);

        return orderDao.create(order) != null;
    }
}
