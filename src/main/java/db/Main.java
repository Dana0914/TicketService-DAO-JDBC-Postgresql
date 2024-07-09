package db;

import model.dao.TicketDao;
import model.dao.UserDao;
import model.entities.Ticket;
import model.entities.TicketType;
import model.entities.User;
import model.impl.TicketDaoImpl;
import model.impl.UserDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.SQLException;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        UserDao userDao = context.getBean(UserDao.class);
        UserDaoImpl userDaoImpl = context.getBean(UserDaoImpl.class);
        User user = context.getBean(User.class);
        user.setUsername("Erick");
        user.setCreationDate(LocalDate.now());
        userDaoImpl.save(user);

        TicketDao ticketDao = context.getBean(TicketDao.class);
        TicketDaoImpl ticketDaoImpl = context.getBean(TicketDaoImpl.class);
        Ticket ticket = new Ticket();
        ticket.setCreationDate(LocalDate.now());
        ticket.setTicketType(TicketType.DAY);
        ticket.setUserId(7L);
        ticketDao.save(ticket);
        ticketDaoImpl.save(ticket);



    }
}