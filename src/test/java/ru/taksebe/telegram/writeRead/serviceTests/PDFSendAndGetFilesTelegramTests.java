package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.telegram.handlers.CallbackQueryHandler;

import java.io.IOException;

@SpringBootTest
public class PDFSendAndGetFilesTelegramTests {

    @Autowired
    CallbackQueryHandler callbackQueryHandler;

    @Test
    void sendPDFDocument() throws IOException {
        callbackQueryHandler.getTemplateOnlyPDF(
"5297506090",
"5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4",
"c:/books/",
"c:/books/MyFile.pdf",
"MyFile",
"pdf",
"AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA"
        );
        Assertions.assertEquals(1, 1);
    }

}

//    String chatId = "5297506090";
//    String token = "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4";
//    String upPath = "c:/books/";
//    String fullPath = "c:/books/TemplatePdf.pdf";
//    String file_name= "TemplatePdf.pdf";
//    String file_suffix = "pdf";
//    String file_id = "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA";

//    "5297506090",
//    "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4",
//    "c:/books/",
//    "c:/books/TemplatePdf.pdf",
//    "TemplatePdf.pdf",
//    "pdf",
//    "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA"

//    String chatId,
//    String token,
//    String upPath,
//    String fullPath,
//    String file_name,
//    String file_suffix,
//    String file_id

//    chatId,
//    token,
//    upPath,
//    fullPath,
//    file_name,
//    file_suffix,
//    file_id