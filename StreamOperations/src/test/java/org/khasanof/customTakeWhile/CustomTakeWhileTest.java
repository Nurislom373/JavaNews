package org.khasanof.customTakeWhile;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/5/2023
 * <br/>
 * Time: 5:41 PM
 * <br/>
 * Package: org.khasanof.customTakeWhile
 */
public class CustomTakeWhileTest {

    @Test
    public void whenCustomTakeWhileIsCalled_ThenCorrectItemsAreReturned() {
        Stream<String> initialStream =
                Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck");

        List<String> result =
                CustomTakeWhile.takeWhile(initialStream, x -> x.length() % 2 != 0)
                        .collect(Collectors.toList());

        assertEquals(asList("cat", "dog"), result);
    }

}
