package ua.andrii.project_19.service;

import com.sun.istack.internal.NotNull;
import ua.andrii.project_19.dao.ItemsDao;
import ua.andrii.project_19.dao.UserDao;
import ua.andrii.project_19.entity.*;
import ua.andrii.project_19.enums.UserType;
import ua.andrii.project_19.exception.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminServiceImpl implements AdminService {

    private UserDao<User> userDao;
    private ItemsDao<Periodical> periodicalDao;
    private ItemsDao<Publisher> publisherDao;
    private ItemsDao<Order> orderDao;

    public AdminServiceImpl(@NotNull UserDao userDao, @NotNull ItemsDao periodicalDao, @NotNull ItemsDao publisherDao, @NotNull ItemsDao orderDao) {
        this.userDao = userDao;
        this.periodicalDao = periodicalDao;
        this.publisherDao = publisherDao;
        this.orderDao = orderDao;
    }

    @Override
    public List<User> getClients() {
        return userDao.getClients();
    }

    @Override
    public User getUser(Long userId) {
        return userDao.read(userId);
    }

    /**
     * @throws AuthenticationException {@inheritDoc}
     */
    @Override
    public User login(String login, String password) throws AuthenticationException {
        if (login == null || login.isEmpty()) {
            throw new AuthenticationException("Login is a required field!");
        }
        if (password == null || password.isEmpty()) {
            throw new AuthenticationException("Password is a required field!");
        }
        return userDao.getUser(login, password);
    }

    /**
     * @throws RegistrationException {@inheritDoc}
     */
    @Override
    public boolean registerUser(String login, String password, String passwordConfirmation, String name, String surname, boolean isBlocked, UserType userType) throws RegistrationException {
        checkRegistrationData(login, name, surname, password, passwordConfirmation);
        User user = User.getUser(login, password, name, surname, isBlocked, userType);
        return userDao.create(user) != null;
    }

    /**
     * @throws WrongUserDataException {@inheritDoc}
     */
    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword, String passwordConfirmation) throws WrongUserDataException {
        if (!user.getPassword().equals(oldPassword)) {
            throw new WrongUserDataException("Wrong old password");
        }
        checkPassword(newPassword, passwordConfirmation);
        user.setPassword(newPassword);
        return userDao.update(user);
    }

    /**
     * @throws WrongUserDataException {@inheritDoc}
     */
    @Override
    public boolean changeUserData(User user, String password, String passwordConfirmation, String name, String surname, boolean isBlocked) throws WrongUserDataException {
        checkForScript(name);
        checkForScript(surname);
        checkPassword(password, passwordConfirmation);
        checkDataIsNotEmpty(name, "Name");
        checkDataIsNotEmpty(surname, "Surname");
        user.setName(name);
        user.setSurname(surname);
        user.setBlocked(isBlocked);

        return userDao.update(user);
    }

    /**
     * Checks if user's registration data is OK
     *
     * @param login                user login
     * @param password             user password
     * @param passwordConfirmation password confirmation (must match password)
     * @param name                 user name
     * @param surname              user surname
     * @throws WrongUserDataException in case some data is invalid
     */
    private void checkRegistrationData(String login, String name, String surname, String password, String passwordConfirmation) throws RegistrationException {
        try {
            checkForScript(name);
            checkForScript(surname);
            checkDataIsNotEmpty(login, "Login");
            if (!login.toLowerCase().matches("^[a-zA-Z0-9]+$")) {
                throw new RegistrationException("Login should be only letters and numbers.");
            }
            checkPassword(password, passwordConfirmation);
            checkDataIsNotEmpty(name, "Name");
            checkDataIsNotEmpty(surname, "Surname");
        } catch (WrongUserDataException e) {
            throw new RegistrationException(e.getMessage());
        }
        if (userDao.hasUser(login)) {
            throw new RegistrationException("User with login '" + login + "' already exists");
        }
    }

    /**
     * Checks if password is OK and password confirmation matches password
     *
     * @param password             user password
     * @param passwordConfirmation password confirmation (must match password)
     * @throws WrongUserDataException
     */
    private void checkPassword(String password, String passwordConfirmation) throws WrongUserDataException {
        if (password.isEmpty()) {
            throw new WrongUserDataException("Password is a required field!");
        }
        if (password.length() < 8) {
            throw new WrongUserDataException("Password must be 8 symbols minimum");
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]|[\\u0400-\\u044F]");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            throw new WrongUserDataException("Password must contain at least one letter");
        }
        pattern = Pattern.compile("[0-9]");
        matcher = pattern.matcher(password);
        if (!matcher.find()) {
            throw new WrongUserDataException("Password must contain at least one digit");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new WrongUserDataException("Password and password confirmation do not match");
        }
    }

    /**
     * Checks if password is OK and password confirmation matches password
     *
     * @param text string for check
     * @throws WrongUserDataException
     */
    private void checkForScript(String text) throws WrongUserDataException {

        if (text.contains("<") || (text.contains(">"))) {
            throw new WrongUserDataException("Text must not contain tag symbols");
        }
    }

    /**
     * Checks if given String object is not empty
     *
     * @param data     data to check
     * @param dataName data name representation
     * @throws WrongUserDataException
     */
    private void checkDataIsNotEmpty(String data, String dataName) throws WrongUserDataException {
        if (data == null || data.isEmpty()) {
            throw new WrongUserDataException(dataName + " can't be empty!");
        }
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public List<Periodical> getPeriodicals() {
        return periodicalDao.findAll();
    }

    @Override
    public Periodical getPeriodical(Long periodicalId) {
        return periodicalDao.read(periodicalId);
    }


    @Override
    public boolean updatePeriodical(Periodical periodical) throws WrongPeriodicalDataException {
        if (periodical.getName().isEmpty()) {
            throw new WrongPeriodicalDataException("Periodical name can't be empty");
        }
        if (periodical.getPrice() == null) {
            throw new WrongPeriodicalDataException("Price is a required field!");
        }
        if (periodical.getId() == null) {
            return periodicalDao.create(periodical) != null;
        } else {
            return periodicalDao.update(periodical);
        }
    }

    @Override
    public boolean addNewPeriodical(String periodicalName, Long publisherId, BigDecimal price) throws WrongPeriodicalDataException {
        if (periodicalName.isEmpty()) {
            throw new WrongPeriodicalDataException("Periodical name is a required field!");
        }
        if (publisherId == null) {
            throw new WrongPeriodicalDataException("Publisher is a required field!");
        }
        Publisher publisher = (Publisher) publisherDao.read(publisherId);
        if (price == null) {
            throw new WrongPeriodicalDataException("Price is a required field!");
        }
        Periodical periodical = new Periodical(periodicalName, publisher, price);
        return periodicalDao.create(periodical) != null;
    }

    @Override
    public boolean addNewPublisher(String publisherName) throws WrongPublisherDataException {
        if (publisherName.isEmpty()) {
            throw new WrongPublisherDataException("Publisher's name is a required field!");
        }
        Publisher publisher = new Publisher.Builder().withName(publisherName).build();

        return publisherDao.create(publisher) != null;
    }

    @Override
    public Publisher getPublisher(Long publisherId) {
        return publisherDao.read(publisherId);
    }

    @Override
    public List<Publisher> getPublishers() {
        return publisherDao.findAll();
    }

    @Override
    public boolean updatePublisher(Publisher publisher) throws WrongPublisherDataException {
        if (publisher.getName().isEmpty()) {
            throw new WrongPublisherDataException("Publisher's name can't be empty");
        }
        if (publisher.getId() == null) {
            return publisherDao.create(publisher) != null;
        } else {
            return publisherDao.update(publisher);
        }
    }

    @Override
    public boolean addNewOrder(List periodicalOrder, User user, BigDecimal totalPrice) throws WrongOrderDataException {
        if (periodicalOrder.isEmpty()) {
            throw new WrongOrderDataException("List is empty!");
        }
        Order order = new Order.Builder().withListPeriodicals(periodicalOrder).withUser(user).withTimestamp(new Timestamp(new Date().getTime())).withPaidStatus(false).withPrice(totalPrice).build();

        return orderDao.create(order) != null;
    }

    @Override
    public boolean updateOrder(Order order) throws WrongOrderDataException {
        if (order == null) {
            throw new WrongOrderDataException("Order can't be null");
        }
        return orderDao.update(order);
    }

    @Override
    public boolean deleteOrder(Order order) throws WrongOrderDataException {
        if (order == null) {
            throw new WrongOrderDataException("Order can't be null");
        }
        return orderDao.delete(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.findAll();
    }
}
