package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.XLSDownloadEmployeeService;
import ru.taksebe.telegram.writeRead.service.XLSNewDownloadEmployeeService;
import ru.taksebe.telegram.writeRead.service.XLSNewDownloadSettingFilesService;

import java.io.IOException;

@SpringBootTest
public class XLSDownloadTests {

    @Autowired
    XLSDownloadEmployeeService xlsDownloadEmployeeService;
    @Autowired
    XLSNewDownloadEmployeeService xlsNewDownloadEmployeeService;
    @Autowired
    XLSNewDownloadSettingFilesService xlsNewDownloadSettingFilesService;

    @Test
    void xlsDownLoadFileOldFormat() throws IOException {
        xlsDownloadEmployeeService.xlsDownLoadFileOldFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void xlsDownLoadFileNewFormat() throws IOException {
        xlsNewDownloadEmployeeService.xlsDownLoadFileNewFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void xlsNewDownloadSettingFilesService() throws IOException {
        xlsNewDownloadSettingFilesService.xlsDownLoadFileNewFormat();
        Assertions.assertEquals(1, 1);
    }

}
