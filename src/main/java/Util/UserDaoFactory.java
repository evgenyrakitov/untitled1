package Util;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class UserDaoFactory {

    private static SessionFactory sessionFactory;
    static String daoType;
    public UserDaoFactory() {
    }

    public static SessionFactory getSessionFactory() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            daoType = property.getProperty("daoType");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(daoType.equals("hibernate")) {
            if (sessionFactory == null) {
                try {
                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                    builder.applySettings(DBHelper.getConfiguration().getProperties());
                    sessionFactory = DBHelper.getConfiguration().buildSessionFactory(builder.build());
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        } else {
            sessionFactory = (SessionFactory) DBHelper.getConnection();
        }
        return sessionFactory;
    }
}
