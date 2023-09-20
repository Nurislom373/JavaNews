package org.khasanof;

import java.util.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/20/2023 6:38 AM
 */
public class SequencedCollection {

    public static void main(String[] args) {
        sequencedList();
        sequencedSet();
    }

    private static void sequencedList() {
        List<String> strings = new ArrayList<>();
        strings.addFirst("First");
        strings.addFirst("Last");
        System.out.println(strings);
        strings.reversed();
        System.out.println(strings);
    }

    private static void sequencedSet() {
        LinkedHashSet<String> strings = new LinkedHashSet<>();
        strings.addFirst("First");
        strings.addFirst("Last");
        System.out.println(strings);
        strings.reversed();
        System.out.println(strings);
    }

}
