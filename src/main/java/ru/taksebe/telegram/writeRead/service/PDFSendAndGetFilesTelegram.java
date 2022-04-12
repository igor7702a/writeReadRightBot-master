package ru.taksebe.telegram.writeRead.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.taksebe.telegram.writeRead.constants.bot.BotMessageEnum;

import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryResourceFileService;
import ru.taksebe.telegram.writeRead.telegram.TelegramApiClient;
import ru.taksebe.telegram.writeRead.utils.FileUtils;

import java.io.IOException;

@Component
public class PDFSendAndGetFilesTelegram {

    TelegramApiClient telegramApiClient;
    DictionaryResourceFileService dictionaryResourceFileService;

    public void sendPDFDocument(String file_name, String file_id) throws IOException {
        String chatId = "5297506090";
        String token = "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4";
        String upPath = "c:/books/";
        String fullPath = "c:/books/TemplatePdf.pdf";
        file_name= "TemplatePdf.pdf";
        String file_suffix = "pdf";
        file_id = "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA";

        SendMessage sendFileResult = getTemplateNew(chatId,
                token,
                upPath,
                fullPath,
                file_name,
                file_suffix,
                file_id);

    }

    // Для одиночного pdf
    private SendMessage getTemplateNew(String chatId,
                                       String token,
                                       String upPath,
                                       String fullPath,
                                       String file_name,
                                       String file_suffix,
                                       String file_id) {

        try {
            ByteArrayResource myResult = dictionaryResourceFileService.getTemplateWorkbookOnlyPDF();
            telegramApiClient.uploadFileOnlyPDF(chatId, myResult);
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }
        return null;
    }

}
