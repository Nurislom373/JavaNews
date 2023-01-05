package org.khasanof.Relations.test;

import org.khasanof.Relations.GenericDao;
import org.khasanof.Relations.model.Company;
import org.khasanof.Relations.model.ContactEmbeddable;
import org.khasanof.config.javaBasedConfig.JavaBasedConfig;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/23/2022
 * <br/>
 * Time: 8:12 PM
 * <br/>
 * Package: org.khasanof.Relations.test
 */
public class Test {
    public static void main(String[] args) {
        GenericDao<Company, Integer> dao = new GenericDao<>(JavaBasedConfig.getSessionFactory());

        Company obj = new Company("Boom", "PDP", "+998993733273",
                new ContactEmbeddable("Nurislom", "Xasanov"));

        dao.save(obj);

        List<Company> list = dao.list();
        System.out.println("list = " + list);
    }
}
