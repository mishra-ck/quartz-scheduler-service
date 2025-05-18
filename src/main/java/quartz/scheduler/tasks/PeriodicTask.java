package quartz.scheduler.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
@Component
public class PeriodicTask {
    @Scheduled(cron = "${cron-string}")
    public void everyFiveSeconds(){
        System.out.println("Periodic Task : " + LocalTime.now());
    }


}



