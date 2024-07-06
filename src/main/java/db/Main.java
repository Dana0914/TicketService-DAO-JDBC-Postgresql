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
    public static void main(String[] args) {
        MyApplicationContextConfiguration ac;
        Connection connection = null;
        try {
            ac = new MyApplicationContextConfiguration();
            connection = ac.getConnection();
            UserDao userDao = new UserDaoImpl(ac, connection);
            User user1 = new User();
            user1.setId(4L);
            user1.setUsername("Kai");
            user1.setCreationDate(LocalDate.now());
            userDao.update(user1, 3L);
            TicketDao ticketDao = new TicketDaoImpl(ac, connection);
            Ticket ticket1 = new Ticket();
            ticket1.setTicketType(TicketType.YEAR);
            ticket1.setUserId(3L);
            ticket1.setId(4L);
            ticket1.setCreationDate(LocalDate.now());
            //ticketDao.update(ticket1);
            //ticketDao.delete(3L);
            ticketDao.save(ticket1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
