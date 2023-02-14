package org.khasanof.concurrentHashMap;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/5/2023
 * <br/>
 * Time: 3:07 PM
 * <br/>
 * Package: org.khasanof.concurrentHashMap
 */
public class NullPointerException {

    public static void main(String[] args) {
        int[] array = new int[10];
        array[11] = 1;

        System.out.println("array = " + array);
    }

    <T extends Number> void genericMethod(T t) {

    }

}
