package org.khasanof.basicValues.columnAnnotation;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 10:55 PM
 */
@Entity
@Table(name = "car", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true, length = 11)
    private int id;

    @Column(name = "model", nullable = false, length = 120)
    private String model;

    @Column(name = "price", nullable = false, length = 10)
    private Long price;

    private LocalDate date;


    public Car(String model, Long price, LocalDate date) {
        this.model = model;
        this.price = price;
        this.date = date;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
