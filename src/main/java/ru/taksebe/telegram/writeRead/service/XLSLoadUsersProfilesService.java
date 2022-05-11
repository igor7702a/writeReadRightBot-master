package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.repository.UsersProfilesCrudRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

@Component
public class XLSLoadUsersProfilesService {

    @Autowired
    private UsersProfilesCrudRepository usersProfilesCrudRepository;

    public void xlsLoadUsersProfilesNewFormat() throws IOException {

        // Инициализация переменных метода
        int ourIndex = 0;

        String rubricBookNumber = "";
        String systemRubricName = "";
        String systemFileName = "";
        String fullFileName = "";
        String accessType = "";
        String tgUser = "";
        LocalDateTime dateSetting = LocalDateTime.now();
        String responsible = "";
        String userFio = "";

        // Read XSL file
        // - Задача: Передать имя файла как параметр
        // - Файл с правами на загрузку файлов
        //FileInputStream inputStream = new FileInputStream(new File("C:/Books/settings/usersprofiles.xlsx"));

        // Файл с правами на нажатие главных кнопок
        FileInputStream inputStream = new FileInputStream(new File("C:/Books/settings/usersProfilesPressButtons.xlsx"));

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
            accessType = "";
            tgUser = "";
            dateSetting = LocalDateTime.now();
            responsible = "";
            userFio = "";

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
                                accessType = cell.getStringCellValue();
                                break;
                            case 6:
                                tgUser = cell.getStringCellValue();
                                break;
                            case 7:
                                dateSetting = LocalDateTime.now();
                                break;
                            case 8:
                                responsible = cell.getStringCellValue();
                                break;
                            case 9:
                                userFio = cell.getStringCellValue();
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
            System.out.println("accessType = " + accessType);
            System.out.println("tgUser = " + tgUser);
            System.out.println("dateSetting = " + dateSetting);
            System.out.println("responsible = " + responsible);
            System.out.println("userFio = " + userFio);

            // Запись в БД
            if(rubricBookNumber.equalsIgnoreCase("Номер книжки") == false) {
                usersProfilesCrudRepository.create_UserProfiles_All8(
                        rubricBookNumber,
                        systemRubricName,
                        systemFileName,
                        fullFileName,
                        accessType,
                        tgUser,
                        dateSetting,
                        responsible,
                        userFio
                );
            }

        }

    }

}

// rubric_book_number VARCHAR(15),
// system_rubric_name VARCHAR(100),
// system_file_name VARCHAR(100),
// full_file_name VARCHAR(250),
// access_type VARCHAR(50),
// tg_user VARCHAR(150),
// date_setting timestamp,
// responsible VARCHAR(100)
// user_fio VARCHAR(150)


