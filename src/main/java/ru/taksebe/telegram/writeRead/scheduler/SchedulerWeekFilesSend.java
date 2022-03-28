package ru.taksebe.telegram.writeRead.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchedulerWeekFilesSend {

    //fixedRate in milliseconds
    @Scheduled(fixedRate = 3000)

    //cron in milliseconds
    @Scheduled(cron = "*/3 * * * * *") // Формат:  секунда, минута, час, день, месяц, день недели

    public void reportCurrentData() {
        System.out.println("My Scheduler working: " + new Date());

    }

}
