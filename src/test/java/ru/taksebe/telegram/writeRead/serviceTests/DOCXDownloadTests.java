package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.DOCXDownloadLetterService;

import java.io.IOException;

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

}
