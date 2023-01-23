package org.khasanof.basicValues.types.blobExample;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/23/2023
 * <br/>
 * Time: 7:37 PM
 * <br/>
 * Package: org.khasanof.basicValues.types.blobExample
 */
@Entity(name = "product")
public class Product {

    @Id
    private Integer id;

    private String name;
    @Lob
    private Blob image;

    public Product() {
    }

    public Product(Integer id, String name, Blob image) {
        this.id = id;
        this.name = name;
        this.image = image;
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
