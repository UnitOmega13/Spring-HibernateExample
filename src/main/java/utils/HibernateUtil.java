package utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            logger.warn("Error building session", e);
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
