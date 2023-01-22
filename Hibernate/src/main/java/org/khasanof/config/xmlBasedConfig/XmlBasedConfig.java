package org.khasanof.config.xmlBasedConfig;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 6:25 PM
 * <br/>
 * Package: org.khasanof.config.xmlBasedConfig
 */
public class XmlBasedConfig {

    private static SessionFactory sessionAnnotationFactory;

    private static SessionFactory buildSessionAnnotationFactory() {
        return new Configuration()
                .configure("hibernate-annotation.cfg.xml")
                .buildSessionFactory();
    }

    public static SessionFactory getSessionAnnotationFactory() {
        if (sessionAnnotationFactory == null) {
            sessionAnnotationFactory = buildSessionAnnotationFactory();
        }
        return sessionAnnotationFactory;
    }

}
