package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;
import ru.taksebe.telegram.writeRead.repository.SamplesFileNameCrudRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

@Component
public class XLSLoadSamplesFilesService {

    @Autowired
    private XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;
    @Autowired
    private SamplesFileNameCrudRepository samplesFileNameCrudRepository;

    public void xlsLoadSamplesFileNewFormat() throws IOException {

        // Инициализация переменных метода
        int ourIndex = 0;

        String rubricBookNumber = "";
        String systemRubricName = "";
        String systemFileName = "";
        String fullFileName = "";
        String midFileName = "";
        String shortFileName = "";
        LocalDateTime dateSetting = LocalDateTime.now();
        String period1 = "";
        String period2 = "";
        String period3 = "";
        String period4 = "";
        String responsible = "";

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File("C:/Books/settings/samples/samples.xlsx"));

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
            rubricBookNumber = "";
            systemRubricName = "";
            systemFileName = "";
            fullFileName = "";
            midFileName = "";
            shortFileName = "";
            dateSetting = LocalDateTime.now();
            period1 = "";
            period2 = "";
            period3 = "";
            period4 = "";
            responsible = "";

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
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
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        System.out.print("STRING");
                        System.out.print(ourIndex);
                        System.out.print("\t");

                        switch (ourIndex) {
                            case 1:
                                rubricBookNumber = cell.getStringCellValue();
                                break;
                            case 2:
                                systemRubricName = cell.getStringCellValue();
                                break;
                            case 3:
                                systemFileName = cell.getStringCellValue();
                                break;
                            case 4:
                                fullFileName = cell.getStringCellValue();
                                break;
                            case 5:
                                midFileName = cell.getStringCellValue();
                                break;
                            case 6:
                                shortFileName = cell.getStringCellValue();
                                break;
                            case 7:
                                dateSetting = LocalDateTime.now();
                                break;
                            case 8:
                                period1 = cell.getStringCellValue();
                                break;
                            case 9:
                                period2 = cell.getStringCellValue();
                                break;
                            case 10:
                                period3 = cell.getStringCellValue();
                                break;
                            case 11:
                                period4 = cell.getStringCellValue();
                                break;
                            case 12:
                                responsible = cell.getStringCellValue();
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
            System.out.println("rubricBookNumber = " + rubricBookNumber);
            System.out.println("systemRubricName = " + systemRubricName);
            System.out.println("systemFileName = " + systemFileName);
            System.out.println("fullFileName = " + fullFileName);
            System.out.println("midFileName = " + midFileName);
            System.out.println("shortFileName = " + shortFileName);
            System.out.println("dateSetting = " + dateSetting);
            System.out.println("period1 = " + period1);
            System.out.println("period2 = " + period2);
            System.out.println("period3 = " + period3);
            System.out.println("period4 = " + period4);
            System.out.println("responsible = " + responsible);

            // Запись в БД
            if(rubricBookNumber.equalsIgnoreCase("Номер книжки") == false) {
                samplesFileNameCrudRepository.create_SampleFileNames_All13(
                        rubricBookNumber,
                        systemRubricName,
                        systemFileName,
                        fullFileName,
                        midFileName,
                        shortFileName,
                        dateSetting,
                        period1,
                        period2,
                        period3,
                        period4,
                        responsible
                );
            }

        }

    }

    public void xlsLoadSamplesFileNewFormat(FileInputStream fisXlsxSamples) throws IOException {

        // Инициализация переменных метода
        int ourIndex = 0;

        String rubricBookNumber = "";
        String systemRubricName = "";
        String systemFileName = "";
        String fullFileName = "";
        String midFileName = "";
        String shortFileName = "";
        LocalDateTime dateSetting = LocalDateTime.now();
        String period1 = "";
        String period2 = "";
        String period3 = "";
        String period4 = "";
        String responsible = "";

        // Read XSL file
        FileInputStream inputStream = fisXlsxSamples;

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
            rubricBookNumber = "";
            systemRubricName = "";
            systemFileName = "";
            fullFileName = "";
            midFileName = "";
            shortFileName = "";
            dateSetting = LocalDateTime.now();
            period1 = "";
            period2 = "";
            period3 = "";
            period4 = "";
            responsible = "";

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
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
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue());
                        System.out.print("STRING");
                        System.out.print(ourIndex);
                        System.out.print("\t");

                        switch (ourIndex) {
                            case 1:
                                rubricBookNumber = cell.getStringCellValue();
                                break;
                            case 2:
                                systemRubricName = cell.getStringCellValue();
                                break;
                            case 3:
                                systemFileName = cell.getStringCellValue();
                                break;
                            case 4:
                                fullFileName = cell.getStringCellValue();
                                break;
                            case 5:
                                midFileName = cell.getStringCellValue();
                                break;
                            case 6:
                                shortFileName = cell.getStringCellValue();
                                break;
                            case 7:
                                dateSetting = LocalDateTime.now();
                                break;
                            case 8:
                                period1 = cell.getStringCellValue();
                                break;
                            case 9:
                                period2 = cell.getStringCellValue();
                                break;
                            case 10:
                                period3 = cell.getStringCellValue();
                                break;
                            case 11:
                                period4 = cell.getStringCellValue();
                                break;
                            case 12:
                                responsible = cell.getStringCellValue();
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
            System.out.println("rubricBookNumber = " + rubricBookNumber);
            System.out.println("systemRubricName = " + systemRubricName);
            System.out.println("systemFileName = " + systemFileName);
            System.out.println("fullFileName = " + fullFileName);
            System.out.println("midFileName = " + midFileName);
            System.out.println("shortFileName = " + shortFileName);
            System.out.println("dateSetting = " + dateSetting);
            System.out.println("period1 = " + period1);
            System.out.println("period2 = " + period2);
            System.out.println("period3 = " + period3);
            System.out.println("period4 = " + period4);
            System.out.println("responsible = " + responsible);

            // Запись в БД
            if(rubricBookNumber.equalsIgnoreCase("Номер книжки") == false) {
                samplesFileNameCrudRepository.create_SampleFileNames_All13(
                        rubricBookNumber,
                        systemRubricName,
                        systemFileName,
                        fullFileName,
                        midFileName,
                        shortFileName,
                        dateSetting,
                        period1,
                        period2,
                        period3,
                        period4,
                        responsible
                );
            }

        }

    }


}

// 1 rubricBookNumber = "";
// 2 systemRubricName = "";
// 3 systemFileName = "";
// 4 fullFileName = "";
// 5 midFileName = "";
// 6 shortFileName = "";
// 7 dateSetting = LocalDateTime.now();
// 8 period1 = "";
// 9 period2 = "";
// 10 period3 = "";
// 11 period4 = "";
// 12 responsible = "";
