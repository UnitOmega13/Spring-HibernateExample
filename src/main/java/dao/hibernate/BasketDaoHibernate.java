package dao.hibernate;

import dao.BasketDAO;
import entity.Basket;
import entity.Product;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class BasketDaoHibernate implements BasketDAO {

    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(BasketDaoHibernate.class);

    @Autowired
    public BasketDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createBasket(Basket basket) {
        Session session = sessionFactory.getCurrentSession();
        session.save(basket);
        logger.info(basket + " created");
    }

    @Override
    public int size(Basket basket) {
        return basket.getSizeOfBasket();
    }

    @Override
    public Optional<Basket> getUserBasket(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM basket where Users = :user");
            query.setParameter("user", user);
            Basket basket = (Basket) query.uniqueResult();
            return Optional.of(basket);
        } catch (Exception e) {
            logger.error("Error getting basket" + user, e);
        }
        return Optional.empty();
    }

    @Override
    public void addProductToBasket(Basket basket, Product product) {
        Session session = sessionFactory.getCurrentSession();
        basket.getProducts().add(product);
        session.update(basket);
        logger.info("Added " + product + " in basket " + basket);
    }

}
