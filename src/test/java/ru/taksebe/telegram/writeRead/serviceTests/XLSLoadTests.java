package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.repository.SamplesFileNameCrudRepository;
import ru.taksebe.telegram.writeRead.service.XLSLoadSettingFilesService;
import ru.taksebe.telegram.writeRead.service.XLSNewFormatLoadSettingFilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadSamplesFilesService;

import java.io.IOException;

@SpringBootTest
public class XLSLoadTests {

    @Autowired
    XLSLoadSettingFilesService xlsLoadSettingFilesService;
    @Autowired
    XLSNewFormatLoadSettingFilesService xlsNewFormatLoadSettingFilesService;
    @Autowired
    XLSLoadSamplesFilesService xlsLoadSamplesFilesService;

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

//    public LocalDate receivedateItemNameFromXLS(String dateItemNameFromXLS){
//        // Пример "20.12.2021"
//        dateItemNameFromXLS = "20.12.2021";
//        StringBuilder sbDate = new StringBuilder(dateItemNameFromXLS.substring(1,2));
//        StringBuilder sbMonth = new StringBuilder(dateItemNameFromXLS.substring(3,4));
//        StringBuilder sbYear = new StringBuilder(dateItemNameFromXLS.substring(6,9));
//        StringBuilder sbDateForConvert = new StringBuilder(sbYear + "-" + sbMonth + sbDate);
//
//        String sbDateStringForConvert = sbDateForConvert.toString();
//
//        LocalDate ld = LocalDate.parse(sbDateStringForConvert);
//        System.out.println("dateItemNameFromXLS - " + dateItemNameFromXLS);
//        System.out.println("ld - " + ld);
//        return ld;
//    };

}
