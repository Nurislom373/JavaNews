package org.khasanof.basicValues.formulaAnnotation;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/23/2023
 * <br/>
 * Time: 2:53 PM
 * <br/>
 * Package: org.khasanof.converter.entity
 */
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "credit", nullable = false)
    private Double credit;

    @Column(name = "rate", nullable = false)
    private Double rate;

    @Formula(value = "credit * rate")
    private Double interest;

    public Account() {
    }

    public Account(Double credit, Double rate) {
        this.credit = credit;
        this.rate = rate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", credit=" + credit +
                ", rate=" + rate +
                ", interest=" + interest +
                '}';
    }
}
