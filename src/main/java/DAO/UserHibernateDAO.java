package DAO;


import Util.DBHelper;
import Util.UserDaoFactory;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.*;

public class UserHibernateDAO implements DAO{




    @Override
    public void create(User user) {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction =session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAll() {
        List<User> userList;
        userList = (List<User>) DBHelper.getConfiguration().openSession().createQuery("From User").list();
        return userList;
    }

    @Override
    public User getUserForId(long id) {
        return DBHelper.getConfiguration().openSession().get(User.class, id);
    }

    @Override
    public void remove(User user) {
        Session session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }





}
