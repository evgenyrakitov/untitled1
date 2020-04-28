package Util;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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

    private static SessionFactory sessionFactory;

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



        return connection;
    }

    public static SessionFactory getConfiguration() {
        if (sessionFactory == null) {
            synchronized (Configuration.class) {
                if (sessionFactory == null) {
                    try {
                        Configuration configuration = new Configuration().configure()
                                                                        .addAnnotatedClass(User.class);
                        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                        builder.applySettings(configuration.getProperties());
                        sessionFactory = configuration.buildSessionFactory(builder.build());
                    } catch (HibernateException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sessionFactory;
    }
}
