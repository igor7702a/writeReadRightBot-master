package ru.taksebe.telegram.writeRead.serviceTests;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.telegram.handlers.CallbackQueryHandler;

import java.io.IOException;

@SpringBootTest
@Tag(name = "PDFSendAndGetFilesTelegramTests", description = "Тест по работе с документами формата PDF")
public class PDFSendAndGetFilesTelegramTests {

    @Autowired
    CallbackQueryHandler callbackQueryHandler;

    @Test
    // test_bot "5297506090", test_group "-684336344"
    void sendPDFDocument() throws IOException {
        callbackQueryHandler.getTemplateOnlyPDF(
    "-684336344",
    "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4",
    "c:/books/",
    "c:/books/MyFile.pdf",
    "MyFile",
    "pdf",
    "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA"
        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void sendPDFDocumentUnix() throws IOException {
        callbackQueryHandler.getTemplateOnlyPDF(
                "5297506090",
                "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4",
                "/home/svc_chatbot/Books/",
                "/home/svc_chatbot/Books/MyFile.pdf",
                "MyFile",
                "pdf",
                "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA"
        );
        Assertions.assertEquals(1, 1);
    }

}