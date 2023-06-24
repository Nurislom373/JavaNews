package org.khasanof.date_time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * @author <a href="https://github.com/Nurislom373">Nurislom</a>
 * @see org.khasanof.date_time
 * @since 24.06.2023 14:48
 */
public class LocalDateTest {

    @Test
    void localDateTest() {
        LocalDate localDate = LocalDate.of(2023, 6, 24);
        System.out.println("localDate = " + localDate);
        System.out.println("localDate.getYear() = " + localDate.getYear());
        System.out.println("localDate.getMonth() = " + localDate.getMonth());
        System.out.println("localDate.getDayOfMonth() = " + localDate.getDayOfMonth());
        System.out.println("localDate.getDayOfWeek() = " + localDate.getDayOfWeek());
        System.out.println("localDate.getDayOfYear() = " + localDate.getDayOfYear());
        System.out.println("localDate.lengthOfMonth() = " + localDate.lengthOfMonth());
        System.out.println("localDate.isLeapYear() = " + localDate.isLeapYear());

        int year = localDate.get(ChronoField.YEAR);
        System.out.println("year = " + year);
        int month = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("month = " + month);
        int day = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("day = " + day);
    }

    @Test
    void locateTimeTest() {
        LocalTime time = LocalTime.of(13, 45, 20);
        System.out.println("time = " + time);
        int hour = time.getHour();
        System.out.println("hour = " + hour);
        int minute = time.getMinute();
        System.out.println("minute = " + minute);
        int second = time.getSecond();
        System.out.println("second = " + second);
    }

    @Test
    void localDateTimeTest() {
        LocalTime time = LocalTime.of(13, 45, 20);
        LocalDate date = LocalDate.of(2023, 6, 24);
        // 2017-09-21T13:45:20
        LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
        System.out.println("dt1 = " + dt1);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        System.out.println("dt2 = " + dt2);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        System.out.println("dt3 = " + dt3);
        LocalDateTime dt4 = date.atTime(time);
        System.out.println("dt4 = " + dt4);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println("dt5 = " + dt5);
    }

    @Test
    void periodTest() {
        Period period = Period.between(LocalDate.of(2023, 6, 25),
                LocalDate.of(2023, 6, 24));
        System.out.println("period = " + period);
        System.out.println("period.getDays() = " + period.getDays());
        System.out.println("period.getMonths() = " + period.getMonths());
        System.out.println("period.isNegative() = " + period.isNegative());
        System.out.println("period.isZero() = " + period.isZero());
    }

}
