package Util;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static DBHelper dbHelper;

    private DBHelper() {

    }

    private static Connection connection;

    private static org.hibernate.cfg.Configuration configuration;

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            synchronized (DBHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper();
                }
            }
        }
        return dbHelper;
    }
    public static Connection getConnection() {

        if (connection == null) {
            synchronized (Connection.class) {
                if (connection == null) {
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

                        connection = DriverManager.getConnection(url.toString());
                    } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        e.printStackTrace();
                        throw new IllegalStateException();
                    }
                }
            }
        }
        return connection;
    }

    public static Configuration getConfiguration() {
        if (configuration == null) {
            synchronized (Configuration.class) {
                if (configuration == null) {
                    try {
                        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
                        configuration.addAnnotatedClass(User.class);

                    } catch (HibernateException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return configuration;
    }
}
