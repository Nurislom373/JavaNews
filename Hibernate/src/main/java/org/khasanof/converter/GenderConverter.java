package org.khasanof.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.khasanof.converter.enums.Gender;

import java.util.Objects;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 8:32 PM
 * <br/>
 * Package: org.khasanof.converter
 */
@Converter
public class GenderConverter implements AttributeConverter<Gender, Character> {

    @Override
    public Character convertToDatabaseColumn(Gender gender) {
        Objects.requireNonNull(gender, "gender must be not null!");
        return gender.getCode();
    }

    @Override
    public Gender convertToEntityAttribute(Character character) {
        Objects.requireNonNull(character, "character must be not null!");
        return Gender.fromCode(character);
    }

}
