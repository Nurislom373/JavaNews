package org.khasanof.passingByValue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/22/2023
 * <br/>
 * Time: 12:05 PM
 * <br/>
 * Package: org.khasanof.passingPrimativeTypes
 */

public class PrimitivesUnitTest {

    /*
        Passing Primitive Types

        The Java Programming Language features eight primitive data types.
        Primitive variables are directly stored in stack memory. Whenever any variable of primitive
        data type is passed as an argument, the actual parameters are copied to formal arguments and
        these formal arguments accumulate their own space in stack memory.

        The lifespan of these formal parameters lasts only as long as that method is running,
        and upon returning, these formal arguments are cleared away from the stack and are discarded.
     */
    @Test
    public void whenModifyingPrimitives_thenOriginalValuesNotModified() {
        int x = 1;
        int y = 2;

        // Before Modification
        assertEquals(x, 1);
        assertEquals(y, 2);

        modify(x, y);

        // After Modification
        assertEquals(x, 1);
        assertEquals(y, 2);
    }

    public static void modify(int x1, int y1) {
        x1 = 5;
        y1 = 10;
    }
}
