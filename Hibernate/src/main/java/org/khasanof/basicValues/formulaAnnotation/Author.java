package org.khasanof.basicValues.formulaAnnotation;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/23/2023
 * <br/>
 * Time: 3:31 PM
 * <br/>
 * Package: org.khasanof.basicValues.formulaAnnotation
 */
@Entity
@Table(name = "author", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    private LocalDate dateOfBirth;

    @Formula(value = "date_part('year', age(dateOfBirth))")
    private int age;

    public Author() {
    }

    public Author(LocalDate dateOfBirth, int age) {
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
