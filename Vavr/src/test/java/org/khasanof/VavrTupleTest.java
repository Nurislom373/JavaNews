package org.khasanof;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/13/2024 2:59 PM
 */
public class VavrTupleTest {

    /**
     *
     */
    @Test
    void firstTestVavrTuple() {
        String T_1 = "Java";
        int T_2 = 8;

        Tuple2<String, Integer> tuple2 = Tuple.of(T_1, T_2);

        assertEquals(tuple2._1(), T_1);
        assertEquals(tuple2._2(), T_2);
    }
}
