package model.service;

import model.dao.TicketDao;
import model.dao.UserDao;
import model.entities.Ticket;
import model.entities.User;
import model.impl.TicketDaoImpl;
import model.impl.UserDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class).addAnnotatedClass(Ticket.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Ticket ticket = new Ticket();
            TicketDao ticketDao = new TicketDaoImpl(sessionFactory);
            UserDao userDao = new UserDaoImpl(sessionFactory);
            User user = new User();
            user.setUsername("Kai");
            user.setCreationDate(LocalDate.of(2024, 5, 5));
            userDao.delete(4L);
            userDao.save(user);
            userDao.update(user, 3L);
            userDao.findById(1L);

            ticket.setTicketType("WEEK");
            ticket.setUserId(3L);
            ticket.setCreationDate(LocalDate.now());
            ticket.setId(2L);
            ticketDao.delete(ticket.getId());
            ticket.setId(2L);
            ticket.setTicketType("YEAR");
            ticketDao.update(ticket);
            ticketDao.save(ticket);
            ticketDao.findById(1);
            ticketDao.findByUserId(1);

        }
    }
}

