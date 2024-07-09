package model.impl;


import model.dao.UserDao;
import model.entities.User;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDaoImpl implements UserDao {
    public final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public User getUserById(long id) throws SQLException {
        ResultSet rs;
        String fetch = "SELECT * from users WHERE id=?";
        try (PreparedStatement pr = connection.prepareStatement(fetch)) {
            pr.setLong(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                return instantiateUser(rs);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void save(User user) throws SQLException {
        int rowsInserted;
        String insert = "INSERT INTO users(username, creation_date) VALUES(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insert)) {
            statement.setString(1, user.getUsername());
            statement.setDate(2, Date.valueOf(user.getCreationDate()));
            rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(User user) throws SQLException {
        int rowsUpdated;
        String update = "UPDATE users SET username=?, creation_date=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setString(1, user.getUsername());
            statement.setDate(2, Date.valueOf(user.getCreationDate()));
            statement.setLong(3, user.getId());
            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        int rowsDeleted;
        String delete = "DELETE FROM users WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setLong(1, id);
            rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An existing user was deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private User instantiateUser(ResultSet rs)  {
        User user = new User();
        try {
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setCreationDate(rs.getDate(("creation_date")).toLocalDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
