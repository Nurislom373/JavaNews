package org.khasanof.config.javaBasedConfig;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.khasanof.Model.Car;
import org.khasanof.account.Account;
import org.khasanof.introduction.entity.AuthUserEntity;

import java.util.Properties;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 6:25 PM
 * <br/>
 * Package: org.khasanof.config.javaBasedConfig
 */
public class JavaBasedConfig {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "2004";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String SHOW_SQL = "true";
    private static final String HBM2DDL_AUTO = "update";

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperties(getProperties());
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(AuthUserEntity.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(registry);
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, DRIVER);
        properties.setProperty(Environment.URL, URL);
        properties.setProperty(Environment.USER, USER);
        properties.setProperty(Environment.PASS, PASS);
        properties.setProperty(Environment.DIALECT, DIALECT);
        properties.setProperty(Environment.SHOW_SQL, SHOW_SQL);
        properties.setProperty(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
        return properties;
    }

}
