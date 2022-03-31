package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.DOCXDownloadLetterService;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class DOCXDownloadTests {

    @Autowired
    DOCXDownloadLetterService docxDownloadLetterService;

    @Test
    void docxDownLoadEmptyLetter() throws IOException {
        docxDownloadLetterService.docxDownLoadEmptyLetter();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void docxDownLoadParagrafLetter() throws IOException {
        docxDownloadLetterService.docxDownLoadParagrafLetter();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void docxDownLoadRealLetter() throws IOException {
        docxDownloadLetterService.docxDownLoadRealLetter();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void receiveQuantityRubrics() {
        docxDownloadLetterService.receiveQuantityRubrics(2021,11,"Ежемесячно");
        Assertions.assertEquals(1, 1);
    }

}
