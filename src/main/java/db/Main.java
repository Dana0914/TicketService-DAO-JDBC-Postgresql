package db;

import model.dao.TicketDao;
import model.dao.UserDao;
import model.entities.Ticket;
import model.entities.TicketType;
import model.entities.User;
import model.impl.TicketDaoImpl;
import model.impl.UserDaoImpl;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        UserDao userDao = new UserDaoImpl(db, connection);
        User user1 = new User(1L,"KAI", LocalDate.now());
        userDao.save(user1);
        userDao.update(user1);
        userDao.delete(1L);
        TicketDao ticketDao = new TicketDaoImpl(db, connection);
        Ticket ticket1 = new Ticket(1L, 1L, TicketType.WEEK, LocalDate.now());
        ticketDao.save(ticket1);
        ticketDao.delete(1L);


    }
}
