package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.SendAndGetFilesTelegram;

import java.io.IOException;

@SpringBootTest
public class PDFSendAndGetFilesTelegramTests {

    @Autowired
    SendAndGetFilesTelegram sendAndGetFilesTelegram;

    // Resend files by id
    @Test
    void sendDocument() throws IOException {
        sendAndGetFilesTelegram.sendDocument("","");
        Assertions.assertEquals(1, 1);
    }

    // Information about bot
    @Test
    void getMe() throws IOException {
        sendAndGetFilesTelegram.getMe();
        Assertions.assertEquals(1, 1);
    }

    // Send Photo JPG
    @Test
    void sendPhotoJPG() throws IOException {
        sendAndGetFilesTelegram.sendPhotoJPG();
        Assertions.assertEquals(1, 1);
    }

    // Send Photo jpg New
    //sendImageUploadingAFile(String filePath, String chatId) {
    @Test
    void sendPhotoJPGNew() throws IOException {
        sendAndGetFilesTelegram.sendImageUploadingAFile("", "");
        Assertions.assertEquals(1, 1);
    }
}


