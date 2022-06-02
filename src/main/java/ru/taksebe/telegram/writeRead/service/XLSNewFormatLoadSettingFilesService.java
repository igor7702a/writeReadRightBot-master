package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.entity.SamplesFileNameEntity;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Component
public class XLSNewFormatLoadSettingFilesService {

    @Autowired
    private XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;

    public void xlsLoadFileNewFormat() throws IOException {

        // Инициализация переменных метода
        int ourIndex = 0;
        String itemName = "";
        LocalDate dateItemName = LocalDate.now();
        String armName = "";
        String armLink = "";
        String officerFor = "";
        int rubricNumber = 0;
        String rubricName = "";
        int bookNumber = 0;
        String bookName = "";
        String fileName = "";
        int monthNumber = 0;
        String timetable = "";
        String typeRecipient = "";
        String nameRecipient = "";
        String fioUpload = "";
        LocalDateTime datetimeUpload = LocalDateTime.now();
        String systemRubricName = "";
        String systemFileName = "";

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File("C:/Books/settings/xlsloadsettingsfiles/xlsloadsettingsfiles.xlsx"));

        // Get the workbook instance for XLS file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            // Инициализация полей в начальное значение
            ourIndex = 0;
            itemName = "";
            dateItemName = LocalDate.now();
            armName = "";
            armLink = "";
            officerFor = "";
            rubricNumber = 0;
            rubricName = "";
            bookNumber = 0;
            bookName = "";
            fileName = "";
            monthNumber = 0;
            timetable = "";
            typeRecipient = "";
            nameRecipient = "";
            fioUpload = "";
            datetimeUpload = LocalDateTime.now();
            systemRubricName = "";
            systemFileName = "";

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                // Change to getCellType() if using POI 4.x
                CellType cellType = cell.getCellType();

                ourIndex = ourIndex + 1;
                switch (cellType) {
                    case _NONE:
                        System.out.print("");
                        System.out.print("NONE");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        System.out.print("BOOLEAN");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                    case BLANK:
                        System.out.print("");
                        System.out.print("BLANK");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                    case FORMULA:
                        // Formula
                        System.out.print(cell.getCellFormula());
                        System.out.print("FORMULA");
                        System.out.print(ourIndex);
                        System.out.print("\t");

                        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                        // Print out value evaluated by formula
                        System.out.print(evaluator.evaluate(cell).getNumberValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        System.out.print("NUMERIC");
                        System.out.print(ourIndex);
                        System.out.print("\t");

                        switch (ourIndex) {
                            case 6:
                                rubricNumber = (int) cell.getNumericCellValue();
                                break;
                            case 8:
                                bookNumber = (int) cell.getNumericCellValue();
                                break;
                            case 11:
                                monthNumber = (int) cell.getNumericCellValue();
                                break;
                        }
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        System.out.print("STRING");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        switch (ourIndex) {
                            case 1:
                                itemName = cell.getStringCellValue();
                                break;
                            case 2:
                                String dateItemNameFromXLS = cell.getStringCellValue();
                                System.out.println("dateItemNameFromXLS" + dateItemNameFromXLS);
                                dateItemName = receiveDateItemNameFromXLS(dateItemNameFromXLS);
                                break;
                            case 3:
                                armName = cell.getStringCellValue();
                                break;
                            case 4:
                                armLink = cell.getStringCellValue();
                                break;
                            case 5:
                                officerFor = cell.getStringCellValue();
                                break;
                            case 7:
                                rubricName = cell.getStringCellValue();
                                break;
                            case 9:
                                bookName = cell.getStringCellValue();
                                break;
                            case 10:
                                fileName = cell.getStringCellValue();
                                break;
                            case 12:
                                timetable = cell.getStringCellValue();
                                break;
                            case 13:
                                typeRecipient = cell.getStringCellValue();
                                break;
                            case 14:
                                nameRecipient = cell.getStringCellValue();
                                break;
                            case 15:
                                fioUpload = cell.getStringCellValue();
                                break;
//                           case 16: // Из Эксель не загружается
//                              break;
                            case 17:
                                systemRubricName = cell.getStringCellValue();
                                break;
                            case 18:
                                systemFileName = cell.getStringCellValue();
                                break;
                        }
                        break;
                    case ERROR:
                        System.out.print("!");
                        System.out.print("ERROR");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                }

            }
            System.out.println("");
            System.out.println("itemName = " + itemName);
            System.out.println("dateItemName = " + dateItemName);
            System.out.println("armName = " + armName);
            System.out.println("armLink = " + armLink);
            System.out.println("officerFor = " + officerFor);
            System.out.println("rubricNumber = " + rubricNumber);
            System.out.println("rubricName = " + rubricName);
            System.out.println("bookNumber = " + bookNumber);
            System.out.println("bookName = " + bookName);
            System.out.println("fileName = " + fileName);
            System.out.println("monthNumber = " + monthNumber);
            System.out.println("timetable = " + timetable);
            System.out.println("typeRecipient = " + typeRecipient);
            System.out.println("nameRecipient = " + nameRecipient);
            System.out.println("fioUpload = " + fioUpload);
            System.out.println("datetimeUpload = " + datetimeUpload);
            System.out.println("systemRubricName = " + systemRubricName);
            System.out.println("systemFileName = " + systemFileName);

            // Запись в БД
            String str1 = "Название темы";
            if(str1.equalsIgnoreCase(itemName) == false){
                xlsLoadSettingsFilesCrudRepository.create_XlsLoadSettingsFiles_All18(
                        itemName,
                        dateItemName,
                        armName,
                        armLink,
                        officerFor,
                        rubricNumber,
                        rubricName,
                        bookNumber,
                        bookName,
                        fileName,
                        monthNumber,
                        timetable,
                        typeRecipient,
                        nameRecipient,
                        fioUpload,
                        datetimeUpload,
                        systemRubricName,
                        systemFileName
                );
            }

        }

    }

    public void xlsLoadFileNewFormat(FileInputStream fisXlsloadsettingsfiles) throws IOException {

        // Инициализация переменных метода
        int ourIndex = 0;
        String itemName = "";
        LocalDate dateItemName = LocalDate.now();
        String armName = "";
        String armLink = "";
        String officerFor = "";
        int rubricNumber = 0;
        String rubricName = "";
        int bookNumber = 0;
        String bookName = "";
        String fileName = "";
        int monthNumber = 0;
        String timetable = "";
        String typeRecipient = "";
        String nameRecipient = "";
        String fioUpload = "";
        LocalDateTime datetimeUpload = LocalDateTime.now();
        String systemRubricName = "";
        String systemFileName = "";

        // Read XSL file
        FileInputStream inputStream = fisXlsloadsettingsfiles;

        // Get the workbook instance for XLS file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            // Инициализация полей в начальное значение
            ourIndex = 0;
            itemName = "";
            dateItemName = LocalDate.now();
            armName = "";
            armLink = "";
            officerFor = "";
            rubricNumber = 0;
            rubricName = "";
            bookNumber = 0;
            bookName = "";
            fileName = "";
            monthNumber = 0;
            timetable = "";
            typeRecipient = "";
            nameRecipient = "";
            fioUpload = "";
            datetimeUpload = LocalDateTime.now();
            systemRubricName = "";
            systemFileName = "";

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                // Change to getCellType() if using POI 4.x
                CellType cellType = cell.getCellType();

                ourIndex = ourIndex + 1;
                switch (cellType) {
                    case _NONE:
                        System.out.print("");
                        System.out.print("NONE");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        System.out.print("BOOLEAN");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                    case BLANK:
                        System.out.print("");
                        System.out.print("BLANK");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                    case FORMULA:
                        // Formula
                        System.out.print(cell.getCellFormula());
                        System.out.print("FORMULA");
                        System.out.print(ourIndex);
                        System.out.print("\t");

                        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                        // Print out value evaluated by formula
                        System.out.print(evaluator.evaluate(cell).getNumberValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        System.out.print("NUMERIC");
                        System.out.print(ourIndex);
                        System.out.print("\t");

                        switch (ourIndex) {
                            case 6:
                                rubricNumber = (int) cell.getNumericCellValue();
                                break;
                            case 8:
                                bookNumber = (int) cell.getNumericCellValue();
                                break;
                            case 11:
                                monthNumber = (int) cell.getNumericCellValue();
                                break;
                        }
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        System.out.print("STRING");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        switch (ourIndex) {
                            case 1:
                                itemName = cell.getStringCellValue();
                                break;
                            case 2:
                                String dateItemNameFromXLS = cell.getStringCellValue();
                                System.out.println("dateItemNameFromXLS" + dateItemNameFromXLS);
                                dateItemName = receiveDateItemNameFromXLS(dateItemNameFromXLS);
                                break;
                            case 3:
                                armName = cell.getStringCellValue();
                                break;
                            case 4:
                                armLink = cell.getStringCellValue();
                                break;
                            case 5:
                                officerFor = cell.getStringCellValue();
                                break;
                            case 7:
                                rubricName = cell.getStringCellValue();
                                break;
                            case 9:
                                bookName = cell.getStringCellValue();
                                break;
                            case 10:
                                fileName = cell.getStringCellValue();
                                break;
                            case 12:
                                timetable = cell.getStringCellValue();
                                break;
                            case 13:
                                typeRecipient = cell.getStringCellValue();
                                break;
                            case 14:
                                nameRecipient = cell.getStringCellValue();
                                break;
                            case 15:
                                fioUpload = cell.getStringCellValue();
                                break;
//                           case 16: // Из Эксель не загружается
//                              break;
                            case 17:
                                systemRubricName = cell.getStringCellValue();
                                break;
                            case 18:
                                systemFileName = cell.getStringCellValue();
                                break;
                        }
                        break;
                    case ERROR:
                        System.out.print("!");
                        System.out.print("ERROR");
                        System.out.print(ourIndex);
                        System.out.print("\t");
                        break;
                }

            }
            System.out.println("");
            System.out.println("itemName = " + itemName);
            System.out.println("dateItemName = " + dateItemName);
            System.out.println("armName = " + armName);
            System.out.println("armLink = " + armLink);
            System.out.println("officerFor = " + officerFor);
            System.out.println("rubricNumber = " + rubricNumber);
            System.out.println("rubricName = " + rubricName);
            System.out.println("bookNumber = " + bookNumber);
            System.out.println("bookName = " + bookName);
            System.out.println("fileName = " + fileName);
            System.out.println("monthNumber = " + monthNumber);
            System.out.println("timetable = " + timetable);
            System.out.println("typeRecipient = " + typeRecipient);
            System.out.println("nameRecipient = " + nameRecipient);
            System.out.println("fioUpload = " + fioUpload);
            System.out.println("datetimeUpload = " + datetimeUpload);
            System.out.println("systemRubricName = " + systemRubricName);
            System.out.println("systemFileName = " + systemFileName);

            // Запись в БД
            String str1 = "Название темы";
            if(str1.equalsIgnoreCase(itemName) == false){
                xlsLoadSettingsFilesCrudRepository.create_XlsLoadSettingsFiles_All18(
                        itemName,
                        dateItemName,
                        armName,
                        armLink,
                        officerFor,
                        rubricNumber,
                        rubricName,
                        bookNumber,
                        bookName,
                        fileName,
                        monthNumber,
                        timetable,
                        typeRecipient,
                        nameRecipient,
                        fioUpload,
                        datetimeUpload,
                        systemRubricName,
                        systemFileName
                );
            }

        }

    }

    public LocalDate receiveDateItemNameFromXLS(String dateItemNameFromXLS){

        // Проверить, чтобы количество цифр 10
        LocalDate ld = LocalDate.now();

        int sizedateItemNameFromXLS = dateItemNameFromXLS.length();

        if (sizedateItemNameFromXLS == 10){
            StringBuilder sbDate = new StringBuilder(dateItemNameFromXLS.substring(0,2));
            StringBuilder sbMonth = new StringBuilder(dateItemNameFromXLS.substring(3,5));
            StringBuilder sbYear = new StringBuilder(dateItemNameFromXLS.substring(6,10));
            StringBuilder sbDateForConvert = new StringBuilder(sbYear + "-" + sbMonth  + "-" + sbDate);

            String sbDateStringForConvert = sbDateForConvert.toString();

            ld = LocalDate.parse(sbDateStringForConvert);
            System.out.println("dateItemNameFromXLS - " + dateItemNameFromXLS);
            System.out.println("ld - " + ld);
        }

        return ld;
    }

    // Метод копирования одной строки в другую по id, но с другим fullFileName и общая дата заголовка
    public void CopyById(long id, String newFullFileName, String newFullFileNameWithoutExtension, LocalDate newDateItemName){
        List<XlsLoadSettingsFilesEntity> result15 = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesById(id);
        result15.forEach(it15-> System.out.println(it15));
        System.out.println("result15.size = " + result15.size());
        int resultExists15 = result15.size();

        if(resultExists15 != 0){

            String itemName = result15.get(0).getItem_name();
            LocalDate dateItemName = newDateItemName;
            String armName = result15.get(0).getArm_name();
            String armLink = result15.get(0).getArm_link();
            String officerFor = result15.get(0).getOfficer_for();
            int rubricNumber = result15.get(0).getRubric_number();
            String rubricName = result15.get(0).getRubric_name();
            int bookNumber = result15.get(0).getBook_number();
            String bookName = newFullFileNameWithoutExtension;
            String fileName = newFullFileNameWithoutExtension;
            int monthNumber = LocalDate.now().getMonthValue();
            String timetable = result15.get(0).getTimetable();
            String typeRecipient = result15.get(0).getType_recipient();
            String nameRecipient = result15.get(0).getName_recipient();
            String fioUpload = result15.get(0).getFio_upload();
            LocalDateTime datetimeUpload = LocalDateTime.now();
            String systemRubricName = result15.get(0).getSystem_rubric_name();
            String systemFileName = result15.get(0).getSystem_file_name();

            if(timetable.equals("Недельно")){
                monthNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
            }

            // Записать с другим полным именем файла
            xlsLoadSettingsFilesCrudRepository.create_XlsLoadSettingsFiles_All18(
                 itemName,
                 dateItemName,
                 armName,
                 armLink,
                 officerFor,
                 rubricNumber,
                 rubricName,
                 bookNumber,
                 bookName,
                 fileName,
                 monthNumber,
                 timetable,
                 typeRecipient,
                 nameRecipient,
                 fioUpload,
                 datetimeUpload,
                 systemRubricName,
                 systemFileName
            );
        }
    }
}