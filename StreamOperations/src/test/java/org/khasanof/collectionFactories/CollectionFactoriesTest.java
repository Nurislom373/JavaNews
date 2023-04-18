package org.khasanof.collectionFactories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}
