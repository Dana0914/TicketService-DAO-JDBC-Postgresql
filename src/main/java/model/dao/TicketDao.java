package model.dao;

import model.entities.Ticket;




public interface TicketDao {
    void findById(long id);
    void findByUserId(long userId);
    void save(Ticket ticket);
    void update(Ticket ticket);
    void delete(long id);
}
