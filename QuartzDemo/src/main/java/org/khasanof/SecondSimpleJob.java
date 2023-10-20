package org.khasanof;

import org.quartz.*;

import java.util.Date;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/26/2023 2:06 PM
 */
public class SecondSimpleJob implements Job {

    public static final String EXECUTION_COUNT = "count";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.out.println("jobDataMap = " + jobDataMap.getWrappedMap());

        Repository repository = (Repository) jobDataMap.get(EXECUTION_COUNT);
        System.out.println("count : " + repository.getKey(EXECUTION_COUNT) + ". Current Thread Name : " + Thread.currentThread().getName());
        Integer count = repository.getKey(EXECUTION_COUNT);
        count++;
        repository.add(EXECUTION_COUNT, count);
        System.out.println("SecondSimpleJob says: " + key + " executing at " + new Date());
    }

}
