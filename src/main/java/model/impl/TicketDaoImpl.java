package model.impl;

import db.DatabaseConnection;
import model.dao.TicketDao;
import model.entities.Ticket;
import model.entities.TicketType;
import model.entities.User;

import java.sql.*;
public class TicketDaoImpl implements TicketDao {
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
    public Ticket getTicketById(long id) throws SQLException {
        con.setAutoCommit(false);
        ResultSet rs;
        String idQuery = "SELECT id, user_id, ticket_type, creation_date from ticket WHERE id=?";
        try (PreparedStatement pr = con.prepareStatement(idQuery)) {
            Savepoint savepoint1 = con.setSavepoint();
            pr.setLong(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                return instantiateTicket(rs, new User());
            }
            con.rollback(savepoint1);
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
        return null;
    }

    @Override
    public Ticket getTicketByUserId(long userId) throws SQLException{
        con.setAutoCommit(false);
        ResultSet rs;
        String fetch = "SELECT id, user_id, ticket_type, creation_date from ticket WHERE user_id=?";
        try (PreparedStatement pr = con.prepareStatement(fetch)) {
            Savepoint savepoint1 = con.setSavepoint();
            pr.setLong(1, userId);
            rs = pr.executeQuery();
            if (rs.next()) {
                return instantiateTicket(rs, new User());
            }
            con.rollback(savepoint1);
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
        return null;

    }

    @Override
    public void save(Ticket ticket) throws SQLException {
        con.setAutoCommit(false);
        int rowsInserted;
        String insert = "INSERT INTO ticket(user_id, ticket_type, creation_date) VALUES(?, CAST(? AS ticket_type), ?)";
        try (PreparedStatement statement = con.prepareStatement(insert)) {
            Savepoint savepoint1 = con.setSavepoint();
            statement.setLong(1, ticket.getUserId());
            statement.setString(2, ticket.getTicketType().name());
            statement.setDate(3, Date.valueOf(ticket.getCreationDate()));
            rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            con.rollback(savepoint1);
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public void update(Ticket ticket) throws SQLException {
        con.setAutoCommit(false);
        int rowsUpdated;
        String update = "UPDATE ticket SET ticket_type=CAST(? AS ticket_type) WHERE id=?";
        try (PreparedStatement statement = con.prepareStatement(update)) {
            Savepoint savepoint1 = con.setSavepoint();
            statement.setString(1, ticket.getTicketType().name());
            statement.setLong(2, ticket.getId());
            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
            con.rollback(savepoint1);
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
        String remove = "DELETE FROM ticket WHERE id=?";
        try (PreparedStatement statement = con.prepareStatement(remove)) {
            Savepoint savepoint1 = con.setSavepoint();
            statement.setLong(1, id);
            rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An existing user was deleted successfully!");
            }
            con.rollback(savepoint1);
            con.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.setAutoCommit(true);
        }
    }

    private Ticket instantiateTicket(ResultSet rs, User user) {
        Ticket ticket = new Ticket();
        try {
            ticket.setId(rs.getLong("id"));
            ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
            ticket.setUserId(rs.getLong("user_id"));
            ticket.setUser(user);
            ticket.setCreationDate(rs.getDate(("creation_date")).toLocalDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticket;
    }

}