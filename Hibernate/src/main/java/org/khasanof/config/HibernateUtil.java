package org.khasanof.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 11:05 PM
 */
public class HibernateUtil {

    private static SessionFactory sessionAnnotationFactory;

    private static SessionFactory buildSessionAnnotationFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate-annotation.cfg.xml");
        System.out.println("Hibernate Annotation Configuration loaded");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        System.out.println("Hibernate Annotation serviceRegistry created");

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionAnnotationFactory() {
        if (sessionAnnotationFactory == null) {
            sessionAnnotationFactory = buildSessionAnnotationFactory();
        }
        return sessionAnnotationFactory;
    }

}
