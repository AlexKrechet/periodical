package ua.andrii.project_19.service;


import ua.andrii.project_19.entity.*;
import ua.andrii.project_19.enums.UserType;
import ua.andrii.project_19.exception.*;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService {
    /**
     * Looks for a record by given parameters; if founds, returns appropriate User object, otherwise - returns null.
     *
     * @param login    user login
     * @param password user passwords
     * @return found User object
     * @throws AuthenticationException in case login and/or password are empty
     */
    User login(String login, String password) throws AuthenticationException;

    /**
     * Create a record of a new user with given parameters;
     *
     * @param login                user login
     * @param password             user password
     * @param passwordConfirmation password confirmation (must match password)
     * @param name                 user name
     * @param surname              user family name
     * @param isBlocked            is user in black list
     * @param userType             user type; available values: UserType.ADMIN or UserType.CLIENT
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws RegistrationException in case some parameters are invalid
     */
    boolean registerUser(String login, String password, String passwordConfirmation, String name, String surname, boolean isBlocked, UserType userType) throws RegistrationException;

    /**
     * Changes user's password to a new one
     *
     * @param user                 current user
     * @param oldPassword          current user's password
     * @param newPassword          new user's password
     * @param passwordConfirmation new password confirmation (must match new password)
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongUserDataException in case some parameters are invalid
     */
    boolean changePassword(User user, String oldPassword, String newPassword, String passwordConfirmation) throws WrongUserDataException;

    /**
     * Changes user's personal data e.g. name, family name and patronymic
     *
     * @param user      current user
     * @param password  user's current password
     * @param name      user's new name
     * @param surname   user's new surname
     * @param isBlocked is user in black list
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongUserDataException in case some parameters are invalid
     */
    boolean changeUserData(User user, String password, String passwordConfirmation, String name, String surname, boolean isBlocked) throws WrongUserDataException;

    /**
     * Returns list of all users who have user type == Client
     *
     * @return list of Client objects
     */
    List<User> getClients();

    /**
     * Returns a User object by given id.
     * If no record with such id found, returns null
     *
     * @param userId id of the Periodical to be found
     * @return found Periodical object
     */
    User getUser(Long userId);


    /**
     * Returns list of all users
     *
     * @return list of User objects
     */
    List<User> getUsers();

    /**
     * Returns list of all periodicals
     *
     * @return list of Periodical objects
     */
    List<Periodical> getPeriodicals();

    /**
     * Returns a Periodical object by given id.
     * If no record with such id found, returns null
     *
     * @param periodicalId id of the Periodical to be found
     * @return found Periodical object
     */
    Periodical getPeriodical(Long periodicalId);

    /**
     * Updates given periodical or creates a new one in case periodical's id is null
     *
     * @param periodical periodical to be updated/created
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongPeriodicalDataException in case periodical name is empty
     */
    boolean updatePeriodical(Periodical periodical) throws WrongPeriodicalDataException;

    /**
     * Creates a new record of Periodical object with given parameters.
     *
     * @param periodicalName name of the periodical
     * @param publisherId    publisher id of the periodical
     * @param price          price of the periodical
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongPeriodicalDataException in case some periodical data is wrong
     */
    boolean addNewPeriodical(String periodicalName, Long publisherId, BigDecimal price) throws WrongPeriodicalDataException;

    /**
     * Creates a new record of Publisher object with given parameters.
     *
     * @param publisherName name of the publisher
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongPeriodicalDataException in case some periodical data is wrong
     */
    boolean addNewPublisher(String publisherName) throws WrongPublisherDataException;

    /**
     * Returns a Publisher object by given id.
     * If no record with such id found, returns null
     *
     * @param publisherId id of the Publisher to be found
     * @return found Publisher object
     */
    Publisher getPublisher(Long publisherId);

    /**
     * Returns list of all publishers
     *
     * @return list of Publisher objects
     */
    List<Publisher> getPublishers();

    /**
     * Updates given publisher or creates a new one in case periodical's id is null
     *
     * @param publisher publisher to be updated/created
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongPeriodicalDataException in case publisher's name is empty
     */
    boolean updatePublisher(Publisher publisher) throws WrongPublisherDataException;

    /**
     * Creates a new record of Order object with given parameters.
     *
     * @param periodicalOrder list of periodicals
     * @param user            user
     * @param totalPrice      total price of order
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongOrderDataException in case some order data is wrong
     */
    public boolean addNewOrder(List periodicalOrder, User user, BigDecimal totalPrice) throws WrongOrderDataException;

    /**
     * Updates given order or creates a new one in case order's id is null
     *
     * @param order order to be deleted
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongOrderDataException in case order is empty
     */
    boolean updateOrder(Order order) throws WrongOrderDataException;

    /**
     * Updates given order or creates a new one in case order's id is null
     *
     * @param order order to be deleted
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongOrderDataException in case order is empty
     */
    boolean deleteOrder(Order order) throws WrongOrderDataException;

    /**
     * Returns list of all orders
     *
     * @return list of Order objects
     */
    List<Order> getOrders();
}
