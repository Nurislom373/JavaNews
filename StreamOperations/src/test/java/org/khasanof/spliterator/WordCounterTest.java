package org.khasanof.spliterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/12/2023
 * <br/>
 * Time: 11:16 PM
 * <br/>
 * Package: org.khasanof.spliterator
 */
public class WordCounterTest {

    @Test
    void wordCounterTest() {
        var sentence = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";

        Stream<Character> characterStream = IntStream.range(0, sentence.length())
                .mapToObj(sentence::charAt);

        int countWords = countWords(characterStream);

        Assertions.assertEquals(countWords, 12);
    }

    public int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

}
