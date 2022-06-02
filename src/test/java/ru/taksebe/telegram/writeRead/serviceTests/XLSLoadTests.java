package ru.taksebe.telegram.writeRead.serviceTests;

import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.XLSLoadSettingFilesService;
import ru.taksebe.telegram.writeRead.service.XLSNewFormatLoadSettingFilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadSamplesFilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadUsersProfilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadAddressService;
import ru.taksebe.telegram.writeRead.entity.SamplesFileNameEntity;
import ru.taksebe.telegram.writeRead.repository.SamplesFileNameCrudRepository;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    SamplesFileNameCrudRepository samplesFileNameCrudRepository;
    @Autowired
    XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;

    @Test
    void xlsLoadFileOldFormat() throws IOException {
        xlsLoadSettingFilesService.xlsLoadFileOldFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "xlsLoadFileNewFormat", description = "Прототип для реальной загрузки")
    void xlsLoadFileNewFormat() throws IOException {
        xlsNewFormatLoadSettingFilesService.xlsLoadFileNewFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void receiveDateItemNameFromXLS() throws IOException {
        xlsLoadSettingFilesService.receiveDateItemNameFromXLS("20.12.2021");
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "xlsLoadSamplesFileNewFormat", description = "Для реальной загрузки Образцов")
    void xlsLoadSamplesFileNewFormat() throws IOException {
        xlsLoadSamplesFilesService.xlsLoadSamplesFileNewFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "CopyByIdSamples", description = "Для копирования строки в БД по id в таблице Samples")
    void CopyByIdSamples() throws IOException {
        xlsLoadSamplesFilesService.CopyById(6, "2_3_Таблица_риски_финансирование_на_01_12_2021.pdf");
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "CopyByIdXLSLoadSettings", description = "Для копирования строки в БД по id в таблице XLSLoadSettings")
    void CopyByIdXLSLoadSettings() throws IOException {
        LocalDate newDateItem = LocalDate.of(2022, 6, 1);
        xlsNewFormatLoadSettingFilesService.CopyById(
                91,
                "2_3_Таблица_риски_финансирование_на_01_12_2021",
                "2_3_Таблица_риски_финансирование_на_01_12_2021",
                newDateItem);
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "CopyByIdWithDataOneRow", description = "Для копирования строки в БД по id c изменением даты Samples")
    void CopyByIdAndChangedData() throws IOException {

        String fileName = "";
        String fullFileName = "";
        String newFileName = "";
        String extentionFile = ".pdf";
        String fileNameWithoutData = "";
        String last10Symbols = "";
        String newData = "15_12_2021";
        Boolean thisIsData = true;
        int numberRowForCopy = 235;

        List<SamplesFileNameEntity> result14 = samplesFileNameCrudRepository.findAllFromSamplesFileNameById(numberRowForCopy);
        result14.forEach(it14-> System.out.println(it14));
        System.out.println("result11.size = " + result14.size());
        int resultExists14 = result14.size();
        if(resultExists14 != 0){
            fileName = result14.get(0).getFull_file_name();
            fullFileName = result14.get(0).getFull_file_name();
        }

        if (fileName.endsWith(".pdf")) {
            fileName = fileName.substring(0, fileName.length() - 4);
        }

        fileNameWithoutData = fileName.substring(0, fileName.length() - 10);
        newFileName = fileNameWithoutData.concat(newData + extentionFile);

        last10Symbols = fileName.substring(fileName.length() - 10);
        for (int i = 0, n = last10Symbols.length(); i < n; i++) {

            char symb = last10Symbols.charAt(i);
            if(!(symb == '0'
                    || symb == '1'
                    || symb == '2'
                    || symb == '3'
                    || symb == '4'
                    || symb == '5'
                    || symb == '6'
                    || symb == '7'
                    || symb == '8'
                    || symb == '9'
                    || symb == '_')
            ){
                thisIsData = false;
            }
        }

        if (thisIsData){
            xlsLoadSamplesFilesService.CopyById(numberRowForCopy, newFileName);
        } else {
            xlsLoadSamplesFilesService.CopyById(numberRowForCopy, fullFileName);
        }

        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "CopyByIdWithManyDataManyRowsOneRubric", description = "Для копирования строки в БД по id c изменением даты Samples")
    void CopyByIdWithManyDataManyRowsOneRubric() throws IOException {

        String fileName = "";
        String fullFileName = "";
        String newFileName = "";
        String extentionFile = ".pdf";
        String fileNameWithoutData = "";
        String last10Symbols = "";

        ArrayList<String> namesOfDate = new ArrayList();
        namesOfDate.add("01_06_2022");

        ArrayList<Integer> numbersRowsForCopy = new ArrayList();
        numbersRowsForCopy.add(232);
        numbersRowsForCopy.add(233);
        numbersRowsForCopy.add(234);
        numbersRowsForCopy.add(235);

        for(String newData : namesOfDate)
            for(Integer ele : numbersRowsForCopy){
                Boolean thisIsData = true;

                List<SamplesFileNameEntity> result14 = samplesFileNameCrudRepository.findAllFromSamplesFileNameById(ele);
                result14.forEach(it14-> System.out.println(it14));
                System.out.println("result11.size = " + result14.size());
                int resultExists14 = result14.size();
                if(resultExists14 != 0){
                    fileName = result14.get(0).getFull_file_name();
                    fullFileName = result14.get(0).getFull_file_name();
                }

                if (fileName.endsWith(".pdf")) {
                    fileName = fileName.substring(0, fileName.length() - 4);
                }

                fileNameWithoutData = fileName.substring(0, fileName.length() - 10);
                newFileName = fileNameWithoutData.concat(newData + extentionFile);

                last10Symbols = fileName.substring(fileName.length() - 10);
                for (int i = 0, n = last10Symbols.length(); i < n; i++) {

                    char symb = last10Symbols.charAt(i);
                    if(!(symb == '0'
                            || symb == '1'
                            || symb == '2'
                            || symb == '3'
                            || symb == '4'
                            || symb == '5'
                            || symb == '6'
                            || symb == '7'
                            || symb == '8'
                            || symb == '9'
                            || symb == '_')
                    ){
                        thisIsData = false;
                    }
                }

                if (thisIsData){
                    xlsLoadSamplesFilesService.CopyById(ele, newFileName);
                } else {
                    xlsLoadSamplesFilesService.CopyById(ele, fullFileName);
                }

            }

        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "CopyByIdWithOneDataManyRowsOneRubric", description = "Для копирования строки в БД по id c изменением даты Samples")
    void CopyByIdWithOneDataManyRowsOneRubric() throws IOException {

        String fileName = "";
        String fullFileName = "";
        String newFileName = "";
        String extentionFile = ".pdf";
        String fileNameWithoutData = "";
        String last10Symbols = "";
        String newData = "15_12_2021";


        ArrayList<Integer> numbersRowsForCopy = new ArrayList();
        numbersRowsForCopy.add(232);
        numbersRowsForCopy.add(233);
        numbersRowsForCopy.add(234);
        numbersRowsForCopy.add(235);

        for(Integer ele : numbersRowsForCopy){
            Boolean thisIsData = true;

            List<SamplesFileNameEntity> result14 = samplesFileNameCrudRepository.findAllFromSamplesFileNameById(ele);
            result14.forEach(it14-> System.out.println(it14));
            System.out.println("result11.size = " + result14.size());
            int resultExists14 = result14.size();
            if(resultExists14 != 0){
                fileName = result14.get(0).getFull_file_name();
                fullFileName = result14.get(0).getFull_file_name();
            }

            if (fileName.endsWith(".pdf")) {
                fileName = fileName.substring(0, fileName.length() - 4);
            }

            fileNameWithoutData = fileName.substring(0, fileName.length() - 10);
            newFileName = fileNameWithoutData.concat(newData + extentionFile);

            last10Symbols = fileName.substring(fileName.length() - 10);
            for (int i = 0, n = last10Symbols.length(); i < n; i++) {

                char symb = last10Symbols.charAt(i);
                if(!(symb == '0'
                        || symb == '1'
                        || symb == '2'
                        || symb == '3'
                        || symb == '4'
                        || symb == '5'
                        || symb == '6'
                        || symb == '7'
                        || symb == '8'
                        || symb == '9'
                        || symb == '_')
                ){
                    thisIsData = false;
                }
            }

            if (thisIsData){
                xlsLoadSamplesFilesService.CopyById(ele, newFileName);
            } else {
                xlsLoadSamplesFilesService.CopyById(ele, fullFileName);
            }

        }

        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "CopyByIdWithManyDataManyRowsOneRubricXLSLoadSetting", description = "Для копирования строки в БД по id c изменением даты XLSLoadSettings")
    void CopyByIdWithManyDataManyRowsOneRubricXLSLoadSetting() throws IOException {

        String fileName = "";
        String fullFileName = "";
        StringBuilder fileNameWithoutExtension = new StringBuilder("");
        String newFileName = "";
        String extentionFile = ".pdf";
        String fileNameWithoutData = "";
        String last10Symbols = "";

        ArrayList<LocalDate> dateItemName = new ArrayList();
        dateItemName.add(LocalDate.of(2022, 6, 1));

        ArrayList<String> namesOfDate = new ArrayList();
        namesOfDate.add("01.06.2022");

        ArrayList<Integer> numbersRowsForCopy = new ArrayList();
        numbersRowsForCopy.add(91);
        numbersRowsForCopy.add(92);
        numbersRowsForCopy.add(93);
        numbersRowsForCopy.add(94);

        for(String newData : namesOfDate)
            for(Integer ele : numbersRowsForCopy){
                Boolean thisIsData = true;

                List<XlsLoadSettingsFilesEntity> result15 = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesById(ele);
                result15.forEach(it15-> System.out.println(it15));
                System.out.println("result15.size = " + result15.size());
                int resultExists15 = result15.size();
                if(resultExists15 != 0){
                    fileName = result15.get(0).getBook_name();
                    fullFileName = result15.get(0).getBook_name();
                }

                if (fileName.endsWith(".pdf")) {
                    fileName = fileName.substring(0, fileName.length() - 4);
                }

                fileNameWithoutData = fileName.substring(0, fileName.length() - 10);
                newFileName = fileNameWithoutData.concat(newData);

                last10Symbols = fileName.substring(fileName.length() - 10);
                for (int i = 0, n = last10Symbols.length(); i < n; i++) {

                    char symb = last10Symbols.charAt(i);
                    if(!(symb == '0'
                            || symb == '1'
                            || symb == '2'
                            || symb == '3'
                            || symb == '4'
                            || symb == '5'
                            || symb == '6'
                            || symb == '7'
                            || symb == '8'
                            || symb == '9'
                            || symb == '_'
                            || symb == '.')
                    ){
                        thisIsData = false;
                    }
                }

                if (thisIsData){
                    xlsNewFormatLoadSettingFilesService.CopyById(
                            ele,
                            newFileName,
                            newFileName,
                            dateItemName.get(0));
                } else {
                    xlsNewFormatLoadSettingFilesService.CopyById(
                            ele,
                            fullFileName,
                            fullFileName,
                            dateItemName.get(0)
                    );
                }

            }

        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "xlsLoadUsersProfilesNewFormat", description = "Для реальной загрузки профилей пользователей")
    void xlsLoadUsersProfilesNewFormat() throws IOException {
        xlsLoadUsersProfilesService.xlsLoadUsersProfilesNewFormat();
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "xlsLoadAddressNewFormat", description = "Для реальной загрузки списка адресов для рассылки")
    void xlsLoadAddressNewFormat() throws IOException {
        xlsLoadAddressService.xlsLoadAddressNewFormat();
        Assertions.assertEquals(1, 1);
    }

}
