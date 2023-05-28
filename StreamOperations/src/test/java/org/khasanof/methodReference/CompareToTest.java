package org.khasanof.methodReference;

import org.junit.jupiter.api.Test;
import org.khasanof.App;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Nurislom
 * <br/>
 * Date: 28.05.2023
 * <br/>
 * Time: 9:32
 * <br/>
 * Package: org.khasanof.methodReference
 */
public class CompareToTest {

    private final List<Apple> list = List.of(
            new Apple(43725, 48325),
            new Apple(8432, 832),
            new Apple(43725, 4738),
            new Apple(1741, 943),
            new Apple(1490629, 5432)
    );

    @Test
    void sortWithLambda() {
        List<Apple> appleList = list.stream().sorted((var1, var2) -> var1.getWeight().compareTo(var2.getWeight()))
                .collect(Collectors.toList());
        System.out.println("appleList = " + appleList);
    }

    @Test
    void sortWithMethodReference() {
        List<Apple> appleList = list.stream().sorted(Comparator.comparing(Apple::getWeight))
                .toList();
        System.out.println("appleList = " + appleList);
    }

}


class Apple implements Comparable<Apple> {

    private Integer weight;
    private Integer price;

    @Override
    public int compareTo(Apple o) {
        return o.weight;
    }

    public Apple(Integer weight, Integer price) {
        this.weight = weight;
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}