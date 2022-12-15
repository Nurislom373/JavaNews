package org.khasanof;

import com.google.common.reflect.ClassPath;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
//import org.reflections.Reflections;
//import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/15/2022
 * <br/>
 * Time: 6:42 PM
 * <br/>
 * Package: org.khasanof
 */
public class FindAllClassesReflectionLibrary {

    public Set<Class> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return new HashSet<>(reflections.getTypesAnnotatedWith(BuilderProcessor.class));
//        return reflections.getSubTypesOf(Object.class)
//                .stream()
//                .collect(Collectors.toSet());
    }

    public static Set<Class> findAllClassesUsingGoogleGuice(String packageName) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getClass().isAnnotationPresent(BuilderProcessor.class))
                .map(clazz -> clazz.load())
                .collect(Collectors.toSet());
    }

}
