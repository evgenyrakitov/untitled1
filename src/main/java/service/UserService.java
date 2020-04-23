package service;

import DAO.UserJDBCDAO;
import DAO.UserHibernateDAO;

import Util.DBHelper;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    private UserService() {

    }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }

    public List<User> getAllUser() {
        return new UserHibernateDAO().getAll();
    }

    public void addUser(User user) {
        new UserHibernateDAO().create(user);
    }

    public void  removeUser(User user) {
       new UserHibernateDAO().remove(user);
    }

    public void updateUser(User user) {
        new UserHibernateDAO().update(user);
    }

    public User getUserById(long id) {
        return new UserHibernateDAO().getUserForId(id);
    }

    public void createTable() {
        getUserDAO().createTable();
    }


    private static UserJDBCDAO getUserDAO() {
        return new UserJDBCDAO(DBHelper.getConnection());
    }
}
