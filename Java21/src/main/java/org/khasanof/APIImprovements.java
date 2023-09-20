package org.khasanof;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/20/2023 10:24 PM
 */
public class APIImprovements {

    void emoji() {
        var codePoint = Character.codePointAt("😃", 0);
        var isEmoji = Character.isEmoji(codePoint);
        // prints "😃 is an emoji: true"
        System.out.println("😃 is an emoji: " + isEmoji);
    }

}
