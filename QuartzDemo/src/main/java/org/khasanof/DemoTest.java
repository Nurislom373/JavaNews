package org.khasanof;

import org.khasanof.tokenizer.StringTokenizerUtils;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/26/2023 3:37 PM
 */
public class DemoTest {

    public static void main(String[] args) {
        List<String> list = StringTokenizerUtils.getTokensWithList("Hello $World $Jecki", "$", true)
                .stream().filter(s -> s.startsWith("$"))
                .toList();
        System.out.println(list);
    }

}
