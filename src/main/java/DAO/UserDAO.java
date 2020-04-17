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

    public List<User> getAllUser() {
        List <User> users = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                Long id = result.getLong("id");
                String login = result.getString("login");
                String email = result.getString("email");
                String pass = result.getString("password");
                users.add(new User(id, login, email, pass));
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public void addUser(User user) {
        PreparedStatement prstmt = null;
        try {
            prstmt = connection.prepareStatement("insert into users (login, email, password) values (?, ?, ?)");
            prstmt.setString(1, user.getLogin());
            prstmt.setString(2, user.getEmail());
            prstmt.setString(3, user.getPassword());
            prstmt.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
        }
        finally {
            try {
                prstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUser(User user) {
        long id = user.getId();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("delete from users where id=?");
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser(User user) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("update users set login=?, email=?, password=? where id=?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserById(long id) {
        User user = new User();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from users where id=?");
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            String login = result.getString("login");
            String email = result.getString("email");
            String pass = result.getString("password");
            result.close();
            stmt.close();
            user.setId(id);
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(pass);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return user;
    }

    public void createTable() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("create table if not exists users (id bigint auto_increment, login varchar(256), email varchar(256), password varchar(256), primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
