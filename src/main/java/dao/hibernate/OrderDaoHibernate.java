package dao.hibernate;

import dao.OrderDetailsDAO;
import entity.OrderDetails;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import utils.HibernateUtil;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class OrderDaoHibernate implements OrderDetailsDAO {
    private static final Logger logger = Logger.getLogger(OrderDaoHibernate.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void addOrder(OrderDetails orderDetails) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(orderDetails);
            transaction.commit();
            logger.info("Order added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error adding order.", e);
        }
    }

    @Transactional
    @Override
    public Optional<OrderDetails> getById(Long orderID) {
        Session session = sessionFactory.getCurrentSession();
        OrderDetails orderDetails = session.get(OrderDetails.class, orderID);
        return Optional.ofNullable(orderDetails);
    }

    @Transactional
    @Override
    public Optional<OrderDetails> getUsersOrder(User user) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<OrderDetails> query = session.createQuery("from orders " +
                "where user = :user order by id desc");
        query.setParameter("user", user);
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((OrderDetails) resultList.get(0));
        }
    }
}
