package org.khasanof.model;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/11/2023
 * <br/>
 * Time: 11:14 AM
 * <br/>
 * Package: org.khasanof.model
 */
public class Dish {

    private String name;
    private Integer calories;
    private Integer price;

    public Dish(String name, Integer calories, Integer price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", price=" + price +
                '}';
    }
}
