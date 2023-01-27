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
    private String type;
    private boolean isVegetarian;

    public Dish(String name, Integer calories, Integer price, String type, boolean isVegetarian) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.type = type;
        this.isVegetarian = isVegetarian;
    }

    public Dish(String name, Integer calories, Integer price, String type) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", isVegetarian=" + isVegetarian +
                '}';
    }
}
