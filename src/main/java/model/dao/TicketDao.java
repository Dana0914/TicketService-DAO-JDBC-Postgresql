package model.dao;

import model.entities.Ticket;


import java.sql.SQLException;


public interface TicketDao {
    Ticket getTicketById(long id) throws SQLException;
    Ticket getTicketByUserId(long userId) throws SQLException;
    void save(Ticket ticket) throws SQLException;
    void update(Ticket ticket) throws SQLException;
    void delete(long id) throws SQLException;
    void deleteUserId(long id) throws SQLException;
}
