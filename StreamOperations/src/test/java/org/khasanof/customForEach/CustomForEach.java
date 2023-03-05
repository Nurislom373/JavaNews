package org.khasanof.customForEach;

import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/5/2023
 * <br/>
 * Time: 5:28 PM
 * <br/>
 * Package: org.khasanof.customForEach
 */
public class CustomForEach {

    public static class Breaker {
        private boolean shouldBreak = false;

        public void stop() {
            shouldBreak = true;
        }

        public boolean get() {
            return shouldBreak;
        }
    }

    public static <T> void forEach(Stream<T> stream, BiConsumer<T, Breaker> consumer) {
        Spliterator<T> tSpliterator = stream.spliterator();
        boolean hadNext = true;
        Breaker breaker = new Breaker();

        while (hadNext && !breaker.get()) {
            hadNext = tSpliterator.tryAdvance(elem -> {
                consumer.accept(elem, breaker);
            });
        }
    }

}
