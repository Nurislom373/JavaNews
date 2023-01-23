package org.khasanof.basicValues.basicAnnotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.khasanof.config.javaBasedConfig.JavaBasedConfig;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/23/2023
 * <br/>
 * Time: 3:24 PM
 * <br/>
 * Package: org.khasanof.basicValues.basic
 */
public class BasicMain {

    public static void main(String[] args) {
        SessionFactory sessionFactory = JavaBasedConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        var product = new Product();
        product.setName("bjdhsfdsv");

        session.persist(product);
        transaction.commit();
    }

}
