package ru.taksebe.telegram.writeRead.telegram.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryExcelService;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryResourceFileService;
import ru.taksebe.telegram.writeRead.api.tasks.TaskService;
import ru.taksebe.telegram.writeRead.constants.bot.BotMessageEnum;
import ru.taksebe.telegram.writeRead.constants.bot.CallbackDataPartsEnum;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnum;
import ru.taksebe.telegram.writeRead.exceptions.UserDictionaryNotFoundException;
import ru.taksebe.telegram.writeRead.telegram.TelegramApiClient;

// Для работы с pdf
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CallbackQueryHandler {
    TelegramApiClient telegramApiClient;
    TaskService taskService;
    DictionaryExcelService dictionaryExcelService;
    DictionaryResourceFileService dictionaryResourceFileService;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        if (data.equals(CallbackDataPartsEnum.TASK_.name() + CallbackDataPartsEnum.USER_DICTIONARY.name())) {
            System.out.println("Это работает кнопка - " + "CallbackDataPartsEnum.USER_DICTIONARY");
            return getDictionaryTasks(chatId, chatId, "personal dictionary");
        } else if (data.equals(CallbackDataPartsEnum.TASK_.name() + CallbackDataPartsEnum.ALL_GRADES.name())) {
            System.out.println("Это работает кнопка - " + "CallbackDataPartsEnum.ALL_GRADES");
            return getAllDictionaryTasks(chatId);
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.USER_DICTIONARY.name())) {
            System.out.println("Это работает кнопка - " + "CallbackDataPartsEnum.DICTIONARY_");
            System.out.println("Это работает кнопка - " + CallbackDataPartsEnum.DICTIONARY_.name());
            return getDictionary(chatId, chatId);
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.ALL_GRADES.name())) {
            System.out.println("Это работает кнопка - " + "CallbackDataPartsEnum.ALL_GRADES");
            return getAllDefaultDictionaries(chatId);

        // Для новых инлайн клавиатур
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.ALL_GRADES_PDF.name())) {
            System.out.println("Это работает клавиатура Все классы PDF");
            return getAllDefaultDictionariesPdf(chatId);
        // Одиночного pdf файла
        }else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.TEMPLATE_NEW.name())) {
            System.out.println("Это работает кнопка - " + "CallbackDataPartsEnum.TEMPLATE_NEW");
            System.out.println("Это работает кнопка - " + CallbackDataPartsEnum.TEMPLATE_NEW.name());
            SendMessage myResult = getTemplateNew(chatId);
            return myResult;
        } else {
            System.out.println("Это работает кнопка - " + "handleDefaultDictionary");
            return handleDefaultDictionary(chatId, data);
        }
    }

    private SendMessage handleDefaultDictionary(String chatId, String data) throws IOException {
        if (data.startsWith(CallbackDataPartsEnum.TASK_.name())) {
            DictionaryResourcePathEnum resourcePath = DictionaryResourcePathEnum.valueOf(
                    data.substring(CallbackDataPartsEnum.TASK_.name().length())
            );
            System.out.println("Это работает кнопка - " + "CallbackDataPartsEnum.TASK_.name");
            return getDictionaryTasks(chatId, resourcePath.name(), resourcePath.getFileName());
        } else if (data.startsWith(CallbackDataPartsEnum.DICTIONARY_.name())) {
            System.out.println("Это работает кнопка - " + "CallbackDataPartsEnum.DICTIONARY_.name");
            return getDictionary(chatId, data.substring(CallbackDataPartsEnum.DICTIONARY_.name().length()));
        } else {
            System.out.println("Это работает кнопка - " + "BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE");
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE.getMessage());
        }
    }

    private SendMessage getDictionaryTasks(String chatId, String dictionaryId, String fileName) throws IOException {
        try {
            ByteArrayResource param1 = taskService.getTasksDocument(dictionaryId, fileName);
            telegramApiClient.uploadFile(chatId, param1);
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TASKS_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    private SendMessage getAllDictionaryTasks(String chatId) throws IOException {
        try {
            telegramApiClient.uploadFile(chatId, taskService.getAllDefaultDictionariesTasksDocument());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TASKS_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    private SendMessage getDictionary(String chatId, String dictionaryId) {
        try {
            telegramApiClient.uploadFile(chatId, dictionaryExcelService.getDictionaryWorkbook(dictionaryId));
        } catch (UserDictionaryNotFoundException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    private SendMessage getAllDefaultDictionaries(String chatId) {
        try {
            ByteArrayResource myResult = dictionaryExcelService.getAllDefaultDictionariesWorkbook();
            telegramApiClient.uploadFile(chatId, myResult);
        } catch (UserDictionaryNotFoundException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    private SendMessage getTemplate(String chatId) {
        try {
            telegramApiClient.uploadFile(chatId, dictionaryResourceFileService.getTemplateWorkbook());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    // PowerPoint
    private SendMessage getTemplatePPTX(String chatId) {
        try {
            ByteArrayResource myResult = dictionaryResourceFileService.getTemplateWorkbookNewType();
            telegramApiClient.uploadFileNewType(chatId, myResult);
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }
        return null;
    }
    // Для одиночного pdf - проверка пересылки с клавиатуры + инлайн
    private SendMessage getTemplateNew(String chatId) {
        try {
            ByteArrayResource myResult = dictionaryResourceFileService.getTemplateWorkbookNewType();
            telegramApiClient.uploadFileNewType(chatId, myResult);
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    // Только для PDF - есть тест
    public SendMessage getTemplateOnlyPDF(
            String chatId,
            String token,
            String upPath,
            String fullPath,
            String file_name,
            String file_suffix,
            String file_id
    ) {
        try {
            ByteArrayResource myResult = dictionaryResourceFileService.getTemplateWorkbookOnlyPDF(
                        chatId,
                        token,
                        upPath,
                        fullPath,
                        file_name,
                        file_suffix,
                        file_id
            );
            telegramApiClient.uploadFileOnlyPDF(
                    chatId,
                    myResult,
                    token,
                    upPath,
                    fullPath,
                    file_name,
                    file_suffix,
                    file_id
            );
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    // Добавить конвертацию Pdf в байт код
    private SendMessage getTemplatePdf(String chatId) {
        try {
            telegramApiClient.uploadFile(chatId, dictionaryResourceFileService.getTemplateWorkbookPdf());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }
        return null;
    }

    // Для новой инлайн клавиатуры
    private SendMessage getAllDefaultDictionariesPdf(String chatId) {
        try {
            System.out.println("Это работает пересылка файла");
            ByteArrayResource myResult = dictionaryExcelService.getAllDefaultDictionariesWorkbookPdf();
            telegramApiClient.uploadFile(chatId, myResult);

        } catch (UserDictionaryNotFoundException e) {
            System.out.println("catch - EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE");
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE.getMessage());
        } catch (Exception e) {
            System.out.println("catch - EXCEPTION_DICTIONARY_WTF_MESSAGE");
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_WTF_MESSAGE.getMessage());
        }
        return null;
    }

}