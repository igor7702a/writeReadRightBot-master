package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.model.DateFile;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;
import ru.taksebe.telegram.writeRead.telegram.handlers.CallbackQueryHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TelegramDownloadLetterService {

    @Autowired
    private XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;
    @Autowired
    private CallbackQueryHandler callbackQueryHandler;

    public String receiveEndFileParam(
            String initialPath, //c:/Books/files/
            int numberYear,
            String timetable,
            int numberMonth,
            String nameRubric,
            String nameBook

    ) throws IOException {
        //Получаем: //c:/Books/files/2021/months/11/нацпроекты/СправкаОпросыВЦИОМ/1.3_Справка - опросы ВЦИОМ_220401_095822.pdf
        StringBuilder sb = new StringBuilder(initialPath);
        sb.append(numberYear + "/");
        sb.append(timetable + "/");
        sb.append(numberMonth + "/");
        sb.append(nameRubric + "/");
        sb.append(nameBook + "/");

        String path = sb.toString();

        File dir = new File(path); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);

        List<DateFile> myFiles = new ArrayList<>();

        for (File element : lst) {
            Path file = Paths.get(String.valueOf(element));
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            FileTime creationTime = attr.creationTime();
            System.out.println(
                    " file path: " + file +
                    " file bytes: " + element +
                    " creationTime: " + creationTime
            );
            myFiles.add(new DateFile(file, element, creationTime));
        }

        lst.forEach(it3-> System.out.println(it3));

        List listMyFile = myFiles.stream()
                .sorted(Comparator.comparing(DateFile::getDataCreated)
                        .reversed())
                .limit(1)
                .collect(Collectors.toList());

        listMyFile.forEach(it5-> System.out.println(it5));

        String myFile = ((DateFile) listMyFile.get(0)).getpathFile().toString();

        return myFile;

    };

    public String receiveEndFile() throws IOException {
        //c:/Books/files/2021/months/11/нацпроекты/СправкаОпросыВЦИОМ/1.3_Справка - опросы ВЦИОМ_220401_095822.pdf
        String path = "c:/Books/files/2021/months/11/нацпроекты/СправкаОпросыВЦИОМ/";

        File dir = new File(path); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);

        List<DateFile> myFiles = new ArrayList<>();

        for (File element : lst) {
            Path file = Paths.get(String.valueOf(element));
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            FileTime creationTime = attr.creationTime();
            System.out.println(
                    " file path: " + file +
                    " file bytes: " + element +
                    " creationTime: " + creationTime
            );
            myFiles.add(new DateFile(file, element, creationTime));
        }

        lst.forEach(it3-> System.out.println(it3));

        List listMyFile = myFiles.stream()
                .sorted(Comparator.comparing(DateFile::getDataCreated)
                        .reversed())
                .limit(1)
                .collect(Collectors.toList());

        listMyFile.forEach(it5-> System.out.println(it5));

        String myFile = ((DateFile) listMyFile.get(0)).getpathFile().toString();

        return myFile;

    };

    public String receivePathFile(
            int yearNumber,
            String timetable,
            int monthNumber,
            String systemNameRubric,
            String systemNameFile
    ){
        String stPathFile = "";
        StringBuilder sbPathFile = new StringBuilder("c:/Books/files");
        sbPathFile.append("/" + yearNumber);
        sbPathFile.append("/" + timetable);
        sbPathFile.append("/" + monthNumber);
        sbPathFile.append("/" + systemNameRubric);
        sbPathFile.append("/" + systemNameFile);
        stPathFile = sbPathFile.toString();
        System.out.println("stPathFile - " + stPathFile);

     return stPathFile;
    };

    public List<Integer> receiveQuantityRubrics(
            int yearNumber,
            int monthNumber,
            String timetable
    ){
        List<Integer> notSoCoolStringList = new ArrayList<Integer>();
        List<Integer> distinctInts = new ArrayList<Integer>();

        LocalDate YearFirst = LocalDate.ofYearDay(yearNumber,1);
        LocalDate YearEnd =LocalDate.ofYearDay(yearNumber,365);

        List<XlsLoadSettingsFilesEntity> result = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles4Param(
                YearFirst, YearEnd, monthNumber, timetable);

        result.forEach(it3-> System.out.println(it3));
        System.out.println("result.size = " + result.size());
        int resultExists = result.size();

        if(resultExists != 0){
            // Добавить в ArrayList только номера рубрик
            for (XlsLoadSettingsFilesEntity element : result) {
                notSoCoolStringList.add(element.getRubric_number());
            }

            // Оставить уникальные
            distinctInts = notSoCoolStringList.stream()
                    .sorted()
                    .distinct().collect(Collectors.toList());
        }
        distinctInts.forEach(it4-> System.out.println(it4));

        return distinctInts;
    };

    public void docxDownLoadEmptyLetter() throws IOException {
        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream( new File("c:/books/letters/createdocument.docx"));
        document.write(out);
        out.close();
        System.out.println("createdocument.docx written successully");
    }

    public void docxDownLoadParagrafLetter() throws IOException {
        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("c:/books/letters/createparagraph.docx"));

        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("At tutorialspoint.com, we strive hard to " +
                "provide quality tutorials for self-learning " +
                "purpose in the domains of Academics, Information " +
                "Technology, Management and Computer Programming Languages.");

        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph.createRun();
        run2.addCarriageReturn();
        run2.setText("new");

        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
    }

    public String docxDownLoadRealLetter() throws IOException {

        // Data
        String initialPath = "c:/Books/files/";
        int numberYear = 2021;
        LocalDate ldFirst = LocalDate.ofYearDay(numberYear, 1);
        LocalDate ldEnd = LocalDate.ofYearDay(numberYear, 365);
        int numberMonth = 11;
        String timetable = "Ежемесячно";
        int numberRubric = 2;
        StringBuilder letterForTelegram = new StringBuilder("");

        List<Integer> quantityRubrics = receiveQuantityRubrics(numberYear, numberMonth, timetable);
        quantityRubrics.forEach(it3-> System.out.println(it3));
        System.out.println("result.size = " + quantityRubrics.size());
        int quantityRubricsExists = quantityRubrics.size();

        if(quantityRubricsExists != 0) {

            int itemNameIndex = 0;
            for (Integer elementRubrics : quantityRubrics) {

                ++itemNameIndex;

                numberRubric = elementRubrics.intValue();

                List<XlsLoadSettingsFilesEntity> result = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles5Param1Order(
                        ldFirst, ldEnd, numberMonth, timetable, numberRubric);
                result.forEach(it3-> System.out.println(it3));
                System.out.println("result.size = " + result.size());
                int resultExists = result.size();

                if(resultExists != 0){

                    if(itemNameIndex == 1){
                        // Материалы к оперативному совещанию 20.12.2021

                        letterForTelegram.append(
                                result.get(0).getItem_name() + " " +
                                        result.get(0).getDate_item_name() +
                                "\n"
                        );
                    }

                    // 3. НАЦИОНАЛЬНЫЕ ЦЕЛИ (войти в АРМ Наццели) + войти... - это ссылка
                    StringBuilder sbArm = new StringBuilder(" (войти в АРМ ");
                    sbArm.append(result.get(0).getArm_name());
                    sbArm.append(")");
                    String stArm = sbArm.toString();
                    String urlArm = result.get(0).getArm_link();

                    char charQuotes = '"';
                    StringBuilder sbUrl = new StringBuilder("<a href=" + charQuotes);
                    sbUrl.append(urlArm);
                    sbUrl.append(charQuotes + ">" + stArm + "</a>");
                    String stUrlArm = sbUrl.toString();

                    letterForTelegram.append(
                            "\n" +
                            "<b>" + result.get(0).getRubric_number() + ". " +
                                    result.get(0).getRubric_name() +"</b>" +
                                    stUrlArm +
                            "\n"
                    );

                    // Ответственные: справка- А.С. Мальков, таблицы - Н.Н. Баценков
                    letterForTelegram.append(
                                    // Курсив начало markdown
                            "<i>"  +
                            "Ответственные: " +
                                    result.get(0).getOfficer_for() +
                                    // Курсив окончание markdown
                            "</i>" +
                                    "\n"
                    );

                    // Перечень подпунктов в Цели:
                    for (XlsLoadSettingsFilesEntity element : result) {
                        //  3.1 Справка достижение НЦР
                        letterForTelegram.append(
                                "\u25FD" +  // маленький белый квадрат
                                "<u>" + element.getBook_name() + "</u>" +
                                "\n"
                        );

                        String nameRubric = element.getSystem_rubric_name();
                        String nameBook = element.getSystem_file_name();
                        String timetablePeriod = "months";
                        String myFile = receiveEndFileParam(
                                initialPath, //c:/Books/files/
                                numberYear,
                                timetablePeriod,
                                numberMonth,
                                nameRubric,
                                nameBook
                                    );

                    }

                    //  Отдельно по файлам
                    for (XlsLoadSettingsFilesEntity element : result) {

                        String nameRubric = element.getSystem_rubric_name();
                        String nameBook = element.getSystem_file_name();
                        String timetablePeriod = "months";
                        String myFile = receiveEndFileParam(
                                initialPath, //c:/Books/files/
                                numberYear,
                                timetablePeriod,
                                numberMonth,
                                nameRubric,
                                nameBook
                        );

                    }

                }

            }

        }

        String letterForTelegramString = letterForTelegram.toString();
        System.out.println("letterForTelegramString - " + letterForTelegramString);

        return letterForTelegramString;
    }

    public String docxDownLoadRealLetterWithFiles() throws IOException {

        // Data
        String initialPath = "c:/Books/files/";
        int numberYear = 2021;
        LocalDate ldFirst = LocalDate.ofYearDay(numberYear, 1);
        LocalDate ldEnd = LocalDate.ofYearDay(numberYear, 365);
        int numberMonth = 11;
        String timetable = "Ежемесячно";
        int numberRubric = 2;
        StringBuilder letterForTelegram = new StringBuilder("");

        List<Integer> quantityRubrics = receiveQuantityRubrics(numberYear, numberMonth, timetable);
        quantityRubrics.forEach(it3-> System.out.println(it3));
        System.out.println("result.size = " + quantityRubrics.size());
        int quantityRubricsExists = quantityRubrics.size();

        if(quantityRubricsExists != 0) {

            int itemNameIndex = 0;
            for (Integer elementRubrics : quantityRubrics) {

                ++itemNameIndex;

                numberRubric = elementRubrics.intValue();

                List<XlsLoadSettingsFilesEntity> result = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles5Param1Order(
                        ldFirst, ldEnd, numberMonth, timetable, numberRubric);
                result.forEach(it3-> System.out.println(it3));
                System.out.println("result.size = " + result.size());
                int resultExists = result.size();

                if(resultExists != 0 && result.get(0).getRubric_number() == 1){

                    if(itemNameIndex == 1){
                        // Материалы к оперативному совещанию 20.12.2021
                        letterForTelegram.append(
                                result.get(0).getItem_name() + " " +
                                        result.get(0).getDate_item_name() +
                                        "\n"
                        );

                    }

                    // 3. НАЦИОНАЛЬНЫЕ ЦЕЛИ (войти в АРМ Наццели) + войти... - это ссылка
                    StringBuilder sbArm = new StringBuilder(" (войти в АРМ ");
                    sbArm.append(result.get(0).getArm_name());
                    sbArm.append(")");
                    String stArm = sbArm.toString();
                    String urlArm = result.get(0).getArm_link();

                    char charQuotes = '"';
                    StringBuilder sbUrl = new StringBuilder("<a href=" + charQuotes);
                    sbUrl.append(urlArm);
                    sbUrl.append(charQuotes + ">" + stArm + "</a>");
                    String stUrlArm = sbUrl.toString();

                    letterForTelegram.append(
                            "\n" +
                                    "<b>" + result.get(0).getRubric_number() + ". " +
                                    result.get(0).getRubric_name() +"</b>" +
                                    stUrlArm +
                                    "\n"
                    );

                    // Ответственные: справка- А.С. Мальков, таблицы - Н.Н. Баценков
                    letterForTelegram.append(
                            // Курсив начало markdown
                            "<i>"  +
                                    "Ответственные: " +
                                    result.get(0).getOfficer_for() +
                                    // Курсив окончание markdown
                                    "</i>" +
                                    "\n"
                    );

                    // Перечень подпунктов в Цели:
                    for (XlsLoadSettingsFilesEntity element : result) {
                        //  3.1 Справка достижение НЦР
                        letterForTelegram.append(
                                "\u25FD" +  // маленький белый квадрат
                                        "<u>" + element.getBook_name() + "</u>" +
                                        "\n"
                        );

                        String nameRubric = element.getSystem_rubric_name();
                        String nameBook = element.getSystem_file_name();
                        String timetablePeriod = "months";
                        String myFile = receiveEndFileParam(
                                initialPath, //c:/Books/files/
                                numberYear,
                                timetablePeriod,
                                numberMonth,
                                nameRubric,
                                nameBook
                        );

                    }

                    //  Отдельно по файлам
                    for (XlsLoadSettingsFilesEntity element : result) {

                        String nameRubric = element.getSystem_rubric_name();
                        String nameBook = element.getSystem_file_name();
                        String timetablePeriod = "months";
                        String myFile = receiveEndFileParam(
                                initialPath, //c:/Books/files/
                                numberYear,
                                timetablePeriod,
                                numberMonth,
                                nameRubric,
                                nameBook
                        );

                        System.out.println(myFile);

                        // Отправить файлы в этот канал
                        String chatId = "5297506090";
                        String token = "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4";
                        String upPath = "c:/books/";
                        String fullPath = myFile; // "c:/books/TemplatePdf.pdf";
                        String file_name = element.getBook_name();// "TemplatePdf.pdf";
                        String file_suffix = "pdf";
                        String file_id = "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA";

                        callbackQueryHandler.getTemplateOnlyPDF(
                                chatId,
                                token,
                                upPath,
                                fullPath,
                                file_name,
                                file_suffix,
                                file_id
                        );

                    }

                }

            }

        }

        String letterForTelegramString = letterForTelegram.toString();
        System.out.println("letterForTelegramString - " + letterForTelegramString);

        return letterForTelegramString;
    }

}


