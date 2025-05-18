package quartz.scheduler.processor;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import quartz.scheduler.jobs.SimpleJob;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JobSchedulerTrigger {

    public static Logger LOGGER = LoggerFactory.getLogger(JobSchedulerTrigger.class);

    public JobDetail createSimpleJob(){
        LOGGER.info("Inside createSimpleJob method");
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .storeDurably(true)
                .withIdentity("myJob")
                .build();
        LOGGER.info("JobDetail is : {}", jobDetail);
        return jobDetail ;
    }
    public Trigger createTriggerRunsEveryFiveSec(){
        LOGGER.info("Inside createTriggerRunsEveryFiveSec method");
        Date afterFiveSec = Date.from(LocalDateTime.now().plusSeconds(5).atZone(ZoneId.systemDefault()).toInstant()) ;
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger")
                .startAt(afterFiveSec)
                .build();
        LOGGER.info("Trigger created : {}",trigger);
        return trigger ;
    }

    @Bean
    public String createScheduler() throws SchedulerException {
        LOGGER.info("Inside createScheduler method ");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();  // at this point scheduler is at "stand-by" mode.
        // start the scheduler
        scheduler.start();
        LOGGER.info("Scheduler started {}", scheduler);
        // now we can schedule our job execution
        JobDetail jobDetail = createSimpleJob();
        Trigger trigger = createTriggerRunsEveryFiveSec() ;
        scheduler.scheduleJob(jobDetail,trigger);
        LOGGER.info("Job Scheduled by scheduler ");
        return new String("Job Triggered by Scheduler");
    }

}
