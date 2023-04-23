package org.khasanof.collectionFactories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/18/2023
 * <br/>
 * Time: 10:20 AM
 * <br/>
 * Package: org.khasanof.collectionFactories
 */
public class CollectionFactoriesTest {

    @Test
    void listFactoriesTest() {
        List<String> listNewArraylist = new ArrayList<>();
        listNewArraylist.add("Raphael");
        listNewArraylist.add("Olivia");
        listNewArraylist.add("Thibaut");

        List<String> listFactory = Arrays.asList("Raphael", "Olivia", "Thibaut");

        Assertions.assertEquals(listNewArraylist.size(), listFactory.size());
        Assertions.assertEquals(listNewArraylist.get(0), listFactory.get(0));
    }

    @Test
    void arraysAsListUnsupportedOperationException() {
        List<String> friends = Arrays.asList("Raphael", "Olivia");
        friends.set(0, "Rishard");
        friends.add("thibuat");
    }

    @Test
    void createSetWithArraysAsList() {
        Set<String> collectWithColFac = new HashSet<>(Arrays.asList("Nurislom", "Abror", "MuhammadQodir"));
        Set<String> collectWithStream = Stream.of("Nurislom", "Abror", "MuhammadQodir")
                .collect(Collectors.toSet());

        Assertions.assertEquals(collectWithStream.size(), collectWithColFac.size());
    }

    @Test
    void createMapFactoryMethod() {
        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println(ageOfFriends);

        Map<String, Integer> stringIntegerMap = Map.ofEntries(Map.entry("Raphael", 30),
                Map.entry("Olivia", 20),
                Map.entry("Thibaut", 36));
        System.out.println(stringIntegerMap);
    }

}
