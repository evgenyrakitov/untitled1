package service;

import DAO.UserDAO;
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
        return getUserDAO().getAllUser();
    }

    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    public void  removeUser(User user) {
       getUserDAO().removeUser(user);
    }

    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    public User getUserById(long id) {
        return getUserDAO().getUserById(id);
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
