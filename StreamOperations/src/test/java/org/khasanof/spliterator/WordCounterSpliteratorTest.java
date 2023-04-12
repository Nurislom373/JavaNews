package org.khasanof.spliterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/13/2023
 * <br/>
 * Time: 12:12 AM
 * <br/>
 * Package: org.khasanof.spliterator
 */
public class WordCounterSpliteratorTest {

    @Test
    void testRun() {
        var sentence = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        Spliterator<Character> spliterator = new WordCounterSpliterator(sentence);

        Stream<Character> characterStream = StreamSupport.stream(spliterator, false);
        int countWords = countWords(characterStream);

        Assertions.assertEquals(countWords, 12);
    }

    public int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

}
