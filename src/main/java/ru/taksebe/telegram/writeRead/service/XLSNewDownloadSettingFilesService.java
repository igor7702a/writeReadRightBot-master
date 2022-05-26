package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.model.Employee;
import ru.taksebe.telegram.writeRead.model.EmployeeDAO;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class XLSNewDownloadSettingFilesService {

    @Autowired
    private XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void xlsDownLoadFileNewFormat() throws IOException {

        File file = new File("C:/Books/settings/SettingFiles.xlsx");
        FileOutputStream outFile = new FileOutputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employees_Sheet");

        List<Employee> list = EmployeeDAO.listEmployees();

        int rownum = 0;
        Cell cell;
        Row row;

        XSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Название темы");
        cell.setCellStyle(style);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Дата названия");
        cell.setCellStyle(style);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Название АРМ");
        cell.setCellStyle(style);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Ссылка на АРМ");
        cell.setCellStyle(style);

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Ответственный");
        cell.setCellStyle(style);

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Номер рубрики");
        cell.setCellStyle(style);

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Название рубрики");
        cell.setCellStyle(style);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Номер книжки");
        cell.setCellStyle(style);

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Название книжки");
        cell.setCellStyle(style);

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Название файла");
        cell.setCellStyle(style);

        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("Номер месяца");
        cell.setCellStyle(style);

        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Тип рассылки");
        cell.setCellStyle(style);

        cell = row.createCell(12, CellType.STRING);
        cell.setCellValue("Тип получателя");
        cell.setCellStyle(style);

        cell = row.createCell(13, CellType.STRING);
        cell.setCellValue("Имя получателя");
        cell.setCellStyle(style);

        cell = row.createCell(14, CellType.STRING);
        cell.setCellValue("ФИО загрузившего");
        cell.setCellStyle(style);

        cell = row.createCell(15, CellType.STRING);
        cell.setCellValue("Дата и время загрузки");
        cell.setCellStyle(style);

        cell = row.createCell(16, CellType.STRING);
        cell.setCellValue("Системное имя рубрики");
        cell.setCellStyle(style);

        cell = row.createCell(17, CellType.STRING);
        cell.setCellValue("Системное имя книжки");
        cell.setCellStyle(style);

        // Data
        List<XlsLoadSettingsFilesEntity> result = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles();
        result.forEach(it3-> System.out.println(it3));
        System.out.println("result.size = " + result.size());
        int resultExists = result.size();

        if(resultExists != 0){

            for (XlsLoadSettingsFilesEntity element : result) {

                rownum++;
                row = sheet.createRow(rownum);

                // Название темы
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(element.getItem_name());

                // Дата названия
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(element.getDate_item_name());

                // Название АРМ
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(element.getArm_name());

                // Ссылка на АРМ
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(element.getArm_link());

                // Ответственный
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(element.getOfficer_for());

                // Номер рубрики
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(element.getRubric_number());

                // Название рубрики
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(element.getRubric_name());

                // Номер книжки
                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(element.getBook_number());

                // Название книжки
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(element.getBook_name());

                // Название файла
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(element.getFile_name());

                // Номер месяца
                cell = row.createCell(10, CellType.NUMERIC);
                cell.setCellValue(element.getMonth_number());

                // Тип рассылки
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue(element.getTimetable());

                // Тип получателя
                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue(element.getType_recipient());

                //Имя получателя
                cell = row.createCell(13, CellType.STRING);
                cell.setCellValue(element.getName_recipient());

                // ФИО загрузившего
                cell = row.createCell(14, CellType.STRING);
                cell.setCellValue(element.getFio_upload());

                // Дата и время загрузки
                cell = row.createCell(15, CellType.STRING);
                cell.setCellValue(element.getDatetime_upload());

                // Системное имя рубрики
                cell = row.createCell(16, CellType.STRING);
                cell.setCellValue(element.getSystem_rubric_name());

                // Системное имя книжки
                cell = row.createCell(17, CellType.STRING);
                cell.setCellValue(element.getSystem_file_name());
            }
        }
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }

}
