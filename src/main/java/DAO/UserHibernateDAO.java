package DAO;


import Util.UserDaoFactory;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class UserHibernateDAO implements DAO{


    @Override
    public void create(User user) {
        Session session = new UserDaoFactory().getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = new UserDaoFactory().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAll() {
        List<User> userList;
        userList = (List<User>) new UserDaoFactory().getSessionFactory().openSession().createQuery("From User").list();
        return userList;
    }

    @Override
    public User getUserForId(long id) {
        return new UserDaoFactory().getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public void remove(User user) {
        Session session = new UserDaoFactory().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
