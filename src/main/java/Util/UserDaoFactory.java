package Util;


import DAO.DAO;
import DAO.UserHibernateDAO;
import DAO.UserJDBCDAO;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    private DAO daoFactory;
    private String daoType;
    public UserDaoFactory() {
    }

    public DAO getUserDaoFactory() {

        Properties property = new Properties();

        try {
            InputStream inputStream = UserDaoFactory.class.getClassLoader().getResourceAsStream("/db.properties");
            property.load(inputStream);
            daoType = property.getProperty("daoType");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (daoType.equals("hibernate")) {
            daoFactory = new UserHibernateDAO();
        } else if (daoType.equals("JDBC")){
            daoFactory = new UserJDBCDAO(DBHelper.getConnection());
        }
        return daoFactory;
    }
}
