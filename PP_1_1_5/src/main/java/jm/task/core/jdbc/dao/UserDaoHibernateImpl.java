package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.model.core.ID;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private jm.task.core.jdbc.util.Util Util;

    public UserDaoHibernateImpl() {

    }


    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("Create table if not EXISTS User (ID bigint AUTO_INCREMENT PRIMARY KEY,  Name varchar(25), LastName varchar(30), Age tinyint (100))").executeUpdate();
            session.getTransaction().commit();
        }

    }


    public void dropUsersTable () {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP table if EXISTS User ").executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void saveUser (String name, String lastName, byte age) throws SQLException {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setName(name);;
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();
        }
    }



    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> users = (List<User>) Util.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            //session.createQuery("DELETE  from User u").executeUpdate();
            session.createQuery("DELETE User ").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
