package org.khasanof.dao;

import org.khasanof.account.Account;
import org.khasanof.config.javaBasedConfig.JavaBasedConfig;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/17/2022
 * <br/>
 * Time: 11:14 PM
 * <br/>
 * Package: org.khasanof.dao
 */
public class Test {

    public static void main(String[] args) {
        var dao = new AccountDao(JavaBasedConfig.getSessionFactory());

        Account account = dao.get(6L);
        System.out.println("account = " + account);

        boolean any = dao.existById(6L);
        System.out.println("any = " + any);

        List<Account> list = dao.list();
        System.out.println("list = " + list);
    }
}
