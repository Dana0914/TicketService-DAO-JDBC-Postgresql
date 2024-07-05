package model.dao;

import model.entities.Ticket;

public interface TicketDao {
    void findTicketById(long id);
    void findByUserId(long userId);
    void save(Ticket ticket);
    void update(Ticket ticket);
    void deleteByTicketId(long id);
    void deleteByUsertId(long id);
}
