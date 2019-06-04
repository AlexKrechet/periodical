package ua.andrii.project_19.dao.impl;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.andrii.project_19.dao.ItemsDao;
import ua.andrii.project_19.entity.Periodical;
import ua.andrii.project_19.entity.Publisher;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalDaoImpl implements ItemsDao<Periodical> {

    private static final Logger logger = Logger.getLogger(PeriodicalDaoImpl.class);
    private DataSource datasource;
    private final String SQL_BASE_QUERY_SELECTION_TEXT = "SELECT periodical.*, publisher.* FROM Periodical as periodical LEFT JOIN Publisher as publisher ON periodical.publisher_id = publisher.id";

    public PeriodicalDaoImpl(@NotNull DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Long create(Periodical periodical) {
        String query_text = "INSERT INTO periodical (name, publisher_id, price) VALUES (?, ?, ?)";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, periodical.getName());
            statement.setLong(2, periodical.getPublisher().getId());
            statement.setBigDecimal(3, periodical.getPrice());

            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                long id = result.getLong(1);
                periodical.setId(id);
                return id;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to insert into Periodical! " + e.getMessage());
            return null;
        }
    }

    @Override
    public Periodical read(Long id) {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT + " WHERE periodical.id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            List<Periodical> periodicals = getPeriodicalFromResultSet(result);
            if (periodicals.size() > 0) {
                Periodical periodical = periodicals.get(0);
                return periodical;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to read from Periodical! " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Periodical periodical) {
        String query_text = "UPDATE periodical SET name = ?, publisher_id = ?, price = ? WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setString(1, periodical.getName());
            statement.setLong(2, periodical.getPublisher().getId());
            statement.setBigDecimal(3, periodical.getPrice());
            statement.setLong(4, periodical.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Failed to updated Periodical! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Periodical periodical) {
        String query_text = "DELETE FROM periodical WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, periodical.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete from periodical! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Periodical> findAll() {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT;
        logger.info(query_text);
        List<Periodical> periodicals = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query_text);
            periodicals = getPeriodicalFromResultSet(result);
        } catch (SQLException e) {
            logger.error("Failed to read from periodical! " + e.getMessage());
        }
        return periodicals;
    }

    private List<Periodical> getPeriodicalFromResultSet(ResultSet result) throws SQLException {
        List<Periodical> periodicals = new ArrayList<>();
        while (result.next()) {

            long id = result.getLong("periodical.id");
            String name = result.getString("periodical.name");
            Long publisher_id = result.getLong("periodical.publisher_id");
            String publisherName = result.getString("publisher.name");

            Publisher publisher = new Publisher.Builder().withName(publisherName).build();
            publisher.setId(publisher_id);
            BigDecimal price = result.getBigDecimal("periodical.price");

            Periodical periodical = new Periodical(name, publisher, price);
            periodical.setId(id);
            periodicals.add(periodical);
        }
        return periodicals;
    }
}
