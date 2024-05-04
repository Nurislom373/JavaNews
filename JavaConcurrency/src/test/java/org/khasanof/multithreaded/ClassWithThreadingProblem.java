package org.khasanof.multithreaded;

/**
 * @author Nurislom
 * @see org.khasanof.multithreaded
 * @since 5/3/2024 10:30 PM
 */
public class ClassWithThreadingProblem {

    int nextId;

    public int takeNextId() {
        return nextId++;
    }
}
