package dao.hibernate;

import dao.DigitalCodeDao;
import entity.DigitalCode;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class DigitalCodeHibernate implements DigitalCodeDao {
    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(DigitalCodeHibernate.class);

    @Autowired
    public DigitalCodeHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(DigitalCode digitalCode) {
        Session session = sessionFactory.getCurrentSession();
        session.save(digitalCode);
        logger.info("Code created and save");
    }

    @Override
    public Optional<DigitalCode> getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        DigitalCode digitalCode = session.get(DigitalCode.class, id);
        return Optional.ofNullable(digitalCode);
    }

    @Override
    public Optional<DigitalCode> getLastCode(User user) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<DigitalCode> query = session.createQuery("from code where user = :user order by id desc ");
        query.setParameter("user", user);
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of((DigitalCode) resultList.get(0));
        }
    }
}
