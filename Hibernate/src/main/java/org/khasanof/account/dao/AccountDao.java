package org.khasanof.account.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.khasanof.account.Account;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/17/2022
 * <br/>
 * Time: 10:45 PM
 * <br/>
 * Package: org.khasanof.dao
 */
public class AccountDao {

    private final SessionFactory sessionFactory;

    public AccountDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Account account) {
        notNull(account, "object is must be not null!");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveAll(Collection<Account> accounts) {
        notNull(accounts, "object is must be not null!");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            accounts.forEach(session::persist);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Account account) {
        notNull(account, "object is must be not null!");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String hql = "UPDATE Account set owner = :owner, money = :money WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("owner", account.getOwner());
            query.setParameter("money", account.getMoney());
            query.setParameter("id", account.getId());
            int update = query.executeUpdate();
            System.out.println("update = " + update);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean existById(Long id) {
        Transaction transaction = null;
        Account account = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Account WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            Object result = query.getSingleResult();

            if (Objects.isNull(result)) {
                return false;
            }
            account = (Account) result;

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void delete(Long id) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM Account WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int update = query.executeUpdate();
            System.out.println("update = " + update);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Account get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Account.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Account> list() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Account ORDER BY id ASC", Account.class)
                    .list();
        }
    }

    private void notNull(Object o, String message) {
        if (Objects.isNull(o)) {
            throw new RuntimeException(message);
        }
    }
}
