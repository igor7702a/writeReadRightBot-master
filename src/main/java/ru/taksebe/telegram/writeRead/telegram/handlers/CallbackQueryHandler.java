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
            return getDictionaryTasks(chatId, chatId, "personal dictionary");
        } else if (data.equals(CallbackDataPartsEnum.TASK_.name() + CallbackDataPartsEnum.ALL_GRADES.name())) {
            return getAllDictionaryTasks(chatId);
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.USER_DICTIONARY.name())) {
            System.out.println("Это работает кнопка - " + CallbackDataPartsEnum.DICTIONARY_.name());
            return getDictionary(chatId, chatId);
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.ALL_GRADES.name())) {
            return getAllDefaultDictionaries(chatId);

        // Для новых инлайн клавиатур
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.ALL_GRADES_PDF.name())) {
            System.out.println("Это работает клавиатура Все классы PDF");
            return getAllDefaultDictionariesPdf(chatId);
        // Одиночного pdf файла
        }else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.TEMPLATE_NEW.name())) {
            System.out.println("Это работает кнопка - " + CallbackDataPartsEnum.TEMPLATE_NEW.name());
            SendMessage myResult = getTemplateNew(chatId);
            return myResult;
        } else {
            return handleDefaultDictionary(chatId, data);
        }
    }

    private SendMessage handleDefaultDictionary(String chatId, String data) throws IOException {
        if (data.startsWith(CallbackDataPartsEnum.TASK_.name())) {
            DictionaryResourcePathEnum resourcePath = DictionaryResourcePathEnum.valueOf(
                    data.substring(CallbackDataPartsEnum.TASK_.name().length())
            );
            return getDictionaryTasks(chatId, resourcePath.name(), resourcePath.getFileName());
        } else if (data.startsWith(CallbackDataPartsEnum.DICTIONARY_.name())) {
            return getDictionary(chatId, data.substring(CallbackDataPartsEnum.DICTIONARY_.name().length()));
        } else {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE.getMessage());
        }
    }

    private SendMessage getDictionaryTasks(String chatId, String dictionaryId, String fileName) throws IOException {
        try {
            telegramApiClient.uploadFile(chatId, taskService.getTasksDocument(dictionaryId, fileName));
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

    // Для одиночного pdf
    private SendMessage getTemplateNew(String chatId) {
        try {
            ByteArrayResource myResult = dictionaryResourceFileService.getTemplateWorkbookNew();
            telegramApiClient.uploadFileNew(chatId, myResult);
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
            // Var1
//            File file = new File("c:/books/sample.pdf");
//            FileInputStream fis = new FileInputStream(file);
//            byte [] data = new byte[(int)file.length()];
//            fis.read(data);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ByteArrayResource bar = new ByteArrayResource(bos.toByteArray());
//            data = bos.toByteArray();
//            telegramApiClient.uploadFile(chatId, bar);

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