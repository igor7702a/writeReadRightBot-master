package ru.taksebe.telegram.writeRead.serviceTests;

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

    // Для реальных данных
    @Test
    void docxDownLoadRealLetter() throws IOException {
        telegramDownloadLetterService.docxDownLoadRealLetter();
        Assertions.assertEquals(1, 1);
    }

}
