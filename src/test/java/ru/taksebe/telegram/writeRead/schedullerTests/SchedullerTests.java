package ru.taksebe.telegram.writeRead.schedullerTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.scheduler.SchedulerWeekFilesSend;

@SpringBootTest
public class SchedullerTests {

    @Autowired
    SchedulerWeekFilesSend schedulerWeekFilesSend;

    @Test
    void schedulerWeekFilesSend_reportCurrentData() {
        schedulerWeekFilesSend.reportCurrentData();
        Assertions.assertEquals(1, 1);
    }

}
