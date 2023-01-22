package org.khasanof.introduction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.khasanof.config.xmlBasedConfig.XmlBasedConfig;
import org.khasanof.introduction.entity.AuthUserEntity;
import org.khasanof.introduction.enums.LanguageEnums;
import org.khasanof.introduction.enums.StatusEnum;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 6:25 PM
 * <br/>
 * Package: org.khasanof.introduction
 */
public class Introduction {

    public static void main(String[] args) {

        /*
            Java Based Config
            SessionFactory sessionFactory = JavaBasedConfig.getSessionFactory();
         */


        SessionFactory sessionFactory = XmlBasedConfig.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        AuthUserEntity user = new AuthUserEntity("Akbar", "Akbarov", "akbarakbarov777@gmail.com", 16,
                "the.akbarov", "12344567", LanguageEnums.ENGLISH, StatusEnum.ACTIVE);

        session.persist(user);
        transaction.commit();
    }

}
