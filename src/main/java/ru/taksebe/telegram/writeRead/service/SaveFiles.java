package ru.taksebe.telegram.writeRead.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import ru.taksebe.telegram.writeRead.entity.SamplesFileNameEntity;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.exceptions.FileNameNotFoundInSamplesException;
import ru.taksebe.telegram.writeRead.exceptions.NotRightSaveFileException;
import ru.taksebe.telegram.writeRead.repository.SamplesFileNameCrudRepository;
import ru.taksebe.telegram.writeRead.entity.UsersProfilesEntity;
import ru.taksebe.telegram.writeRead.repository.UsersProfilesCrudRepository;

@Component
public class SaveFiles {

    @Autowired
    SamplesFileNameCrudRepository samplesFileNameCrudRepository;
    @Autowired
    UsersProfilesCrudRepository usersProfilesCrudRepository;

    public void CopyFile() throws IOException {
        File source = new File("c:/Books/TemplatePdf.pdf");
        File  dest = new File("c:/Books/TemplatePdf1.pdf");
        copyFileUsingApacheCommonsIO(source, dest);
    }

    public void CopyFile(String fileSourceName, String fileDestname) throws IOException {
        File source = new File(fileSourceName);
        File  dest = new File(fileDestname);
        copyFileUsingApacheCommonsIO(source, dest);
    }

    public void CopyFile(String fileSourceName, String fileDestName, String pathFile, String pathOS) throws IOException {
        StringBuilder fullPathSource = new StringBuilder(pathOS + pathFile + fileSourceName);
        StringBuilder fullPathDest = new StringBuilder(pathOS + pathFile + fileDestName);
        File source = new File(fullPathSource.toString());
        File  dest = new File(fullPathDest.toString());
        copyFileUsingApacheCommonsIO(source, dest);
    }

    public static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }

    public String GetStringDateForNameFile() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd_HHmmss");
        Date date = new Date();
        String myDate = dateFormat.format(date);
        System.out.println(myDate);
        return myDate;
    }

    //метод определения расширения файла
    public String getFileExtension(String fileName) {
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

    public String GetPathFile(String fileName){
        String realPath = "";
        String rubricSystemName = "нацпроекты";
        String fileSystemName = "СпрОпрВциом";

        String first1 = "1.1_УД_НП_проектный_офис";
        String first2 = "1.2_НП_касса";
        String first3 = "1.3_Справка - опросы ВЦИОМ";

        if(fileName.indexOf(first1) == 0
                | fileName.indexOf(first2) == 0
                | fileName.indexOf(first3) == 0
        ){
            rubricSystemName = "нацпроекты";
        }

        if(fileName.indexOf(first1) == 0){
            fileSystemName = "ТабУровДост";
        } else if(fileName.indexOf(first2) == 0){
            fileSystemName = "ТабКасИсп";
        } else if(fileName.indexOf(first3) == 0){
            fileSystemName = "СпрОпрВциом";
        }

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

        if(fileName.indexOf(first1) == 0
            || fileName.indexOf(first2) == 0
            || fileName.indexOf(first3) == 0
        ){
            period = "months";
        } else if(fileName.indexOf(first1) == 0){ // quater

        }

        StringBuilder sb = new StringBuilder(
                        numberYear + "/" +
                        period + "/" +
                        numberMonthShort  + "/" +
                        rubricSystemName + "/" +
                        fileSystemName + "/");
        realPath = sb.toString();

        return realPath;
    }

    public String GetPathFileReal(String fileName, String userName){
        String realPath = "";

        String rubricSystemName = "Нет";
        String fileSystemName = "Нет";
        String first1 = "Нет";
        String period = "";
        String periodQuater = "";

        List<SamplesFileNameEntity> ResultByFileName =
                samplesFileNameCrudRepository.findAllFromSamplesFileNameFirstParam1(fileName);
        int resultExists = ResultByFileName.size();

        if(resultExists != 0){
            rubricSystemName = ResultByFileName.get(0).getSystem_rubric_name();
            fileSystemName = ResultByFileName.get(0).getSystem_file_name();
            first1 = ResultByFileName.get(0).getFull_file_name();
            period = ResultByFileName.get(0).getPeriod2();
            periodQuater = ResultByFileName.get(0).getPeriod3();
        }

        String numberYear = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY")); //2022
        String numberMonthShort = "";
        String numberWeekShort = "";

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

        // Здесь обработать, если загрузка квартальная
        if(periodQuater.equals("quarters")){
            period = "quarters";
            numberMonthShort = "3"; // Необходимо автоматически определить номер квартала
            numberYear = Integer.toString(Integer.parseInt(numberYear) - 1);// Определить как будет в реальном проекте;
        }

        StringBuilder sb = new StringBuilder(
                numberYear + "/" +
                        period + "/" +
                        numberMonthShort  + "/" +
                        rubricSystemName + "/" +
                        fileSystemName + "/");
        realPath = sb.toString();

        int index = realPath.indexOf("Нет");
        if (index != -1) {
            throw new FileNameNotFoundInSamplesException();
        }

        // Проверить есть ли у пользователя права на запись файла, иначе выдать исключение
        List<UsersProfilesEntity> resultUsersProfiles = usersProfilesCrudRepository.findAllFromUsersProfilesBy4Param(
                userName, rubricSystemName, fileSystemName);
        resultUsersProfiles.forEach(it3-> System.out.println(it3));
        System.out.println("result.size = " + resultUsersProfiles.size());
        int resultUsersProfilesExists = resultUsersProfiles.size();
        if(resultUsersProfilesExists == 0){
            throw new NotRightSaveFileException();
        }

        return realPath;
    }
}




























































































































































































































































































