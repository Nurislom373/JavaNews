package org.khasanof;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product(23, "Apple"),
                new Product(13, "Lemon"),
                new Product(30, "Banana"),
                new Product(31, "Bread"),
                new Product(14, "Sugar")
        );

        // get all products name with list
        List<String> collect = products.stream()
                .map(Product::getName)
                .toList();
        System.out.println("collect = " + collect);


        // get all products average price
        Double averagePrice = products.stream()
                .collect(Collectors.averagingInt(Product::getPrice));
        System.out.println("averagePrice = " + averagePrice);

        // all product price
        Integer summingPrice = products.stream()
                .collect(Collectors.summingInt(Product::getPrice));
        System.out.println("summingPrice = " + summingPrice);

        // Custom Collector
        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add, (first, second) -> {
                    first.addAll(second);
                    return first;
                });

        LinkedList<Product> collectToLinkedList = products.stream().collect(toLinkedList);
        System.out.println("collectToLinkedList = " + collectToLinkedList);

        // Big Price
        Stream<Product> productStream = products.parallelStream();
        boolean parallel = productStream.isParallel();
        System.out.println("parallel = " + parallel);
        boolean bigPrice = productStream
                .map(product -> product.getPrice() * 12)
                .anyMatch(price -> price > 200);
        System.out.println("bigPrice = " + bigPrice);
    }
}
