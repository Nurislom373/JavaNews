package org.khasanof.Relations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.khasanof.account.Account;

import java.lang.reflect.ParameterizedType;
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
 * Package: org.khasanof.Relations
 */
public class GenericDao<E, I> {

    private Class<E> persistenceClass;
    private final SessionFactory sessionFactory;

    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistenceClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(E entity) {
        notNull(entity, "object is must be not null!");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveAll(Collection<E> entities) {
        notNull(entities, "object is must be not null!");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            entities.forEach(session::persist);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean existById(I id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Account WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            Object result = query.getSingleResult();

            if (Objects.isNull(result)) {
                return false;
            }

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

    public void delete(I id) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM Account WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int update = query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public E get(I id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(persistenceClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<E> list() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Account ORDER BY id ASC", persistenceClass).list();
        }
    }

    private void notNull(Object o, String message) {
        if (Objects.isNull(o)) {
            throw new RuntimeException(message);
        }
    }
}
