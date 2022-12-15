package org.khasanof;

import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Product product = new Product("fdjkbfs", 213);
        System.out.println("product = " + product);

        String name = product.getClass().getPackageName();
        System.out.println("name = " + name);

        Set<Class> classes = new FindAllClassesReflectionLibrary()
                .findAllClassesUsingReflectionsLibrary("org.khasanof");
        System.out.println("classes = " + classes);


    }
}