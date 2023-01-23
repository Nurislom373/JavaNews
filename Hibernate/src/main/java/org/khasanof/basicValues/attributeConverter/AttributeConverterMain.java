package org.khasanof.basicValues.attributeConverter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.khasanof.config.javaBasedConfig.JavaBasedConfig;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 8:31 PM
 * <br/>
 * Package: org.khasanof.converter
 */
public class AttributeConverterMain {

    public static void main(String[] args) {
        SessionFactory sessionFactory = JavaBasedConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        var person = new Person("Jeck", Gender.MALE);

        session.persist(person);
        transaction.commit();
    }

}
