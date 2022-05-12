package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.XLSLoadSettingFilesService;
import ru.taksebe.telegram.writeRead.service.XLSNewFormatLoadSettingFilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadSamplesFilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadUsersProfilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadAddressService;

import java.io.IOException;

@SpringBootTest
public class XLSLoadTests {

    @Autowired
    XLSLoadSettingFilesService xlsLoadSettingFilesService;
    @Autowired
    XLSNewFormatLoadSettingFilesService xlsNewFormatLoadSettingFilesService;
    @Autowired
    XLSLoadSamplesFilesService xlsLoadSamplesFilesService;
    @Autowired
    XLSLoadUsersProfilesService xlsLoadUsersProfilesService;
    @Autowired
    XLSLoadAddressService xlsLoadAddressService;

    @Test
    void xlsLoadFileOldFormat() throws IOException {
        xlsLoadSettingFilesService.xlsLoadFileOldFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test // Прототип для реальной загрузки
    void xlsLoadFileNewFormat() throws IOException {
        xlsNewFormatLoadSettingFilesService.xlsLoadFileNewFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void receiveDateItemNameFromXLS() throws IOException {
        xlsLoadSettingFilesService.receiveDateItemNameFromXLS("20.12.2021");
        Assertions.assertEquals(1, 1);
    }

    @Test // Для реальной загрузки Образцов
    void xlsLoadSamplesFileNewFormat() throws IOException {
        xlsLoadSamplesFilesService.xlsLoadSamplesFileNewFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test // Для реальной загрузки профилей пользователей
    void xlsLoadUsersProfilesNewFormat() throws IOException {
        xlsLoadUsersProfilesService.xlsLoadUsersProfilesNewFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test // Для реальной загрузки списка адресов для рассылки
    void xlsLoadAddressNewFormat() throws IOException {
        xlsLoadAddressService.xlsLoadAddressNewFormat();
        Assertions.assertEquals(1, 1);
    }

}
