package org.khasanof.multiFunctionalInterfaceTest;

import org.junit.jupiter.api.Test;

/**
 * Author: Nurislom
 * <br/>
 * Date: 28.05.2023
 * <br/>
 * Time: 9:05
 * <br/>
 * Package: org.khasanof.multiFunctionalInterfaceTest
 */
public class MultiFunctionalInterfaceTest {

    @Test
    void run() {
        BaseClass.doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("true = " + true);
            }
        });
    }

    @Test
    void exception() {
        BaseClass.doSomething((Runnable) () -> System.out.println("true = " + true));
    }

}

class BaseClass {

    public static void doSomething(Runnable runnable) {
        runnable.run();
    }

    public static void doSomething(Task task) {
        task.execute();
    }

}

interface Task {

    void execute();

}
