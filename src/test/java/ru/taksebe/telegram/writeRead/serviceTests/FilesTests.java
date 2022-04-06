package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryResourceFileService;
import ru.taksebe.telegram.writeRead.service.CreateFileStructureForNextYear;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryExcelService;
import ru.taksebe.telegram.writeRead.telegram.TelegramApiClient;

import java.io.IOException;

@SpringBootTest
public class FilesTests {

    @Autowired
    CreateFileStructureForNextYear createFileStructureForNextYear;
    @Autowired
    DictionaryResourceFileService dictionaryResourceFileService;
    @Autowired
    DictionaryExcelService dictionaryExcelService;
    @Autowired
    TelegramApiClient telegramApiClient;

    // Create folder
    @Test
    void CreateFolder() {
        createFileStructureForNextYear.CreateFolder();
        Assertions.assertEquals(1, 1);
    }

    // Конвертировать файлы из pdf в ByteArrayResource(byte[] byteArray)
    @Test
    void getTemplateWorkbookPdfVar2() throws IOException {
        dictionaryResourceFileService.getTemplateWorkbookPdfVar2();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void getAllDefaultDictionariesWorkbookPdf() throws IOException {
        dictionaryExcelService.getAllDefaultDictionariesWorkbookPdf();
        Assertions.assertEquals(1, 1);
    }

}


