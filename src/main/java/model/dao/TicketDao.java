package model.dao;

import model.entities.Ticket;


public interface TicketDao {
    Ticket getTicketById(long id);
    Ticket getTicketByUserId(long userId);
    void save(Ticket ticket);
    void update(Ticket ticket);
    void delete(long id);
}
