package org.khasanof;

import io.vavr.Lazy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/13/2024 3:08 PM
 */
public class VavrLazyTest {

    /**
     *
     */
    @Test
    void firstTestVavrLazy() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertAll(
                "Grouped assertions of Lazy",
                () -> assertFalse(lazy.isEvaluated()), // = false
                () -> assertNotNull(lazy.get()), // random generated
                () -> assertTrue(lazy.isEvaluated()), // = true
                () -> assertNotNull(lazy.get())  // memoized
        );
    }

    /**
     *
     */
    @Test
    void secondTestVavrLazy() {
        CharSequence charSequence = Lazy.val(() -> "Java!", CharSequence.class);
        assertFalse(charSequence.isEmpty());
    }
}
