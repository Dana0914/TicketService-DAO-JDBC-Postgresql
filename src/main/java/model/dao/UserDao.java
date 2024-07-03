package model.dao;

import model.entities.User;


public interface UserDao {
    User getUserById(long id);
    void save(User user);
    void update(User user);
    void delete(long id);
}
