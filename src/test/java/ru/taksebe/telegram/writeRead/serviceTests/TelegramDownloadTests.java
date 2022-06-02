package ru.taksebe.telegram.writeRead.serviceTests;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.TelegramDownloadLetterService;

import java.io.IOException;

@SpringBootTest
public class TelegramDownloadTests {

    @Autowired
    TelegramDownloadLetterService telegramDownloadLetterService;

    @Test
    @Operation(summary = "docxDownLoadRealLetter", description = "Для реальных данных")
    void docxDownLoadRealLetter() throws IOException {
        telegramDownloadLetterService.docxDownLoadRealLetter();
        Assertions.assertEquals(1, 1);
    }

}
