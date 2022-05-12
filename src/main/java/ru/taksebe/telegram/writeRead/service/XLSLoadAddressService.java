package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.entity.AddressEntity;
import ru.taksebe.telegram.writeRead.repository.AddressCrudRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Component
public class XLSLoadAddressService {

    @Autowired
    private AddressCrudRepository addressCrudRepository;

    public void xlsLoadAddressNewFormat() throws IOException {

        // Инициализация переменных метода
        int ourIndex = 0;

        String typeAddress = "";
        String matterAddress = "";
        String descriptionAddress = "";
        LocalDateTime datetimeUpload = LocalDateTime.now();
        String responsible = "";


        // Read XSL file
        // - Задача: Передать имя файла как параметр
        // - Файл с правами на загрузку файлов
        //FileInputStream inputStream = new FileInputStream(new File("C:/Books/settings/usersprofiles.xlsx"));

        // Файл с правами на нажатие главных кнопок
        FileInputStream inputStream = new FileInputStream(new File("C:/Books/settings/address.xlsx"));

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

            typeAddress = "";
            matterAddress = "";
            descriptionAddress = "";
            datetimeUpload = LocalDateTime.now();
            responsible = "TarasovIY";

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
                                typeAddress = cell.getStringCellValue();
                                break;
                            case 2:
                                matterAddress = cell.getStringCellValue();
                                break;
                            case 3:
                                descriptionAddress = cell.getStringCellValue();
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
            System.out.println("typeAddress = " + typeAddress);
            System.out.println("matterAddress = " + matterAddress);
            System.out.println("descriptionAddress = " + descriptionAddress);
            System.out.println("datetimeUpload = " + datetimeUpload);
            System.out.println("responsible = " + responsible);

            // Запись в БД
            if(typeAddress.equalsIgnoreCase("Тип адреса") == false) {

                // Проверить может такая запись по 3 параметрам уже есть в базе
                List<AddressEntity> result = addressCrudRepository.findAllFromAddressBy3Param(
                        typeAddress, matterAddress, descriptionAddress);
                result.forEach(it3-> System.out.println(it3));
                System.out.println("result.size = " + result.size());
                int resultExists = result.size();
                if(resultExists == 0){
                    addressCrudRepository.create_Address_All5(
                            typeAddress,
                            matterAddress,
                            descriptionAddress,
                            LocalDateTime.now(),
                            responsible
                    );
                    System.out.println("Создана новая запись в БД! " + typeAddress + " " + matterAddress + " " + descriptionAddress);
                }else {
                    System.out.println("Такая запись уже есть в БД! " + typeAddress + " " + matterAddress + " " + descriptionAddress);
                }

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


