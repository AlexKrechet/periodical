package ua.andrii.project_19.dao.impl;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;
import ua.andrii.project_19.dao.ItemsDao;
import ua.andrii.project_19.entity.Publisher;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDaoImpl implements ItemsDao<Publisher> {

    private static final Logger logger = Logger.getLogger(PublisherDaoImpl.class);
    private DataSource datasource;
    private final static String SQL_BASE_QUERY_SELECTION_TEXT = "SELECT * FROM publisher";

    public PublisherDaoImpl(@NotNull DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Long create(Publisher publisher) {
        String query_text = "INSERT INTO publisher (name) VALUES (?)";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, publisher.getName());

            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                long id = result.getLong(1);
                publisher.setId(id);
                return id;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to insert into publishers! " + e.getMessage());
            return null;
        }
    }

    @Override
    public Publisher read(Long id) {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT + " WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            List<Publisher> publishers = getPublisherFromResultSet(result);
            if (publishers.size() > 0) {
                Publisher publisher = publishers.get(0);
                return publisher;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("Failed to read from publishers! " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Publisher publisher) {
        String query_text = "UPDATE publisher SET name = ? WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setString(1, publisher.getName());
            statement.setLong(2, publisher.getId());
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Failed to updated publishers! " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Publisher publisher) {
        String query_text = "DELETE FROM publisher WHERE id = ?";
        logger.info(query_text);
        try (Connection connection = datasource.getConnection(); PreparedStatement statement = connection.prepareStatement(query_text)) {
            statement.setLong(1, publisher.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Failed to delete from publishers! " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Publisher> findAll() {
        String query_text = SQL_BASE_QUERY_SELECTION_TEXT;
        logger.info(query_text);
        List<Publisher> publishers = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query_text);
            publishers = getPublisherFromResultSet(result);
        } catch (SQLException e) {
            logger.error("Failed to read from publishers! " + e.getMessage());
        }
        return publishers;
    }

    private List<Publisher> getPublisherFromResultSet(ResultSet result) throws SQLException {
        List<Publisher> publishers = new ArrayList<>();
        while (result.next()) {

            long id = result.getLong("id");
            String name = result.getString("name");

            Publisher publisher = new Publisher.Builder().withName(name).build();
            publisher.setId(id);
            publishers.add(publisher);
        }
        return publishers;
    }
}
