package db;

import model.dao.TicketDao;
import model.dao.UserDao;
import model.entities.Ticket;
import model.entities.TicketType;
import model.entities.User;
import model.impl.TicketDaoImpl;
import model.impl.UserDaoImpl;


import java.sql.SQLException;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User user1 = new User();
        User user2 = new User();
        user1.setId(4);
        user1.setUsername("Alex");
        user1.setCreationDate(LocalDate.now());
        user2.setId(5);
        user2.setUsername("Max");
        user2.setCreationDate(LocalDate.now());
        userDao.save(user1);
        userDao.save(user2);
        TicketDao ticketDao = new TicketDaoImpl();
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        ticket1.setTicketType(TicketType.DAY);
        ticket1.setUserId(4L);
        ticket1.setId(4L);
        ticket2.setTicketType(TicketType.WEEK);
        ticket1.setCreationDate(LocalDate.now());
        ticket2.setCreationDate(LocalDate.now());
        ticket2.setUserId(3L);
        ticket2.setId(5L);
        ticketDao.save(ticket1);
        ticketDao.save(ticket2);


    }
}
