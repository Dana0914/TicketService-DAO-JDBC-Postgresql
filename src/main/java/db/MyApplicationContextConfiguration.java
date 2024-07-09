package db;


import model.entities.Ticket;
import model.entities.User;
import model.impl.TicketDaoImpl;
import model.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan
public class MyApplicationContextConfiguration {

    public static final String URL = "jdbc:postgresql://localhost:5432/ticketservice";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Bean
    public UserDaoImpl userDaoImpl() throws SQLException {
        return new UserDaoImpl(getConnection());
    }

    @Bean
    public TicketDaoImpl ticketDaoImpl() throws SQLException {
        return new TicketDaoImpl(getConnection());
    }

}
