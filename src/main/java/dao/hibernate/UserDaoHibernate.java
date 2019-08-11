package dao.hibernate;

import dao.UsersDAO;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoHibernate implements UsersDAO {
    private static final Logger logger = Logger.getLogger(UserDaoHibernate.class);
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoHibernate(SessionFactory sessionFactory, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        String saltedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(saltedPassword);
        session.save(user);
        logger.info(user + " created");
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM users", User.class).list();
        }
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public void removeUser(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        if (user != null) {
            session.delete(user);
            logger.info(user + " remover");
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> query = session.createQuery("from users where email = :email");
        query.setParameter("email", email);
        User user = query.getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<User> query = session.createQuery("from users where login = :login");
        query.setParameter("login", login);
        User user = query.getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public void updateUser(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        String saltedPassword = bCryptPasswordEncoder.encode(updatedUser.getPassword());
        updatedUser.setPassword(saltedPassword);
        session.update(updatedUser);
        logger.info(updatedUser + " updated");
    }
}
