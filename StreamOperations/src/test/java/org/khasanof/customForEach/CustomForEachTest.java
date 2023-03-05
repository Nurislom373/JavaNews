package org.khasanof.customForEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/5/2023
 * <br/>
 * Time: 5:32 PM
 * <br/>
 * Package: org.khasanof.customForEach
 */
public class CustomForEachTest {

    @Test
    void customForEachTest() {
        Stream<String> initialStream = Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck");
        List<String> result = new ArrayList<>();

        CustomForEach.forEach(initialStream, (elem, breaker) -> {
            if (elem.length() % 2 == 0) {
                breaker.stop();
            } else {
                result.add(elem);
            }
        });

        assertEquals(asList("cat", "dog"), result);
    }

}
