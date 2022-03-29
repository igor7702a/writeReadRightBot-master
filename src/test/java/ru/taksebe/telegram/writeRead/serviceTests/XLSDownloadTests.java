package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.XLSDownloadEmployeeService;

import java.io.IOException;

@SpringBootTest
public class XLSDownloadTests {

    @Autowired
    XLSDownloadEmployeeService xlsDownloadEmployeeService;

    @Test
    void xlsDownLoadFileOldFormat() throws IOException {
        xlsDownloadEmployeeService.xlsDownLoadFileOldFormat();
        Assertions.assertEquals(1, 1);
    }

}
