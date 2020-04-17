package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUser() throws SQLException {
        List <User> users = new ArrayList<>();
        Statement stmt = connection.createStatement();
        stmt.execute("select * from users");
        ResultSet result = stmt.getResultSet();
        while (result.next()) {
            Long id = result.getLong("id");
            String login = result.getString("login");
            String email = result.getString("email");
            String pass = result.getString("password");
            users.add(new User(id, login, email, pass));
        }
        result.close();
        stmt.close();
        return users;
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement prstmt = connection.prepareStatement("insert into users (login, email, password) values (?, ?, ?)");
        prstmt.setString(1, user.getLogin());
        prstmt.setString(2, user.getEmail());
        prstmt.setString(3, user.getPassword());
        prstmt.executeUpdate();
        prstmt.close();
    }

    public void removeUser(User user) throws SQLException {
        long id = user.getId();
        PreparedStatement stm = connection.prepareStatement("delete from users where id=?");
        stm.setLong(1, id);
        stm.executeUpdate();
        stm.close();
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("update users set login=?, email=?, password=? where id=?");
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setLong(4, user.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public User getUserById(long id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("select * from users where id=?");
        stmt.setLong(1, id);
        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            String login = result.getString("login");
            String email = result.getString("email");
            String pass = result.getString("password");

            result.close();
            stmt.close();
            return new User(id, login, email, pass);
        } else return null;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, login varchar(256), email varchar(256), password varchar(256), primary key (id))");
        stmt.close();
    }
}
