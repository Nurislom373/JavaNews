package org.khasanof.account;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.tuple.GenerationTiming;
import org.khasanof.config.javaBasedConfig.JavaBasedConfig;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * @author Nurislom
 * Date: 12/5/2022
 * Time: 11:11 PM
 */
@Entity
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private Long id;

    private String owner;
    private Long money;

    @GeneratedUuidValue(timing = GenerationTiming.INSERT)
    private UUID code;

    public Account(String owner, Long money) {
        this.owner = owner;
        this.money = money;
    }

    public Account() {

    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(owner, account.owner) && Objects.equals(money, account.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, money);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", money=" + money +
                '}';
    }
}


class Main {

    public static void main(String[] args) {
        Account account = new Account();
        account.setMoney(90L);
        account.setOwner("Akbar");

        Session openSession = JavaBasedConfig.getSessionFactory().openSession();

//        insert(openSession, account);

        System.out.println(advancedList(openSession));
    }

    public static List<Account> getList(Session session) {
        session.beginTransaction();
        return session.createQuery("from Account", Account.class).list();
    }

    public static List<Account> advancedList(Session session) {
        session.beginTransaction();
        return session.createQuery("from Account where money > :money and owner = :owner", Account.class)
                .setParameter("money", 10)
                .setParameter("owner", "Nurislom")
                .list();
    }

    public static void insert(Session session, Account account) {
        session.beginTransaction();
        session.persist(account);
        session.getTransaction().commit();
    }

    public static void delete(Session session, Account account) {

    }

}
