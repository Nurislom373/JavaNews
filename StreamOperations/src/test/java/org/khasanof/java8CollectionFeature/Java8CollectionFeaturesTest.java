package org.khasanof.java8CollectionFeature;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 07.05.2023
 * <br/>
 * Time: 6:17
 * <br/>
 * Package: org.khasanof.java8CollectionFeature
 */
public class Java8CollectionFeaturesTest {

    @Test
    void concurrentModificationException() {
        assertThrows(ConcurrentModificationException.class, this::enhacedLoopTest);
        assertThrows(ConcurrentModificationException.class, this::iteratorTest);
    }

    void enhacedLoopTest() {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (Integer integer : integers) {
            if (integer % 2 == 0) {
                integers.remove(integer);
            }
        }
    }

    void iteratorTest() {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext(); ) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                integers.remove(next);
            }
        }
    }

    @Test
    void removeIteratorTest() {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext(); ) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }

        assertNotEquals(integers.size(), 10);
        assertEquals(integers.size(), 5);
    }

    @Test
    void removeListFromRemoveIfMethodTest() {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        integers.removeIf((f) -> f % 2 == 0);

        assertNotEquals(integers.size(), 10);
        assertEquals(integers.size(), 5);
    }

    @Test
    void listIteratorUpdateElement() {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        for (ListIterator<Integer> iterator = integers.listIterator(); iterator.hasNext(); ) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.set(1);
            }
        }

        assertEquals(integers.size(), 10);
    }

    @Test
    void replaceAllMethodTest() {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        integers.replaceAll((f) -> {
            if (f % 2 == 0) {
                return 1;
            }
            return f;
        });

        assertEquals(integers.size(), 10);
        assertEquals(integers.get(1), 1);
    }

}
