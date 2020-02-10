package org.pp.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJobs {

    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void initialDelay() {
        System.out.println("initialDelay");
    }

    @Scheduled(fixedDelay = 1000)
    public void fixedDelay() {
        System.out.println("fixedDelay");
    }

    @Scheduled(fixedRate = 1000)
    public void fixedRate() {
        System.out.println("fixedRate");
    }

    @Scheduled(cron = "0 * * * * ?")
    public void cron() {
        System.out.println("cron");
    }
}
