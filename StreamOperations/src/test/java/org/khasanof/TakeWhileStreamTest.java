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
         The processing is done as long as our predicate is matched and the initial stream still has elements. When
         either of the conditions becomes false, our Spliterator “breaks” and the streaming operation ends.
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
