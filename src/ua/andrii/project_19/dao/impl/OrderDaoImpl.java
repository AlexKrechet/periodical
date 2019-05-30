package ua.andrii.project_19.dao.impl;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.andrii.project_19.dao.OrderDao;
import ua.andrii.project_19.entity.*;
import ua.andrii.project_19.enums.UserType;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private DataSource datasource;
    private final String SQL_BASE_QUERY_SELECTION_TEXT = "SELECT orders.*, users.*, periodical.*, periodical_orders.*, publisher.* " +
            "FROM orders " +
            "LEFT JOIN users AS users ON users.id = orders.user_id " +
            "LEFT JOIN periodical_orders AS periodical_orders ON periodical_orders.orders_id = orders.id " +
            "LEFT JOIN periodical AS periodical ON periodical.id = periodical_orders.periodical_id " +
            "LEFT JOIN publisher as publisher ON periodical.publisher_id = publisher.id";


    public OrderDaoImpl(@NotNull DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Long create(Order order) {
        String query_text = "INSERT INTO orders (total_price, purchase_date, user_id, paid) VALUES (?, ?, ?, ?)";//TODO: Constants everywhere if possible
        logger.info(query_text);
        Long id = null;
        //TODO: Independent methods where the body is too masive
        //TODO: Correction of naming for not digits
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setBigDecimal(1, order.getTotal_price());
            statement.setTimestamp(2, new Timestamp(order.getPurchaseDate().getTime()));
            statement.setLong(3, order.getUser().getId());
            statement.setBoolean(4, order.isPaid());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                id = result.getLong(1);
                order.setId(id);

                boolean resultCreatePeriodicalOrderList = createPeriodicalOrderList(connection, order, order.getPeriodicalOrders());
                if (!resultCreatePeriodicalOrderList) {
                    connection.rollback();
                    logger.error("Error occurred while creating periodicalOrders list");
                }
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            logger.error("Failed to insert into Orders! " + e.getMessage());
            return null; //TODO: should throw new DB Access exception for case if DB falling
        }
    }

    //TODO: where to push <>
    private boolean createPeriodicalOrderList(Connection connection, Order order, List<PeriodicalOrder> periodicalOrders) {
        int result = 0;
        for (PeriodicalOrder periodicalOrder : periodicalOrders) {
            result += createPeriodicalOrder(connection, order, periodicalOrder.getPeriodical(), periodicalOrder.getPeriodicalQuantity());
        }
        return result == periodicalOrders.size();
    }

    private int createPeriodicalOrder(Connection connection, Order order, Periodical periodical, int periodicalQuantity) {
        String query_text = "INSERT INTO periodical_orders (periodical_id, orders_id, periodical_quantity) VALUES (?, ?, ?)";
        logger.info(query_text);
        try (PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, periodical.getId());
            statement.setLong(2, order.getId());
            statement.setInt(3, periodicalQuantity);

            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to insert into Periodical_orders! " + e.getMessage());
            return -1;
        }
    }

    @Override
    public Order read(Long id) {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT + " WHERE orders.id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            List<Order> orders = getOrderFromResultSet(result);
            if (orders.size() > 0) {
                Order order = orders.get(0);
                order.setPeriodicalOrders(getPeriodicalOrdersFromResultSet(result));
                return order;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to read from Orders! " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Order order) {
        String query_text = "UPDATE orders SET total_price = ?, purchase_date = ?, user_id = ?, paid = ? WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {

            statement.setBigDecimal(1, order.getTotal_price());
            statement.setTimestamp(2, new Timestamp(order.getPurchaseDate().getTime()));
            statement.setLong(3, order.getUser().getId());
            statement.setBoolean(4, order.isPaid());
            System.out.println("****************************************");
            System.out.println(order.isPaid());
            statement.setLong(5, order.getId());

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Failed to updated Orders! " + e.getMessage());

            return false;
        }
    }

    @Override
    public boolean delete(Order order) {
        String query_text = "DELETE orders, periodical_orders FROM orders LEFT JOIN periodical_orders as periodical_orders ON orders_id = id WHERE orders.id= ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setLong(1, order.getId());
            int resultDeleteOrders = statement.executeUpdate();

            if (!(resultDeleteOrders > 0)) {
                connection.rollback();
                logger.error("Error occurred while deleting Orders, periodicalOrders lists");
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete from Orders! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> findAll() {

        String query_text = "SELECT orders.*, users.* FROM orders LEFT JOIN users AS users ON users.id = orders.user_id";
        logger.info(query_text);
        List<Order> orders = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query_text);
            orders = getOrderFromResultSetSimple(result);
        } catch (SQLException e) {
            logger.error("Failed to read from Orders! " + e.getMessage());
        }
        return orders;
    }

    private List<Order> getOrderFromResultSetSimple(ResultSet result) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (result.next()) {

            long orderId = result.getLong("orders.id");
            Timestamp purchaseDate = result.getTimestamp("orders.purchase_date");
            boolean paid = result.getBoolean("orders.paid");
            BigDecimal totalPrice = result.getBigDecimal("orders.total_price");
            long userId = result.getLong("users.id");

            String login = result.getString("users.login");
            String password = result.getString("users.password");
            String name = result.getString("users.name");
            String surname = result.getString("users.surname");
            boolean isBlocked = result.getBoolean("users.isBlocked");
            UserType userType = UserType.valueOf(result.getString("users.user_type").toUpperCase());


            User user = User.getUser(login, password, name, surname, isBlocked, userType);
            user.setId(userId);

            //Order order = new Order(getPeriodicalOrdersFromResultSet(result), user, purchaseDate, paid, totalPrice);
            Order order = new Order(null, user, purchaseDate, paid, totalPrice);
            order.setId(orderId);
            orders.add(order);
        }
        return orders;
    }

    private List<Order> getOrderFromResultSet(ResultSet result) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (result.next()) {

            long orderId = result.getLong("orders.id");
            Timestamp purchaseDate = result.getTimestamp("orders.purchase_date");
            boolean paid = result.getBoolean("orders.paid");
            BigDecimal totalPrice = result.getBigDecimal("orders.total_price");

            String login = result.getString("users.login");
            String password = result.getString("users.password");
            String name = result.getString("users.name");
            String surname = result.getString("users.surname");
            boolean isBlocked = result.getBoolean("users.isBlocked");
            UserType userType = UserType.valueOf(result.getString("users.user_type").toUpperCase());
            long userId = result.getLong("users.id");

            User user = User.getUser(login, password, name, surname, isBlocked, userType);
            user.setId(userId);

            Order order = new Order(getPeriodicalOrdersFromResultSet(result), user, purchaseDate, paid, totalPrice);
            order.setId(orderId);
            orders.add(order);
        }
        return orders;
    }

    private List<PeriodicalOrder> getPeriodicalOrdersFromResultSet(ResultSet result) throws SQLException {
        List<PeriodicalOrder> periodicalOrders = new ArrayList<>();
        while (result.next()) {

            Long publisherId = result.getLong("periodical.publisher_id");
            String publisherName = result.getString("publisher.name");
            Publisher publisher = new Publisher(publisherName);

            publisher.setId(publisherId);

            int periodicalQuantity = result.getInt("periodical_orders.periodical_quantity");
            long periodicalId = result.getLong("periodical.id");
            String name = result.getString("periodical.name");
            BigDecimal price = result.getBigDecimal("periodical.price");

            Periodical periodical = new Periodical(name, publisher, price);
            periodical.setId(periodicalId);

            PeriodicalOrder periodicalOrder = new PeriodicalOrder(periodical, periodicalQuantity);
            periodicalOrders.add(periodicalOrder);
        }
        return periodicalOrders;
    }
}
