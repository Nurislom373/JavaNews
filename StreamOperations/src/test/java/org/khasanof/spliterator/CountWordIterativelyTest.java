package org.khasanof.spliterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/12/2023
 * <br/>
 * Time: 11:01 PM
 * <br/>
 * Package: org.khasanof.spliterator
 */
public class CountWordIterativelyTest {

    @Test
    void countWordsIterativelyTest() {
        var sentence = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        int iteratively = countWordsIteratively(sentence);

        Assertions.assertEquals(iteratively, 12);
    }

    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

}
