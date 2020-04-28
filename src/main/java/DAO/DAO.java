package DAO;

import model.User;

import java.util.List;

public interface DAO {

    void create(User user);

    void update(User user);

    List<User> getAll();

    User getUserForId(long id);

    void remove(User user);


}
