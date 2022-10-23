package org.khasanof;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ABCTest {

    @Test
    public void createStream() {
        List<String> strings = Arrays.asList("A", "B", "C", "D");

        Optional<String> any = strings.stream().findAny();

        org.junit.jupiter.api.Assertions.assertTrue(any.isPresent());
    }

}
