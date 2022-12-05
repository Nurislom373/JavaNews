package org.khasanof.account;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.tuple.GenerationTiming;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * Date: 12/5/2022
 * Time: 11:55 PM
 */
@ValueGenerationType(generatedBy = UuidValueGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE } )
@Inherited
public @interface GeneratedUuidValue {
    GenerationTiming timing();
}
