package ru.taksebe.telegram.writeRead.telegram.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryExcelService;
import ru.taksebe.telegram.writeRead.api.dictionaries.DictionaryResourceFileService;
import ru.taksebe.telegram.writeRead.api.tasks.TaskService;
import ru.taksebe.telegram.writeRead.constants.bot.BotMessageEnum;
import ru.taksebe.telegram.writeRead.constants.bot.CallbackDataPartsEnum;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnum;
import ru.taksebe.telegram.writeRead.entity.UsersProfilesEntity;
import ru.taksebe.telegram.writeRead.exceptions.NoRightTemplateNewButton;
import ru.taksebe.telegram.writeRead.exceptions.NoRightUploadMaterialsWithFilesButton;
import ru.taksebe.telegram.writeRead.exceptions.UserDictionaryNotFoundException;
import ru.taksebe.telegram.writeRead.telegram.TelegramApiClient;
import ru.taksebe.telegram.writeRead.repository.UsersProfilesCrudRepository;
import ru.taksebe.telegram.writeRead.entity.UsersProfilesEntity;

// Для работы с pdf
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.List;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CallbackQueryHandler {
    @Autowired
    UsersProfilesCrudRepository usersProfilesCrudRepository;

    TelegramApiClient telegramApiClient;
    TaskService taskService;
    DictionaryExcelService dictionaryExcelService;
    DictionaryResourceFileService dictionaryResourceFileService;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {
        final String chatId = buttonQuery.getMessage().getChatId().toString();
        final String tgUser = buttonQuery.getFrom().getUserName();

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
            // Здесь проверить право на нажатие этой кнопки
            List<UsersProfilesEntity> result = usersProfilesCrudRepository.findAllFromUsersProfilesBy2Param(
                    "TEMPLATE_NEW",tgUser);
            result.forEach(it3-> System.out.println(it3));
            System.out.println("result.size = " + result.size());
            int resultExists = result.size();
            if(resultExists == 0){
                throw new NoRightTemplateNewButton();
            }

            System.out.println("Это работает кнопка - " + CallbackDataPartsEnum.TEMPLATE_NEW.name());
            SendMessage myResSm = getTemplateNew(chatId);
            return myResSm;
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
            ByteArrayResource myResBAR = dictionaryExcelService.getAllDefaultDictionariesWorkbook();
            telegramApiClient.uploadFile(chatId, myResBAR);
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
            ByteArrayResource myResBAR = dictionaryResourceFileService.getTemplateWorkbookNewType();
            telegramApiClient.uploadFileNewType(chatId, myResBAR);
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }
        return null;
    }
    // Для одиночного pdf - проверка пересылки с клавиатуры + инлайн
    private SendMessage getTemplateNew(String chatId) {
        try {
            ByteArrayResource myResBAR = dictionaryResourceFileService.getTemplateWorkbookNewType();
            telegramApiClient.uploadFileNewType(chatId, myResBAR);
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
            ByteArrayResource myResBAR = dictionaryResourceFileService.getTemplateWorkbookOnlyPDF(
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
                    myResBAR,
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
            ByteArrayResource myResBAR = dictionaryExcelService.getAllDefaultDictionariesWorkbookPdf();
            telegramApiClient.uploadFile(chatId, myResBAR);

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