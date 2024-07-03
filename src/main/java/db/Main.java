package db;

import model.dao.TicketDao;
import model.dao.UserDao;
import model.entities.Ticket;
import model.entities.TicketType;
import model.entities.User;
import model.impl.TicketDaoImpl;
import model.impl.UserDaoImpl;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        User user1 = new User("Kai", LocalDate.of(2024,7, 3));
        Ticket ticket = new Ticket(3, TicketType.DAY, LocalDate.of(2024,7,3));
        TicketDao dao = new TicketDaoImpl();
        UserDao userDao = new UserDaoImpl();


        System.out.println(dao.getTicketById(2));
        System.out.println(dao.getTicketByUserId(3));

        dao.delete(2);
        dao.save(ticket);
        ticket.setId(4);
        dao.update(ticket);


        System.out.println(userDao.getUserById(2));
        user1.setId(4);
        userDao.save(user1);
        User user2 = new User("Kael", LocalDate.of(2024,2,5));
        user2.setId(4);
        userDao.update(user2);
        userDao.delete(0);




    }
}
