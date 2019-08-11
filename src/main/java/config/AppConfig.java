package config;

import java.util.Properties;

import javax.sql.DataSource;

import entity.Basket;
import entity.OrderDetails;
import entity.Product;
import entity.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { 
      @ComponentScan("dao"),
      @ComponentScan("service")
    })
public class AppConfig {
   private Environment environment;

   @Autowired
   public AppConfig(Environment environment) {
      this.environment = environment;
   }

   @Bean
   public DataSource getDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(environment.getProperty("db.driver"));
      dataSource.setUrl(environment.getProperty("db.url"));
      dataSource.setUsername(environment.getProperty("db.username"));
      dataSource.setPassword("");
      return dataSource;
   }

   @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      factoryBean.setDataSource(getDataSource());
      Properties props=new Properties();
      props.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
      factoryBean.setHibernateProperties(props);
      factoryBean.setAnnotatedClasses(User.class);
      factoryBean.setAnnotatedClasses(Product.class);
      factoryBean.setAnnotatedClasses(Basket.class);
      factoryBean.setAnnotatedClasses(OrderDetails.class);
      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;
   }
}
