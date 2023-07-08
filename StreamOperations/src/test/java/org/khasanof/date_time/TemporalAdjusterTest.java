package org.khasanof.date_time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author Nurislom
 * @see org.khasanof.date_time
 * @since 07.07.2023 11:01
 */
public class TemporalAdjusterTest {

    @Test
    void test() {
        LocalDate localDate = LocalDate.of(2018, 10, 12);
        LocalDate with = localDate.with(new NextWorkingDay());
        System.out.println("with = " + with);
    }

}
