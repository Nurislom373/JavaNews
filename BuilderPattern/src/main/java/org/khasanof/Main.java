package org.khasanof;

import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello world!");
//
//        Product product = new Product("fdjkbfs", 213);
//        System.out.println("product = " + product);
//
//        String name = product.getClass().getPackageName();
//        System.out.println("name = " + name);
//
//        Set<Class> classes = new FindAllClassesReflectionLibrary()
//                .findAllClassesUsingReflectionsLibrary("org.khasanof");
//        System.out.println("classes = " + classes);


        int i = 10;

        doSomething(i);


    }

    public static void doSomething(long i) {
        System.out.println("long i = " + i);
    }

    public static void doSomething(Integer i) {
        System.out.println("Integer i = " + i);
    }  //It will be matched, if no match after widening.

    public static void doSomething(int... i) {
        System.out.println("int.. i = " + Arrays.toString(i));
    }
}