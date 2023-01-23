package org.khasanof.basicValues.formulaAnnotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.khasanof.config.javaBasedConfig.JavaBasedConfig;

import java.time.LocalDate;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/23/2023
 * <br/>
 * Time: 2:58 PM
 * <br/>
 * Package: org.khasanof.converter
 */
public class FormulaMain {

    public static void main(String[] args) {
        SessionFactory sessionFactory = JavaBasedConfig.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {

//            Transaction transaction = session.beginTransaction();
//
//            var acc = new Account(23.0, 543.75);
//
//            session.persist(acc);
//            transaction.commit();
//
//            //
//            getAccount(session);

            authorTest(session);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void getAccount(Session session) {

        Account account = session.find(Account.class, 1L);

        System.out.println(account);
    }

    static void authorTest(Session session) {

        var author = new Author();
        author.setDateOfBirth(LocalDate.of(2004, 10, 18));

        Transaction transaction = session.beginTransaction();
        session.persist(author);
        transaction.commit();

        Author auth = session.find(Author.class, 1L);
        System.out.println("auth = " + auth);
    }

}
