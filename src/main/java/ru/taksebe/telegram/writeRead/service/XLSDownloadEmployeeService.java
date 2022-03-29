package ru.taksebe.telegram.writeRead.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.model.Employee;
import ru.taksebe.telegram.writeRead.model.EmployeeDAO;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;

@Component
public class XLSDownloadEmployeeService {

    @Autowired
    private XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void xlsDownLoadFileOldFormat() throws IOException {

        File file = new File("C:/Books/settings/employee.xls");
        FileOutputStream outFile = new FileOutputStream(file);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees_Sheet");

        List<Employee> list = EmployeeDAO.listEmployees();

        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // EmpNo
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("EmpNo");
        cell.setCellStyle(style);
        // EmpName
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("EmpNo");
        cell.setCellStyle(style);
        // Salary
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Salary");
        cell.setCellStyle(style);
        // Grade
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Grade");
        cell.setCellStyle(style);
        // Bonus
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Bonus");
        cell.setCellStyle(style);

        // Data
        for (Employee emp : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(emp.getEmpNo());
            // EmpName (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getEmpName());
            // Salary (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(emp.getSalary());
            // Grade (D)
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(emp.getGrade());
            // Bonus (E)
            String formula = "0.1*C" + (rownum + 1) + "*D" + (rownum + 1);
            cell = row.createCell(4, CellType.FORMULA);
            cell.setCellFormula(formula);
        }

        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }

}

//1+    itemName String
//2+    dateItemName LocalDate (String)
//3+    armName String
//4+    armLink String
//5+    officerFor String
//6+    rubricNumber int
//7+    rubricName String
//8+    bookNumber int
//9+    bookName String
//10+    fileName String
//11+    monthNumber int
//12+    timetable String
//13+    typeRecipient String
//14+    nameRecipient String
//15+    fioUpload String
//16+    datetimeUpload LocalDateTime
//17+    systemRubricName String
//18+    systemFileName String

//    Id SERIAL PRIMARY KEY,
//+    item_name VARCHAR(200),
//+    date_item_name DATE,
//+    arm_name VARCHAR(200),
//+    arm_link VARCHAR(200),
//+    officer_for VARCHAR(200),
//+    rubric_number INTEGER,
//+    rubric_name VARCHAR(150),
//+    book_number INTEGER,
//+    book_name VARCHAR(200),
//+    file_name VARCHAR(200),
//+    month_number INTEGER,
//+    timetable VARCHAR(100),
//+    type_recipient VARCHAR(100),
//+    name_recipient VARCHAR(200),
//+    fio_upload VARCHAR(200),
//+    datetime_upload TIMESTAMP,
//+    system_rubric_name VARCHAR(100),
//+    system_file_name VARCHAR(100)

//    itemName = "";
//    dateItemName = LocalDate.now();
//    armName = "";
//    armLink = "";
//    officerFor = "";
//    rubricNumber = 0;
//    rubricName = "";
//    bookNumber = 0;
//    bookName = "";
//    fileName = "";
//    monthNumber = 0;
//    timetable = "";
//    typeRecipient = "";
//    nameRecipient = "";
//    fioUpload = "";
//    datetimeUpload = LocalDateTime.now();
//    systemRubricName = "";
//    systemFileName = "";

//    itemName
//    dateItemName
//    armName
//    armLink
//    officerFor
//    rubricNumber
//    rubricName
//    bookNumber
//    bookName
//    fileName
//    monthNumber
//    timetable
//    typeRecipient
//    nameRecipient
//    fioUpload
//    datetimeUpload
//    systemRubricName
//    systemFileName


