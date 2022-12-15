package org.khasanof;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/15/2022
 * <br/>
 * Time: 5:42 PM
 * <br/>
 * Package: org.khasanof
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface BuilderProcessor {
}
