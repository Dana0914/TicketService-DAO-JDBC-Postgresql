package model.dao;

import model.entities.User;

import java.sql.SQLException;


public interface UserDao {
    User getUserById(long id) throws SQLException;
    void save(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(long id) throws SQLException;
}
