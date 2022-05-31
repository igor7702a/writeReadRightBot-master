package ru.taksebe.telegram.writeRead.serviceTests;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.SaveFiles;
import ru.taksebe.telegram.writeRead.service.OSValidator;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
@Tag(name = "SaveFilesTest", description = "Тест по сохранению файлов")
public class SaveFilesTest {

    @Autowired
    SaveFiles saveFiles;
    @Autowired
    OSValidator osValidator;

    @Test
    void receiveOS() throws IOException {
        saveFiles.CopyFile();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void receiveOS_2Param() throws IOException {
        saveFiles.CopyFile("c:/Books/TemplatePdf.pdf", "c:/Books/TemplatePdf2.pdf");
        Assertions.assertEquals(1, 1);
    }

    @Test
    void receiveOS_4Param() throws IOException {
        saveFiles.CopyFile(
                "TemplatePdf.pdf",
                "TemplatePdf3.pdf",
                "Books/",
                "c:/");
        Assertions.assertEquals(1, 1);
    }

    @Test
    void receiveOS_4ParamWinUnix() throws IOException {
        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);
        // -

        saveFiles.CopyFile(
                "TemplatePdf.pdf",
                "TemplatePdf4.pdf",
                "Books/",
                pathOS);
        Assertions.assertEquals(1, 1);
    }

    @Test
    void GetStringDateForNameFile() throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("_yyyyMMdd_HHmmss");
        Date date = new Date();
        String myDate = dateFormat.format(date);
        System.out.println(myDate);
        Assertions.assertEquals(1, 1);
    }

    @Test
    void GetPathFile() throws IOException {
        String realPath = "";
        String pathOS = "c:/Books/";
        String fileName = "1.3_Справка - опросы ВЦИОМ";
        String rubricSystemName = "нацпроекты";
        String fileSystemName = "СпрОпрВциом";
        String first4 = "1.3_";
        String numberYear = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY")); //2022
        String numberMonthShort = "";
        String numberWeekShort = "";
        String period = "";

        String numberMonthFull = LocalDate.now().format(DateTimeFormatter.ofPattern("MM")); //04
        // Если первый символ = 0, то учитываем без него
        char cMounth = numberMonthFull.charAt(0);
        if(cMounth == 48){
            numberMonthShort = numberMonthFull.substring(1);
        }else {
            numberMonthShort = new StringBuilder(numberMonthFull).toString();
        }

        String numberWeekFull = LocalDate.now().format(DateTimeFormatter.ofPattern("w")); //17
        // Если первый символ = 0, то учитываем без него
        char cWeek = numberWeekFull.charAt(0);
        if(cWeek == 48){
            numberWeekShort = numberMonthFull.substring(1);
        }else {
            numberWeekShort = new StringBuilder(numberMonthFull).toString();
        }

        if(fileName.indexOf(first4) == 0){
            period = "month";
        }

        StringBuilder sb = new StringBuilder(
                pathOS +
                numberYear + "/" +
                period + "/" +
                numberMonthShort  + "/" +
                rubricSystemName + "/" +
                fileSystemName + "/");
        realPath = sb.toString();
        Assertions.assertEquals(1, 1);
    }
}


