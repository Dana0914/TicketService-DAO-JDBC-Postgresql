package model.impl;

import db.DatabaseConnection;
import model.dao.TicketDao;
import model.entities.Ticket;
import model.entities.TicketType;
import model.entities.User;

import java.sql.*;
public class TicketDaoImpl implements TicketDao {
    DatabaseConnection databaseConnection;
    Connection con;
    public TicketDaoImpl(DatabaseConnection databaseConnection, Connection con) {
        this.databaseConnection = databaseConnection;
        this.con = con;
    }

    @Override
    public Ticket getTicketById(long id) throws SQLException {
        ResultSet rs;
        String idQuery = "SELECT * from ticket WHERE id=?";
        try (PreparedStatement pr = con.prepareStatement(idQuery)) {
            pr.setLong(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                return instantiateTicket(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Ticket getTicketByUserId(long userId) throws SQLException{
        ResultSet rs;
        String fetch = "SELECT * from ticket WHERE user_id=?";
        try (PreparedStatement pr = con.prepareStatement(fetch)) {
            pr.setLong(1, userId);
            rs = pr.executeQuery();
            if (rs.next()) {
                return instantiateTicket(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public void save(Ticket ticket) throws SQLException {
        int rowsInserted;
        String insert = "INSERT INTO ticket(user_id, ticket_type, creation_date) VALUES(?, CAST(? AS ticket_type), ?)";
        try (PreparedStatement statement = con.prepareStatement(insert)) {
            statement.setLong(1, ticket.getUserId());
            statement.setString(2, ticket.getTicketType().name());
            statement.setDate(3, Date.valueOf(ticket.getCreationDate()));
            rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Ticket ticket) throws SQLException {
        int rowsUpdated;
        String update = "UPDATE ticket SET ticket_type=CAST(? AS ticket_type) WHERE id=?";
        try (PreparedStatement statement = con.prepareStatement(update)) {
            statement.setString(1, ticket.getTicketType().name());
            statement.setLong(2, ticket.getId());
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
        String remove = "DELETE FROM ticket WHERE id=?";
        try (PreparedStatement statement = con.prepareStatement(remove)) {
            statement.setLong(1, id);
            rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An existing user was deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Ticket instantiateTicket(ResultSet rs) {
        Ticket ticket = new Ticket();
        try {
            ticket.setId(rs.getLong("id"));
            ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
            ticket.setUserId(rs.getLong("user_id"));
            ticket.setCreationDate(rs.getDate(("creation_date")).toLocalDate());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticket;
    }

}