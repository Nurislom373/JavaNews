package org.khasanof;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/26/2023 3:12 PM
 */
public class Repository {

    private final Map<String, Integer> integerMap = new HashMap<>();

    public void add(String key, Integer value) {
        this.integerMap.put(key, value);
    }

    public Integer getKey(String key) {
        return this.integerMap.get(key);
    }

}
