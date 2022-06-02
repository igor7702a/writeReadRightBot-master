package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.entity.ForwardedFilesEntity;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.model.DateFile;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;
import ru.taksebe.telegram.writeRead.repository.ForwardedFilesCrudRepository;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TelegramDownloadLetterService {

    @Autowired
    private XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;
    @Autowired
    private ForwardedFilesCrudRepository forwardedFilesCrudRepository;
    @Autowired
    private CallbackQueryHandler callbackQueryHandler;
    @Autowired
    OSValidator osValidator;

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

        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/Books/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/Books/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);
        // -

        StringBuilder sbPathFile = new StringBuilder(sbPath.toString() + "files");
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

    public List<Integer> receiveQuantityRubricsOne(
            int yearNumber,
            int monthNumber,
            String timetable,
            String systemRubricName
    ){
        List<Integer> notSoCoolStringList = new ArrayList<Integer>();
        List<Integer> distinctInts = new ArrayList<Integer>();

        LocalDate YearFirst = LocalDate.ofYearDay(yearNumber,1);
        LocalDate YearEnd =LocalDate.ofYearDay(yearNumber,365);

        List<XlsLoadSettingsFilesEntity> result = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles5Param(
                YearFirst, YearEnd, monthNumber, timetable, systemRubricName);

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
        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/Books/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/Books/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);
        // -

        StringBuilder sb = new StringBuilder(sbPath.toString() + "files/");

        String initialPath = sb.toString();
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

    public String docxDownLoadRealLetterWithFiles(String chatId, String tgUser, String systemRubricName) throws IOException {

        // Data
        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/Books/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/Books/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);

        StringBuilder sb = new StringBuilder(sbPath.toString() + "files/");
        String initialPath = sb.toString();
        int numberYear = LocalDate.now().getYear(); // Для теста отнимем от номера года 2022-1=2022
        LocalDate ldFirst = LocalDate.ofYearDay(numberYear, 1);
        LocalDate ldEnd = LocalDate.ofYearDay(numberYear, 365);
        int numberMonth = LocalDate.now().getMonthValue(); // Для теста добавим к номеру месяца 6, чтобы получилось 11
        String timetable = "Ежемесячно"; // получить из запроса
        int numberRubric = 2;
        String chatIdAim = "5297506090"; // телеграм бот test_bot
        String chatIdAimFile = "-684336344"; // телеграм канал test_group, получить из запроса
        StringBuilder letterForTelegram = new StringBuilder("");

        // Получить как параметр системное название рубрики, например нацпроекты и получить запросом всю оставшуюся информацию
        // Получить запросом из таблицы XlsLoadSettingsFiles
        List<XlsLoadSettingsFilesEntity> result10 = xlsLoadSettingsFilesCrudRepository.
                find1FromXlsLoadSettingsFilesBySystemRubricNameOrder(systemRubricName); //выбираем последнюю строку
        result10.forEach(it10-> System.out.println(it10));
        System.out.println("result10.size = " + result10.size());
        int resultExists10 = result10.size();

        if(resultExists10 != 0){
            numberYear = result10.get(0).getDate_item_name().getYear(); // Для теста отнимем от номера года 2022-1=2022
            numberMonth = result10.get(0).getMonth_number(); // Для теста добавим к номеру месяца 6, чтобы получилось 11
            timetable = result10.get(0).getTimetable(); // получить из запроса
            chatIdAimFile = result10.get(0).getName_recipient(); //"-684336344"; // телеграм канал test_group, получить из запроса
            if(timetable.equals("Недельно")){
                numberMonth = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
            }
        }

        // По новой схеме выводим одну рубрику на одну кнопку
        // Создать новый запрос - получить только одну рубрику, добавить параметр systemRubricName
        List<Integer> quantityRubrics = receiveQuantityRubricsOne(numberYear, numberMonth, timetable, systemRubricName);
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
                        // Создать метод определяющий, что файл уже переслан, получить результат = 1
                        // Если файл уже переслан, строку с сообщением не выводить, а выводить строку, что этот файл уже переслали
                        String timetablePeriod = "months";
                        String nameRubric = element.getSystem_rubric_name();
                        String nameBook = element.getSystem_file_name();

                        if (timetable.equals("Квартально")){
                            timetablePeriod = "quarters";
                        }

                        if (timetable.equals("Недельно")){
                            timetablePeriod = "weeks";
                        }

                        List<ForwardedFilesEntity> resultAlreadySended9 = forwardedFilesCrudRepository.findAllFromForwardedAlreadySended(
                                nameRubric,
                                nameBook,
                                String.valueOf(numberYear),
                                timetablePeriod,
                                String.valueOf(numberMonth),
                                "Good",
                                "bot_telegram",
                                chatIdAimFile);

                        resultAlreadySended9.forEach(it9-> System.out.println(it9));
                        System.out.println("resultAlreadySended9.size = " + resultAlreadySended9.size());
                        int resultAlreadySendedExists9 = resultAlreadySended9.size();

                        if(resultAlreadySendedExists9 != 0){
                            // Пересылаем сообщение, что этот файл уже переслали
                            letterForTelegram.append(
                                    "\u25FD" +  // маленький белый квадрат
                                            "<u>" + element.getBook_name() + " - файл уже переслали " +
                                            resultAlreadySended9.get(0).getDelivery_date().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</u>" +
                                            "\n"
                            );
                        }else {
                            // Пересылаем требуемое сообщение
                            //  3.1 Справка достижение НЦР
                            letterForTelegram.append(
                                    "\u25FD" +  // маленький белый квадрат
                                            "<u>" + element.getBook_name() + "</u>" +
                                            "\n"
                            );

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

                    //  Отдельно по файлам ВНИМАНИЕ!!!
                    for (XlsLoadSettingsFilesEntity element : result) {

                        String nameRubric = element.getSystem_rubric_name();
                        String nameBook = element.getSystem_file_name();
                        String timetablePeriod = "months";
                        if(timetable.equals("Квартально")){
                            timetablePeriod = "quarters";
                        }
                        if(timetable.equals("Недельно")){
                            timetablePeriod = "weeks";
                        }
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
                        //String chatId = "5297506090"; - // Определено в параметрах метода
                        String token = "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4";

                        String upPath = sbPath.toString();
                        String fullPath = myFile; // "c:/books/TemplatePdf.pdf";
                        String file_name = element.getBook_name();// "TemplatePdf.pdf";
                        String file_suffix = "pdf";
                        String file_id = "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA";
                        String comment = "";

                        // Проверить - может файл уже пересылали раньше
                        // Если не пересылали переслать, если пересылали - не пересылать,
                        // но записать в лог файл, что переслка не удалась и указать причину.
                        List<ForwardedFilesEntity> resultAlreadySended = forwardedFilesCrudRepository.findAllFromForwardedAlreadySended(
                                nameRubric,
                                nameBook,
                                String.valueOf(numberYear),
                                timetablePeriod,
                                String.valueOf(numberMonth),
                                "Good",
                                "bot_telegram",
                                chatIdAimFile);

                        resultAlreadySended.forEach(it8-> System.out.println(it8));
                        System.out.println("resultAlreadySended.size = " + resultAlreadySended.size());
                        int resultAlreadySendedExists = resultAlreadySended.size();

                        if(resultAlreadySendedExists != 0){
                            // Записать информацию об ошибке отправления - файл уже был отправлен в лог - таблица forwarded_files
                            comment = "This file is already sended " + resultAlreadySended.get(0).getDelivery_date();
                            forwardedFilesCrudRepository.create_ForwardedFiles_All16(
                                    "None",
                                    nameRubric,
                                    nameBook,
                                    myFile,
                                    String.valueOf(numberYear),
                                    timetablePeriod,
                                    String.valueOf(numberMonth),
                                    resultAlreadySended.get(0).getDelivery_date(),
                                    "Bad", // Attention!
                                    "bot_telegram",
                                    "None", // требуется переопределение как параметра, по chatId получить из таблицы address
                                    chatIdAimFile,
                                    LocalDateTime.now(),
                                    tgUser,
                                    comment);
                        } else {
                            callbackQueryHandler.getTemplateOnlyPDF(
                                    chatIdAimFile,
                                    token,
                                    upPath,
                                    fullPath,
                                    file_name,
                                    file_suffix,
                                    file_id
                            );
                            // Записать информацию об отправленном файле в лог - таблица forwarded_files
                            forwardedFilesCrudRepository.create_ForwardedFiles_All16(
                                    "None",
                                    nameRubric,
                                    nameBook,
                                    myFile,
                                    String.valueOf(numberYear),
                                    timetablePeriod,
                                    String.valueOf(numberMonth),
                                    LocalDateTime.now(),
                                    "Good",
                                    "bot_telegram",
                                    "None", // требуется переопределение как параметра, по chatId получить из таблицы address
                                    chatIdAimFile,
                                    LocalDateTime.now(),
                                    tgUser,
                                    ""
                            );

                        }

                    }

                }

            }

        }

        String letterForTelegramString = letterForTelegram.toString();
        System.out.println("letterForTelegramString - " + letterForTelegramString);

        return letterForTelegramString;
    }

    public String docxDownLoadRealLetterWithFiles(String chatId, String tgUser) throws IOException {

        // Data
        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/Books/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/Books/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);

        StringBuilder sb = new StringBuilder(sbPath.toString() + "files/");
        String initialPath = sb.toString();
        int numberYear = 2021; // Для теста отнимем от номера года 2022-1=2022
        LocalDate ldFirst = LocalDate.ofYearDay(numberYear, 1);
        LocalDate ldEnd = LocalDate.ofYearDay(numberYear, 365);
        int numberMonth = 11; // Для теста добавим к номеру месяца 6, чтобы получилось 11
        String timetable = "Ежемесячно"; // получить из запроса
        int numberRubric = 2;
        String chatIdAim = "5297506090"; // телеграм бот test_bot
        String chatIdAimFile = "-684336344"; // телеграм канал test_group, получить из запроса
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
                        // Создать метод определяющий, что файл уже переслан, получить результат = 1
                        // Если файл уже переслан, строку с сообщением не выводить, а выводить строку, что этот файл уже переслали
                        String timetablePeriod = "months";
                        String nameRubric = element.getSystem_rubric_name();
                        String nameBook = element.getSystem_file_name();

                        List<ForwardedFilesEntity> resultAlreadySended9 = forwardedFilesCrudRepository.findAllFromForwardedAlreadySended(
                                nameRubric,
                                nameBook,
                                String.valueOf(numberYear),
                                timetablePeriod,
                                String.valueOf(numberMonth),
                                "Good",
                                "bot_telegram",
                                chatIdAim);

                        resultAlreadySended9.forEach(it9-> System.out.println(it9));
                        System.out.println("resultAlreadySended9.size = " + resultAlreadySended9.size());
                        int resultAlreadySendedExists9 = resultAlreadySended9.size();

                        if(resultAlreadySendedExists9 != 0){
                            // Пересылаем сообщение, что этот файл уже переслали
                            letterForTelegram.append(
                                    "\u25FD" +  // маленький белый квадрат
                                            "<u>" + element.getBook_name() + " - файл уже переслали " +
                                            resultAlreadySended9.get(0).getDelivery_date().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "</u>" +
                                            "\n"
                            );
                        }else {
                            // Пересылаем требуемое сообщение
                            //  3.1 Справка достижение НЦР
                            letterForTelegram.append(
                                    "\u25FD" +  // маленький белый квадрат
                                            "<u>" + element.getBook_name() + "</u>" +
                                            "\n"
                            );

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
                        //String chatId = "5297506090"; - // Определено в параметрах метода
                        String token = "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4";

                        String upPath = sbPath.toString();
                        String fullPath = myFile; // "c:/books/TemplatePdf.pdf";
                        String file_name = element.getBook_name();// "TemplatePdf.pdf";
                        String file_suffix = "pdf";
                        String file_id = "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA";
                        String comment = "";

                        // Проверить - может файл уже пересылали раньше
                        // Если не пересылали переслать, если пересылали - не пересылать,
                        // но записать в лог файл, что переслка не удалась и указать причину.
                        List<ForwardedFilesEntity> resultAlreadySended = forwardedFilesCrudRepository.findAllFromForwardedAlreadySended(
                                nameRubric,
                                nameBook,
                                String.valueOf(numberYear),
                                timetablePeriod,
                                String.valueOf(numberMonth),
                                "Good",
                                "bot_telegram",
                                chatIdAim);

                        resultAlreadySended.forEach(it8-> System.out.println(it8));
                        System.out.println("resultAlreadySended.size = " + resultAlreadySended.size());
                        int resultAlreadySendedExists = resultAlreadySended.size();

                        if(resultAlreadySendedExists != 0){
                            // Записать информацию об ошибке отправления - файл уже был отправлен в лог - таблица forwarded_files
                            comment = "This file is already sended " + resultAlreadySended.get(0).getDelivery_date();
                            forwardedFilesCrudRepository.create_ForwardedFiles_All16(
                                    "None",
                                    nameRubric,
                                    nameBook,
                                    myFile,
                                    String.valueOf(numberYear),
                                    timetablePeriod,
                                    String.valueOf(numberMonth),
                                    resultAlreadySended.get(0).getDelivery_date(),
                                    "Bad", // Attention!
                                    "bot_telegram",
                                    "None", // требуется переопределение как параметра, по chatId получить из таблицы address
                                    chatIdAim,
                                    LocalDateTime.now(),
                                    tgUser,
                                    comment);
                        } else {
                            callbackQueryHandler.getTemplateOnlyPDF(
                                    chatIdAimFile,
                                    token,
                                    upPath,
                                    fullPath,
                                    file_name,
                                    file_suffix,
                                    file_id
                            );
                            // Записать информацию об отправленном файле в лог - таблица forwarded_files
                            forwardedFilesCrudRepository.create_ForwardedFiles_All16(
                                    "None",
                                    nameRubric,
                                    nameBook,
                                    myFile,
                                    String.valueOf(numberYear),
                                    timetablePeriod,
                                    String.valueOf(numberMonth),
                                    LocalDateTime.now(),
                                    "Good",
                                    "bot_telegram",
                                    "None", // требуется переопределение как параметра, по chatId получить из таблицы address
                                    chatIdAim,
                                    LocalDateTime.now(),
                                    tgUser,
                                    ""
                            );

                        }

                    }

                }

            }

        }

        String letterForTelegramString = letterForTelegram.toString();
        System.out.println("letterForTelegramString - " + letterForTelegramString);

        return letterForTelegramString;
    }

}


