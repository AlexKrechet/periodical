package ua.andrii.project_19.service;

import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.PeriodicalOrder;
import ua.andrii.project_19.entity.User;
import ua.andrii.project_19.exception.WrongOrderDataException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    /**
     * Returns a Periodical object by given id.
     * If no record with such id found, returns null
     *
     * @param periodicalId id of the Periodical to be found
     * @return found Periodical object
     */
    Periodical getPeriodical(Long periodicalId);

    /**
     * Returns list of all periodicals
     *
     * @return list of Periodical objects
     */
    List<Periodical> getPeriodicals();

    /**
     * Creates a new record of Order object with given parameters.
     *
     * @param periodicalOrder list of periodicals
     * @param user            user
     * @param totalPrice      total price of order
     * @return <code><b>true</b></code> if operation's successful, otherwise - <code><b>false</b></code>
     * @throws WrongOrderDataException in case some order data is wrong
     */
    public boolean addNewOrder(List<PeriodicalOrder> periodicalOrder, User user, BigDecimal totalPrice) throws WrongOrderDataException;
}
