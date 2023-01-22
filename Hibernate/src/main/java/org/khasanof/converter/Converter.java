package org.khasanof.converter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.khasanof.config.javaBasedConfig.JavaBasedConfig;
import org.khasanof.config.xmlBasedConfig.XmlBasedConfig;
import org.khasanof.converter.entity.Person;
import org.khasanof.converter.entity.Product;
import org.khasanof.converter.enums.Gender;
import org.khasanof.introduction.entity.AuthUserEntity;
import org.khasanof.introduction.enums.LanguageEnums;
import org.khasanof.introduction.enums.StatusEnum;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 8:31 PM
 * <br/>
 * Package: org.khasanof.converter
 */
public class Converter {

    public static void main(String[] args) {
        SessionFactory sessionFactory = JavaBasedConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        var product = new Product();
        product.setName("bjdhsfdsv");

        var person = new Person("Jeck", Gender.MALE);

        session.persist(product);
        transaction.commit();
    }

}
