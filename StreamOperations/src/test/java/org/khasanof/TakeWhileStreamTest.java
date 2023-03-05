package org.khasanof;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/5/2023
 * <br/>
 * Time: 5:14 PM
 * <br/>
 * Package: org.khasanof
 */
public class TakeWhileStreamTest {

    /*
         As Java developers, we often write code that iterates over a set of elements and performs an operation on each
         one. The Java 8 streams library and its forEach method allow us to write that code in a clean, declarative
         manner.

         While this is similar to loops, we are missing the equivalent of the break statement to abort iteration.
         A stream can be very long, or potentially infinite, and if we have no reason to continue processing it, we
         would want to break from it, rather than wait for its last element.
     */

    @Test
    void test_forEach() {
        Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck")
                .filter(n -> n.length() % 2 != 0)
                .forEach(System.out::println);

        //
        List<String> list = List.of("cat", "dog", "elephant", "fox", "rabbit", "duck");
        for (String element : list) {
            if (element.length() % 2 != 0) {
                System.out.println(element);
            }
        }
    }

    @Test
    void test_takeWhile() {
        Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck")
                .takeWhile(n -> n.length() % 2 != 0)
                .forEach(System.out::println);

        //
        List<String> list = List.of("cat", "dog", "elephant", "fox", "rabbit", "duck");
        for (String element : list) {
            if (element.length() % 2 == 0) {
                break;
            }
            System.out.println(element);
        }
    }


}
