package org.khasanof.basicValues.attributeConverter;

import java.util.Arrays;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 8:25 PM
 * <br/>
 * Package: org.khasanof.converter.enums
 */
public enum Gender {
    MALE('M'),
    FEMALE('F');

    private final char code;

    Gender(char code) {
        this.code = code;
    }

    public static Gender fromCode(char code) {
        return Arrays.stream(values()).filter(val -> val.code == code)
                .findFirst().orElseThrow(() -> {
                    throw new UnsupportedOperationException("The code" + code + " is not supported");
                });
    }

    public char getCode() {
        return code;
    }

}
