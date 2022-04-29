package ru.taksebe.telegram.writeRead.api.dictionaries;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.exceptions.DictionaryTooBigException;
import ru.taksebe.telegram.writeRead.model.Dictionary;
import ru.taksebe.telegram.writeRead.model.Word;
import ru.taksebe.telegram.writeRead.service.SaveFiles;
import ru.taksebe.telegram.writeRead.service.OSValidator;
import ru.taksebe.telegram.writeRead.repository.SavedFilesCrudRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DictionaryMaterialsAdditionService {

    @Autowired
    SaveFiles saveFiles;
    @Autowired
    OSValidator osValidator;
    @Autowired
    SavedFilesCrudRepository savedFilesCrudRepository;

    private final DictionaryRepository repository;

    public void addUserDictionaryMaterials(String userId, File file, String fileName, String userName) throws IOException {
        // Этап 1: Запись файла в папку Books
        // Этап 1.2: Добавить пути Linux
        // Этап 2: Запись информации в таблицу Лог в БД
        // Этап 3: Оставить только необходимые названия файлов
        //  Например 1.1_УД_НП_проектный_офис_220331_153802
        //  Например 1.2_НП_касса_2020vs2021
        //  Например 1.3_Справка - опросы ВЦИОМ_220429_110400.pdf

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            // Этап 1: Запись файла в папку Books
            String fileSourceName = fileName;
            String fileDestName = fileName;
            String pathFile = saveFiles.GetPathFile(fileName);
            //String pathFile = "";
            String fileNameExtension = saveFiles.getFileExtension(fileName);
            String fileNameWithoutExtension = fileName.replace(("." + fileNameExtension), "");
            String DateForFilename = saveFiles.GetStringDateForNameFile();

            // For OS +
            String myOS = osValidator.returnOS();
            String pathOS = "";

            if(myOS == "This is Windows"){
                pathOS = "c:/Books/files/";
            }
            else if(myOS == "This is Unix or Linux"){
                pathOS = "/home/svc_chatbot/Books/files/";
            }
            else {
            }
            StringBuilder sbPath = new StringBuilder(pathOS);
            // -
            StringBuilder fullPathSource = new StringBuilder(pathOS + pathFile + fileSourceName);
            StringBuilder fullPathDest = new StringBuilder(
                    pathOS + pathFile + fileNameWithoutExtension + "_" + DateForFilename + "." + fileNameExtension);
            File source = file;
            File  dest = new File(fullPathDest.toString());
            saveFiles.copyFileUsingApacheCommonsIO(source, dest);

            // Этап 2: Запись информации в таблицу Лог в БД
            savedFilesCrudRepository.create_SavedFiles_All6(
                    fileNameWithoutExtension + "_" + DateForFilename + "." + fileNameExtension,
                    fullPathDest.toString(),
                    LocalDateTime.now(),
                    "TarasovIY",
                    "DescriptionFromTelegram",
                    "Good"
            );
        }
    }

    public void addDefaultDictionary(String dictionaryId, XSSFWorkbook workbook) {
        repository.save(Dictionary.builder().id(dictionaryId).wordList(createDictionary(workbook)).build());
    }

    private List<Word> createDictionary(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        List<Word> result = new ArrayList<>();
        while (rowIterator.hasNext()) {
            result.add(createDictionaryWord(rowIterator.next()));
        }
        result.remove(0);

        if (result.size() > 1000) {
            throw new DictionaryTooBigException();
        }
        return result;
    }

    private Word createDictionaryWord(Row row) {
        Iterator<Cell> cellIterator = row.iterator();

        List<String> line = new ArrayList<>();
        while (cellIterator.hasNext()) {
            line.add(cellIterator.next().getStringCellValue());
        }

        String key = line.get(0);
        line.remove(0);

        return new Word(key, new HashSet<>(line));
    }
}