package org.khasanof.model;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/17/2023
 * <br/>
 * Time: 9:41 AM
 * <br/>
 * Package: org.khasanof.model
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
