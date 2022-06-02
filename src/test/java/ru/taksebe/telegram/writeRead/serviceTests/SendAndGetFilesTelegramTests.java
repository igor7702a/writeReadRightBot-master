package ru.taksebe.telegram.writeRead.serviceTests;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.SendAndGetFilesTelegram;

import java.io.IOException;

@SpringBootTest
public class SendAndGetFilesTelegramTests {

    @Autowired
    SendAndGetFilesTelegram sendAndGetFilesTelegram;

    @Test
    @Operation(summary = "sendDocument", description = "Resend files by id")
    void sendDocument() throws IOException {
        sendAndGetFilesTelegram.sendDocument("","");
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "getMe", description = "Information about bot")
    void getMe() throws IOException {
        sendAndGetFilesTelegram.getMe();
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "sendPhotoJPG", description = "Send Photo JPG")
    void sendPhotoJPG() throws IOException {
        sendAndGetFilesTelegram.sendPhotoJPG();
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Operation(summary = "sendImageUploadingAFile", description = "Send Photo jpg New")
    void sendPhotoJPGNew() throws IOException {
        sendAndGetFilesTelegram.sendImageUploadingAFile("", "");
        Assertions.assertEquals(1, 1);
    }
}


