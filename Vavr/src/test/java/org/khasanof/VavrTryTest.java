package org.khasanof;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/13/2024 2:49 PM
 */
public class VavrTryTest {

    /**
     *
     */
    @Test
    void firstTestWithoutVavrTry() {
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> Divider.divide(10, 0));
        System.out.println("arithmeticException = " + arithmeticException);
    }

    /**
     *
     */
    @Test
    void secondTestWithVavrTry() {
        Try<Integer> tryDivideResult = Divider.divideVavr(10, 0);

        System.out.println("tryDivideResult.isFailure() = " + tryDivideResult.isFailure());
        System.out.println("tryDivideResult.getCause() = " + tryDivideResult.getCause());

        tryDivideResult = Divider.divideVavr(10, 2);

        System.out.println("tryDivideResult.isFailure() = " + tryDivideResult.isFailure());
        System.out.println("tryDivideResult.getCause() = " + tryDivideResult.getOrNull());
    }
}
