package org.khasanof.customTakeWhile;

import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/5/2023
 * <br/>
 * Time: 5:36 PM
 * <br/>
 * Package: org.khasanof.customTakeWhile
 */
public class CustomTakeWhile {

    public static <T> Stream<T> takeWhile(Stream<T> stream, Predicate<T> predicate) {
        CustomSpliterator<T> customSpliterator = new CustomSpliterator<>(stream.spliterator(), predicate);
        return StreamSupport.stream(customSpliterator, false);
    }

}
