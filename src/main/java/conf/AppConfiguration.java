package conf;

import db.MyApplicationContextConfiguration;
import model.dao.TicketDao;
import model.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

public class AppConfiguration {
    ApplicationContext appContext = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
    UserDao userDao = appContext.getBean(UserDao.class);
    TicketDao ticketDao = appContext.getBean(TicketDao.class);
    Connection dbConnection = appContext.getBean(Connection.class);
}
