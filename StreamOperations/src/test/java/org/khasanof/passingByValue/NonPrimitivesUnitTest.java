package org.khasanof.passingByValue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/22/2023
 * <br/>
 * Time: 12:07 PM
 * <br/>
 * Package: org.khasanof.passingPrimativeTypes
 */
public class NonPrimitivesUnitTest {

    /*
        Passing Object References

        In Java, all objects are dynamically stored in Heap space under the hood.
        These objects are referred from references called reference variables.
        A Java object, in contrast to Primitives, is stored in two stages. The reference variables
        are stored in stack memory and the object that they're referring to, are stored in a Heap memory.

        Whenever an object is passed as an argument, an exact copy of the reference variable is created
        which points to the same location of the object in heap memory as the original reference variable.

        As a result of this, whenever we make any change in the same object in the method, that change is
        reflected in the original object. However, if we allocate a new object to the passed reference variable,
        then it won't be reflected in the original object.

        Let's try to comprehend this with the help of a code example:
     */
    @Test
    public void whenModifyingObjects_thenOriginalObjectChanged() {
        Foo a = new Foo(1);
        Foo b = new Foo(1);

        // Before Modification
        assertEquals(a.num, 1);
        assertEquals(b.num, 1);

        modify(a, b);

        // After Modification
        assertEquals(a.num, 2);
        assertEquals(b.num, 1);
    }

    public static void modify(Foo a1, Foo b1) {
        a1.num++;

        b1 = new Foo(1);
        b1.num++;
    }

}

class Foo {
    public int num;

    public Foo(int num) {
        this.num = num;
    }
}
