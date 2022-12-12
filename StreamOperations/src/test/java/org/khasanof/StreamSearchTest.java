package org.khasanof;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/12/2022
 * <br/>
 * Time: 8:27 PM
 * <br/>
 * Package: org.khasanof
 */
public class StreamSearchTest {

    @Test
    public void streamFindAnyMethodTest() {
        List<String> list = List.of("Bob", "Kane", "Marie", "Janne", "Akbar");

        Optional<String> any = list.stream().findAny();

        Assertions.assertTrue(any.isPresent());
        org.assertj.core.api.Assertions.assertThat(any.get()).isEqualTo("Bob");
    }

    @Test
    public void streamFindAnyMethodParallelTest() {
        List<String> list = List.of("Bob", "Kane", "Marie", "Janne", "Akbar");

        Optional<String> optional = list.stream()
                .parallel().findAny();

        Optional<String> any = list.parallelStream()
                .findAny();

        org.assertj.core.api.Assertions.assertThat(optional.get()).isNotNull();
        System.out.println(optional.get());
        System.out.println(any.get());
    }

    @Test
    public void whenFilterListWithCollectionOfPredicatesUsingAnd() {
        List<String> list = List.of("Kane", "Messi", "Ronaldo", "Pedro", "Depay", "Pique", "Umtiti", "Iniesta");

        var predicates = new ArrayList<Predicate<String>>();
        predicates.add(str -> str.length() < 6);
        predicates.add(str -> str.contains("e"));
        predicates.add(str -> str.startsWith("P"));

        List<String> result = list.stream()
                .filter(predicates.stream().reduce(x -> true, Predicate::and))
                .toList();

        Assertions.assertEquals(result.size(), 2);
        System.out.println("result = " + result);
    }

}
