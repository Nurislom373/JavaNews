package org.khasanof;

import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/20/2023 7:42 AM
 */
public class StructuredConcurrency {

    public static void main(String[] args) {
        String subTaskResult = getSubTaskResult();
        System.out.println("subTaskResult = " + subTaskResult);
    }

    private static String getSubTaskResult() {
        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            Supplier<String> greeting = scope.fork(StructuredConcurrency::getGreeting);
            Supplier<String> firstName = scope.fork(StructuredConcurrency::getFirstName);
            StructuredTaskScope.Subtask<String> lastName = scope.fork(StructuredConcurrency::getLastName);

            StructuredTaskScope.Subtask.State state = lastName.state();
            System.out.println("state = " + state);

            scope.join();
            return greeting.get() + firstName.get() + lastName.get();
        } catch (Exception e) {
            throw new RuntimeException("Jeck");
        }
    }
    
    private static String getGreeting() {
        return "Hello ";
    }

    private static String getFirstName() {
        return "John ";
    }

    private static String getLastName() throws InterruptedException {
        Thread.sleep(1000);
        return "Doe";
    }

}
