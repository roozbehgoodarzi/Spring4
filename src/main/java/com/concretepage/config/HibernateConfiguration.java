package com.concretepage.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Goodarzi on 02/16/2015.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.concretepage.config","com.ocs.spring.service","com.ocs.spring.dao.hibernateImpl","com.ocs.entity" })
//@PropertySources(value = {@PropertySource( "classpath:application.properties" )})
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    private static final String JNDI_NAME = "jdbc/LOC1";

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.ocs.entity" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/smpl");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
//    @Bean
//    @Primary
//    @Resource(name = JNDI_NAME)
//    public DataSource dataSource2() {
//
//        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
//        jndiDataSourceLookup.setResourceRef(true);
//        return jndiDataSourceLookup.getDataSource(JNDI_NAME);
//
//    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
//        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "false");
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}