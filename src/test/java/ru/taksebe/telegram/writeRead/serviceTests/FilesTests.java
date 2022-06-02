package ru.taksebe.telegram.writeRead.serviceTests;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryResourceFileService;
import ru.taksebe.telegram.writeRead.service.CreateFileStructureForNextYear;
import ru.taksebe.telegram.writeRead.service.SaveFiles;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryExcelService;
import ru.taksebe.telegram.writeRead.telegram.TelegramApiClient;

import java.io.IOException;

@SpringBootTest
@Tag(name = "FilesTests", description = "Тест по работе с файлами")
public class FilesTests {

    @Autowired
    CreateFileStructureForNextYear createFileStructureForNextYear;
    @Autowired
    DictionaryResourceFileService dictionaryResourceFileService;
    @Autowired
    DictionaryExcelService dictionaryExcelService;
    @Autowired
    TelegramApiClient telegramApiClient;
    @Autowired
    SaveFiles saveFiles;

    @Test
    @Operation(summary = "CreateFolder", description = "Create folder")
    void CreateFolder() {
        createFileStructureForNextYear.CreateFolder();
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "xlsLoadFileNewFormat", description = "Конвертировать файлы из pdf в ByteArrayResource(byte[] byteArray)")
    void getTemplateWorkbookPdfVar2() throws IOException {
        dictionaryResourceFileService.getTemplateWorkbookPdfVar2();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void getAllDefaultDictionariesWorkbookPdf() throws IOException {
        dictionaryExcelService.getAllDefaultDictionariesWorkbookPdf();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void findFirst3SymbolsInFileName() throws IOException {
        int index = 0;
        StringBuilder sb = new StringBuilder("");
        //String FileName = "2.1 Справка риски.pdf";
        String FileName = "2_2_Таблица_общая_сведения_о_рисках_на_01_12_2021.pdf";

        for (int i = 0, n = FileName.length(); i < n; i++) {
            ++index;
            if(index > 3){break;}

            char symb = FileName.charAt(i);
            if(symb == '0'
                    || symb == '1'
                    || symb == '2'
                    || symb == '3'
                    || symb == '4'
                    || symb == '5'
                    || symb == '6'
                    || symb == '7'
                    || symb == '8'
                    || symb == '9'
            ){
                sb.append(String.valueOf(symb));
            } else if (symb == '.' || symb == '_'){
                sb.append(String.valueOf('.'));
            }
            System.out.println(sb);

        }
        Assertions.assertEquals(1, 1);
    }

    @Test
    void findFirst3SymbolsInFileNameNotVoid() throws IOException {
        int index = 0;
        StringBuilder sb = new StringBuilder("");
        String FileName = "2.1 Справка риски.pdf";
        saveFiles.findFirst3SymbolsInFileName(FileName);
        Assertions.assertEquals(1, 1);
    }

}


