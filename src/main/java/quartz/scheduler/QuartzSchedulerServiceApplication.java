package quartz.scheduler;

import org.quartz.Scheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import quartz.scheduler.processor.JobSchedulerTrigger;

@SpringBootApplication
@EnableScheduling
public class QuartzSchedulerServiceApplication {

	private final JobSchedulerTrigger jobSchedulerTrigger ;

	public QuartzSchedulerServiceApplication(JobSchedulerTrigger jobSchedulerTrigger) {
		this.jobSchedulerTrigger = jobSchedulerTrigger;
	}

	public static void main(String[] args) {
		SpringApplication.run(QuartzSchedulerServiceApplication.class, args);
	}

	public CommandLineRunner run(Scheduler scheduler){
		return (String[] args) ->{
			jobSchedulerTrigger.createScheduler();
		};
	}
}





