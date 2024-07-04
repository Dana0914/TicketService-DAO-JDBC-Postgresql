package model.dao;

import model.entities.User;



public interface UserDao {
    void findById(long id);
    void save(User user);
    void update(User user, long id);
    void delete(long id);
}
