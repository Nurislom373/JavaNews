package org.khasanof;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.khasanof.Model.Car;

import java.time.LocalDate;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Car car = new Car("Gentra", 12000L, LocalDate.now());

        save(car);

        System.out.println(get(car.getId()));
    }

    public static SessionFactory sessionFactory() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty(Environment.USER, "postgres");
        properties.setProperty(Environment.PASS, "2004");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Car.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void save(Car car) {
        Transaction transaction = null;
        try (Session session = sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(car);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Car get(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Car car = session.find(Car.class, 1L);
            transaction.commit();
            return car;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}