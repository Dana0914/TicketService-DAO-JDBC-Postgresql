package model.impl;

import db.DatabaseConnection;
import model.dao.UserDao;
import model.entities.Ticket;
import model.entities.User;

import java.sql.*;


public class UserDaoImpl implements UserDao {
    DatabaseConnection db = new DatabaseConnection();

    @Override
    public User getUserById(long id)  {
        ResultSet rs;
        String fetch = "SELECT id, username, creation_date from users WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement pr = con.prepareStatement(fetch)) {
            pr.setLong(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                return instantiateUser(rs, new Ticket());
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void save(User user)  {
        int rowsInserted;
        String insert = "INSERT INTO users(id, username, creation_date) VALUES(?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement statement = con.prepareStatement(insert)) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setDate(3, Date.valueOf(user.getCreationDate()));
            rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        int rowsUpdated;
        String update = "UPDATE users SET username=? WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement statement = con.prepareStatement(update)) {
            statement.setString(1, user.getUsername());
            statement.setLong(2, user.getId());
            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        int rowsDeleted;
        String delete = "DELETE FROM users WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement statement = con.prepareStatement(delete)) {
            statement.setLong(1, id);
            rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An existing user was deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private User instantiateUser(ResultSet rs, Ticket ticket)  {
        User user = new User();
        try {
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setCreationDate(rs.getDate(("creation_date")).toLocalDate());
            user.setTicket(ticket);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
