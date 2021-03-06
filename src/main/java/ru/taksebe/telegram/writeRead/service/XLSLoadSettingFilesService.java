package ru.taksebe.telegram.writeRead.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class XLSLoadSettingFilesService {

    @Autowired
    private XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;

    public void xlsLoadFileOldFormat() throws IOException {

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
        FileInputStream inputStream = new FileInputStream(new File("C:/Books/settings/ФайлДляЗагрузки_Old1.xls"));

        // Get the workbook instance for XLS file
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

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
    };

}

