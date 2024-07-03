package model.impl;

import db.DatabaseConnection;
import model.dao.UserDao;
import model.entities.Ticket;
import model.entities.User;

import java.sql.*;


public class UserDaoImpl implements UserDao {
    static DatabaseConnection db;
    static Connection con;

    static {
        try {
            db = new DatabaseConnection();
            con = db.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUserById(long id) throws SQLException {
        con.setAutoCommit(false);
        ResultSet rs;
        String fetch = "SELECT id, username, creation_date from users WHERE id=?";
        try (PreparedStatement pr = con.prepareStatement(fetch)) {
            Savepoint savepoint = con.setSavepoint("savepoint");
            pr.setLong(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                return instantiateUser(rs, new Ticket());
            }
            con.rollback(savepoint);
            con.commit();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
        return null;
    }


    @Override
    public void save(User user) throws SQLException {
        con.setAutoCommit(false);
        int rowsInserted;
        String insert = "INSERT INTO users(id, username, creation_date) VALUES(?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(insert)) {
            Savepoint savepoint = con.setSavepoint("savepoint");
            statement.setLong(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setDate(3, Date.valueOf(user.getCreationDate()));
            rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            con.rollback(savepoint);
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        con.setAutoCommit(false);
        int rowsUpdated;
        String update = "UPDATE users SET username=? WHERE id=?";
        try (PreparedStatement statement = con.prepareStatement(update)) {
            Savepoint savepoint = con.setSavepoint("savepoint");
            statement.setString(1, user.getUsername());
            statement.setLong(2, user.getId());
            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
            con.rollback(savepoint);
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        con.setAutoCommit(false);
        int rowsDeleted;
        String delete = "DELETE FROM users WHERE id=?";
        try (PreparedStatement statement = con.prepareStatement(delete)) {
            Savepoint savepoint = con.setSavepoint("savepoint");
            statement.setLong(1, id);
            rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An existing user was deleted successfully!");
            }
            con.rollback(savepoint);
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
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
