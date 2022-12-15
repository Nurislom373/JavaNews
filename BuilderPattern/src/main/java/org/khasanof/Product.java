package org.khasanof;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/15/2022
 * <br/>
 * Time: 5:58 PM
 * <br/>
 * Package: org.khasanof
 */

@BuilderProcessor
public class Product {
    private String name;
    private Integer price;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
