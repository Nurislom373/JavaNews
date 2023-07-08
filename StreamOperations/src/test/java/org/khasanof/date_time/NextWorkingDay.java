package org.khasanof.date_time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * @author Nurislom
 * @see org.khasanof.date_time
 * @since 07.07.2023 11:06
 */
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int datToAdd = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY) datToAdd = 3;
        else if (dayOfWeek == DayOfWeek.SATURDAY) datToAdd = 2;
        return temporal.plus(datToAdd, ChronoUnit.DAYS);
    }
}
