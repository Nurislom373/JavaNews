package org.khasanof.date_time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * @author Nurislom
 * @see org.khasanof.date_time
 * @since 07.07.2023 11:14
 */
public class PrintingAndParsingTest {

    @Test
    void test() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("s1 = " + s1);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("s2 = " + s2);
    }

    @Test
    void testParsing() {
        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("date1 = " + date1);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("date2 = " + date2);
    }

    @Test
    void customDateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        System.out.println("date1 = " + date1);
        String formattedDate = date1.format(formatter);
        System.out.println("formattedDate = " + formattedDate);
        LocalDate date2 = LocalDate.parse(formattedDate, formatter);
        System.out.println("date2 = " + date2);
    }

    @Test
    void localLanFormatter() {
        DateTimeFormatter italianFormatter =
                DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.US);
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String formattedDate = date1.format(italianFormatter); // 18. marzo 2014
        System.out.println("formattedDate = " + formattedDate);
        LocalDate date2 = LocalDate.parse(formattedDate, italianFormatter);
        System.out.println("date2 = " + date2);
    }

    @Test
    void islamicCalendar() {
        HijrahDate now = HijrahDate.now();
        System.out.println("now = " + now);
        HijrahDate ramadanDate =
                HijrahDate.now().with(ChronoField.DAY_OF_MONTH, 1)
                        .with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("Ramadan starts on " +
                IsoChronology.INSTANCE.date(ramadanDate) +
                " and ends on " +
                IsoChronology.INSTANCE.date(ramadanDate.with(TemporalAdjusters.lastDayOfMonth())));
    }

}
