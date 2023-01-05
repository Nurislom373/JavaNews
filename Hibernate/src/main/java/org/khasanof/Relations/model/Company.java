package org.khasanof.Relations.model;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/23/2022
 * <br/>
 * Time: 6:27 PM
 * <br/>
 * Package: org.khasanof.Relations.model
 */
@Entity
@Table(name = "company")
public class Company {

    private static Logger logger = LoggerFactory.getLogger(Company.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Basic(optional = false)
    private String name;

    @Column(name = "address", columnDefinition = "varchar(255) default 'Jack Sparrow 35 street'")
    private String address;

    @Column(name = "phone", columnDefinition = "varchar(255) default '+998993733273'")
    private String phone;

    @Embedded
    private ContactEmbeddable contact;

    public Company() {

    }

    public Company(String name, String address, String phone, ContactEmbeddable contact) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contact = contact;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        Company.logger = logger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContactEmbeddable getContact() {
        return contact;
    }

    public void setContact(ContactEmbeddable contact) {
        this.contact = contact;
    }
}
