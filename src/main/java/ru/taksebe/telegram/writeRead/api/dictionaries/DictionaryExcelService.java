package ru.taksebe.telegram.writeRead.api.dictionaries;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryPdfResourcePathEnum;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnum;
import ru.taksebe.telegram.writeRead.exceptions.UserDictionaryNotFoundException;
import ru.taksebe.telegram.writeRead.model.Dictionary;
import ru.taksebe.telegram.writeRead.model.Word;
import ru.taksebe.telegram.writeRead.utils.FileUtils;
import ru.taksebe.telegram.writeRead.utils.ResourceLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DictionaryExcelService {
    DictionaryRepository repository;
    WordService wordService;
    ResourceLoader resourceLoader;

    public boolean isUserDictionaryExist(String id) {
        return repository.existsById(id);
    }

    public ByteArrayResource getAllDefaultDictionariesWorkbook() throws IOException {
        List<Dictionary> defaultDictionaryList = Arrays.stream(DictionaryResourcePathEnum.values())
                .map(resourcePath -> repository.findById(resourcePath.name()).orElseThrow(UserDictionaryNotFoundException::new))
                .collect(Collectors.toList());
        return createWorkbookByteArray(defaultDictionaryList, "All grades");
    }

    public ByteArrayResource getDictionaryWorkbook(String id) throws IOException {
        Dictionary dictionary = repository.findById(id).orElseThrow(UserDictionaryNotFoundException::new);
        return createWorkbookByteArray(Collections.singletonList(dictionary), getFileName(id));
    }

    private ByteArrayResource createWorkbookByteArray(List<Dictionary> dictionaryList, String fileName) throws IOException {
        XSSFWorkbook workbook = createWorkbook(dictionaryList);
        return FileUtils.createOfficeDocumentResource(workbook, fileName, ".xlsx");
    }

    private XSSFWorkbook createWorkbook(List<Dictionary> dictionaryList) throws IOException {
        XSSFWorkbook workbook = resourceLoader.loadTemplateWorkbook();
        if (dictionaryList.isEmpty()) {
            return workbook;
        }

        List<Word> wordList = wordService.getDictionariesWordList(dictionaryList);
        wordList.sort(Comparator.comparing(Word::getWord, String::compareToIgnoreCase));
        XSSFSheet sheet = workbook.getSheetAt(0);
        writeDictionary(sheet, wordList);

        return workbook;
    }

    private void writeDictionary(XSSFSheet sheet, List<Word> wordList) {
        int rowNumber = 1;
        for (Word word : wordList) {
            Row row = sheet.createRow(rowNumber++);
            int cellNum = 0;
            List<String> dictionaryWordList = new ArrayList<>(word.getMistakes());
            dictionaryWordList.add(0, word.getWord());
            for (String value : dictionaryWordList) {
                Cell cell = row.createCell(cellNum++);
                cell.setCellValue(value);
            }
        }
    }

    private String getFileName(String id) {
        List<String> defaultDictionaryNames = Arrays.stream(DictionaryResourcePathEnum.values())
                .filter(dictionaryResourcePathEnum -> dictionaryResourcePathEnum.name().equals(id))
                .map(DictionaryResourcePathEnum::getFileName)
                .collect(Collectors.toList());
        return defaultDictionaryNames.isEmpty() ? "Personal dictionary" : defaultDictionaryNames.get(0);
    }

    // Для тестирования отправки Pdf вариант 1
    public ByteArrayResource getAllDefaultDictionariesWorkbookPdfVar1() throws IOException {
        File file = new File("c:\\Books\\files\\2021\\months\\11\\нацпроекты\\ТабКасИсп\\1.2_НП_касса_2020vs2021_220331_153802.pdf");
        FileInputStream fis = new FileInputStream(file);

        byte [] data = new byte[(int)file.length()];
        fis.read(data);
        System.out.println("data.length - " + data.length);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        data = bos.toByteArray();
        ByteArrayResource myResPdf = new ByteArrayResource(data);
        return myResPdf;

    }

    public ByteArrayResource getAllDefaultDictionariesWorkbookPdf() throws IOException {
        DictionaryPdfResourcePathEnum[] param1 = DictionaryPdfResourcePathEnum.values();
        List<Dictionary> defaultDictionaryList = Arrays.stream(param1)
                .map(resourcePath -> repository.findById(resourcePath.name()).orElseThrow(UserDictionaryNotFoundException::new))
                .collect(Collectors.toList());
        ByteArrayResource myResBAR = createWorkbookByteArrayPdf(defaultDictionaryList, "All grades");
        return myResBAR;
    }

    // Для тестирования работы с Pdf
    private ByteArrayResource createWorkbookByteArrayPdf(List<Dictionary> dictionaryList, String fileName) throws IOException {
        XSSFWorkbook workbook = createWorkbookPdf(dictionaryList);
        // 06.04.22 Замена суффикса с .xlsx на .pdf
        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResourcePdf(workbook, fileName, ".pdf");
        return myResBAR;
    }

    private XSSFWorkbook createWorkbookPdf(List<Dictionary> dictionaryList) throws IOException {
        XSSFWorkbook workbook = resourceLoader.loadTemplateWorkbookPdf();
        if (dictionaryList.isEmpty()) {
            return workbook;
        }

        List<Word> wordList = wordService.getDictionariesWordListPdf(dictionaryList);
        wordList.sort(Comparator.comparing(Word::getWord, String::compareToIgnoreCase));
        XSSFSheet sheet = workbook.getSheetAt(0);
        writeDictionary(sheet, wordList);

        return workbook;
    }

}