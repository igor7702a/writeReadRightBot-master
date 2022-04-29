package ru.taksebe.telegram.writeRead.telegram.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryAdditionService;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryMaterialsAdditionService;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryExcelService;
import ru.taksebe.telegram.writeRead.constants.bot.BotMessageEnum;
import ru.taksebe.telegram.writeRead.constants.bot.ButtonNameEnum;
import ru.taksebe.telegram.writeRead.constants.bot.CallbackDataPartsEnum;
import ru.taksebe.telegram.writeRead.exceptions.DictionaryTooBigException;
import ru.taksebe.telegram.writeRead.exceptions.TelegramFileNotFoundException;
import ru.taksebe.telegram.writeRead.telegram.TelegramApiClient;
import ru.taksebe.telegram.writeRead.telegram.keyboards.InlineKeyboardMaker;
import ru.taksebe.telegram.writeRead.telegram.keyboards.InlineKeyboardMakerPdf;
import ru.taksebe.telegram.writeRead.telegram.keyboards.ReplyKeyboardMaker;
import ru.taksebe.telegram.writeRead.telegram.handlers.CallbackQueryHandler;

import ru.taksebe.telegram.writeRead.service.TelegramDownloadLetterService;

import java.io.IOException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageHandler {
    @Autowired
    TelegramDownloadLetterService telegramDownloadLetterService;
    @Autowired
    CallbackQueryHandler callbackQueryHandler;

    DictionaryAdditionService dictionaryAdditionService;
    DictionaryMaterialsAdditionService dictionaryMaterialsAdditionService;
    DictionaryExcelService dictionaryExcelService;

    TelegramApiClient telegramApiClient;
    ReplyKeyboardMaker replyKeyboardMaker;
    InlineKeyboardMaker inlineKeyboardMaker;
    InlineKeyboardMakerPdf inlineKeyboardMakerPdf;

    public BotApiMethod<?> answerMessage(Message message) throws IOException {
        String chatId = message.getChatId().toString();

        if (message.hasDocument()) {

            // Здесь обработка файла для шаблона Материалы
            // проверка на имя пользователя и запись файла в папку
            SendMessage myResult = addUserDictionaryMaterials(
                    chatId,
                    message.getDocument().getFileId(),
                    message.getDocument().getFileName().toString(),
                    message.getFrom().getUserName().toString()
            );

            // Здесь далее обработка файла эксель в начальном шаблоне
            //SendMessage myResult = addUserDictionary(chatId, message.getDocument().getFileId());
            return myResult;
        }

        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            SendMessage myResult = getStartMessage(chatId);
        return myResult;

            // new buttons for materials
        } else if (inputText.equals(ButtonNameEnum.UPLOAD_MATERIALS_BUTTON.getButtonName())) {
            System.out.println("Это работает новая кнопка!");
            return getStartMessageMaterials(chatId);

        } else if (inputText.equals(ButtonNameEnum.UPLOAD_MATERIALS_WITH_FILES_BUTTON.getButtonName())) {
            System.out.println("Это работает новая кнопка рассылки материалов с файлами!");
            SendMessage sendResult = getStartMessageMaterialsWithFiles(chatId);
            return sendResult;

        } else if (inputText.equals(ButtonNameEnum.UPLOAD_FILES_BUTTON.getButtonName())) {
            System.out.println("Это работает новая кнопка Тест пересылки файлов!");
            return getDictionaryMessageFiles(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_TASKS_BUTTON.getButtonName())) {
            return getTasksMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_DICTIONARY_BUTTON.getButtonName())) {
            return getDictionaryMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.UPLOAD_DICTIONARY_BUTTON.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.UPLOAD_DICTIONARY_MESSAGE.getMessage());
        } else if (inputText.equals(ButtonNameEnum.HELP_BUTTON.getButtonName())) {
            SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
            sendMessage.enableMarkdown(true);
            return sendMessage;
        } else {
            return new SendMessage(chatId, BotMessageEnum.NON_COMMAND_MESSAGE.getMessage());
        }
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getTasksMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.CHOOSE_DICTIONARY_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getInlineMessageButtons(
                CallbackDataPartsEnum.TASK_.name(),
                dictionaryExcelService.isUserDictionaryExist(chatId)
        ));
        return sendMessage;
    }

    private SendMessage getDictionaryMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.CHOOSE_DICTIONARY_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getInlineMessageButtonsWithTemplate(
                CallbackDataPartsEnum.DICTIONARY_.name(),
                dictionaryExcelService.isUserDictionaryExist(chatId)
        ));
        return sendMessage;
    }

    private SendMessage addUserDictionary(String chatId, String fileId) {
        try {
            // Для Эксель
            dictionaryAdditionService.addUserDictionary(chatId, telegramApiClient.getDocumentFile(fileId));
            // Для pdf
            //dictionaryMaterialsAdditionService.addUserDictionary(chatId, telegramApiClient.getDocumentFile(fileId));
            return new SendMessage(chatId, BotMessageEnum.SUCCESS_UPLOAD_MESSAGE.getMessage());
        } catch (TelegramFileNotFoundException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TELEGRAM_API_MESSAGE.getMessage());
        } catch (DictionaryTooBigException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TOO_LARGE_DICTIONARY_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_BAD_FILE_MESSAGE.getMessage());
        }
    }

    // Для записи файлов, здесь определяется в какую папку записать файл и
    // от какого пользователя можно записывать файлы (Добавить имя файла и Логин пользователя)
    // message.document.fileName message.from.userName
    // Этап 1: Запись файла просто в папку Books
    // Этап 2: Запись информации в лог загрузки

    private SendMessage addUserDictionaryMaterials(String chatId, String fileId, String fileName, String userName) {
        try {
            // Для Эксель
            //dictionaryAdditionService.addUserDictionary(chatId, telegramApiClient.getDocumentFile(fileId));
            // Для pdf
            dictionaryMaterialsAdditionService.addUserDictionaryMaterials(
                    chatId,
                    telegramApiClient.getDocumentFile(fileId),
                    fileName,
                    userName);
            return new SendMessage(chatId, BotMessageEnum.SUCCESS_UPLOAD_MESSAGE.getMessage());
        } catch (TelegramFileNotFoundException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TELEGRAM_API_MESSAGE.getMessage());
        } catch (DictionaryTooBigException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TOO_LARGE_DICTIONARY_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_BAD_FILE_MESSAGE.getMessage());
        }
    }

    // Для новых кнопок
    // Получаем текстовое письмо без пересылки файлов
    private SendMessage getStartMessageMaterials(String chatId) throws IOException {
        String myResult = telegramDownloadLetterService.docxDownLoadRealLetter();
        SendMessage sendMessage = new SendMessage(chatId, myResult);
        System.out.println("chatId - " + chatId);
        sendMessage.setParseMode("HTML");
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        String myParseMode = sendMessage.getParseMode();

        return sendMessage;
    }

    // Получаем текстовое письмо + пересылкa файлов - на новой кнопке
    private SendMessage getStartMessageMaterialsWithFiles(String chatId) throws IOException {
        // Цикл по пунктам 1-3, получить запросом количество пунктов, организовать цикл по пуктам
        // В цикле получить сообщение и список файлов и отправить.
        // Получаем текст первой части сообщения
        // Пересылка сообщения в телеграм

        String myResult = telegramDownloadLetterService.docxDownLoadRealLetterWithFiles();
        SendMessage sendMessage = new SendMessage(chatId, myResult);
        System.out.println("chatId - " + chatId);
        sendMessage.setParseMode("HTML");
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        String myParseMode = sendMessage.getParseMode();

        return sendMessage;
    }

    // Для тестирования рассылки файлов
    private SendMessage getDictionaryMessageFiles(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.CHOOSE_DICTIONARY_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(inlineKeyboardMakerPdf.getInlineMessageButtonsWithTemplate(
                CallbackDataPartsEnum.DICTIONARY_.name(),
                dictionaryExcelService.isUserDictionaryExist(chatId)
        ));
        return sendMessage;
    }
}