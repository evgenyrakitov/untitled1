package service;

import DAO.UserDAO;
import DAO.UserHibernateDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public UserService() {

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

    private static Connection getMysqlConnection() {

        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=Akitov2009").
                    append("&serverTimezone=UTC");


            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
