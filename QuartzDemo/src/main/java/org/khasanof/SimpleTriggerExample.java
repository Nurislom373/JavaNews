package org.khasanof;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.repeatSecondlyForTotalCount;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 9/26/2023 1:20 PM
 */
public class SimpleTriggerExample {

    public static void main(String[] args) throws SchedulerException {
        System.out.println("------- Initializing -------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        Repository repository = new Repository();
        repository.add("count", 1);

        JobDetail job = newJob(SecondSimpleJob.class).withIdentity("job1", "group1")
                .build();

        job.getJobDataMap().put("count", repository);

        System.out.println("------- Initialization Complete --------");

        System.out.println("------- Scheduling Jobs ----------------");

        SimpleTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount((3 - 1))
                )
                .forJob(job.getKey())
                .build();

        sched.scheduleJob(job, trigger);
        sched.start();

//        CalendarIntervalScheduleBuilder.calendarIntervalSchedule()

        JobDetail job2 = newJob(SimpleJob.class).withIdentity("job2", "group1")
                .build();

        SimpleTrigger trigger2 = newTrigger().withIdentity("trigger2", "group1")
                .withSchedule(repeatSecondlyForTotalCount(10, 1))
                .forJob(job2.getKey())
                .build();

        sched.scheduleJob(job2, trigger2);

        System.out.println("------- Waiting five minutes... ------------");
        try {
            // wait five minutes to show jobs
            Thread.sleep(6000L);
            // executing...
        } catch (Exception e) {
            //
        }

        System.out.println("------- Shutting Down ---------------------");
//        sched.shutdown(true);

        System.out.println("------- Shutdown Complete -----------------");

        // display some stats about the schedule that just ran
        SchedulerMetaData metaData = sched.getMetaData();
        System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

}
