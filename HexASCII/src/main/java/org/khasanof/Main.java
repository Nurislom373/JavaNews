package org.khasanof;

import static org.khasanof.HexASCIIConverter.fromHex;
import static org.khasanof.HexASCIIConverter.toHex;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/1/26
 */
public class Main {

    public static void main(String[] args) {
        String encodedText = toHex("Hello");
        System.out.println(encodedText);

        String decodedText = fromHex(encodedText);
        System.out.println(decodedText);
    }
}
