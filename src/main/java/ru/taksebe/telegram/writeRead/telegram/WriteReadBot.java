package ru.taksebe.telegram.writeRead.telegram;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.taksebe.telegram.writeRead.constants.bot.BotMessageEnum;
import ru.taksebe.telegram.writeRead.exceptions.NoRightTemplateNewButton;
import ru.taksebe.telegram.writeRead.exceptions.NoRightUploadFilesButton;
import ru.taksebe.telegram.writeRead.exceptions.NoRightUploadMaterialsButton;
import ru.taksebe.telegram.writeRead.exceptions.NoRightUploadMaterialsWithFilesButton;
import ru.taksebe.telegram.writeRead.telegram.handlers.CallbackQueryHandler;
import ru.taksebe.telegram.writeRead.telegram.handlers.MessageHandler;

import java.io.IOException;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteReadBot extends SpringWebhookBot {
    String botPath;
    String botUsername;
    String botToken;

    MessageHandler messageHandler;
    CallbackQueryHandler callbackQueryHandler;

    public WriteReadBot(SetWebhook setWebhook, MessageHandler messageHandler,CallbackQueryHandler callbackQueryHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            BotApiMethod<?> myResBAM = handleUpdate(update);
            return myResBAM;
        } catch (IllegalArgumentException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_ILLEGAL_MESSAGE.getMessage());
        } catch (NoRightUploadMaterialsButton e) {
            return new SendMessage(update.getMessage().getChatId().toString(), BotMessageEnum.EXCEPTION_NOT_RIGHTS_UPLOAD_MATERIALS_BUTTON.getMessage());
        } catch (NoRightUploadMaterialsWithFilesButton e) {
            return new SendMessage(update.getMessage().getChatId().toString(), BotMessageEnum.EXCEPTION_NOT_RIGHTS_UPLOAD_MATERIALS_WITH_FILES_BUTTON.getMessage());
        } catch (NoRightUploadFilesButton e) {
            return new SendMessage(update.getMessage().getChatId().toString(), BotMessageEnum.EXCEPTION_NOT_RIGHTS_UPLOAD_FILES_BUTTON.getMessage());
        } catch (NoRightTemplateNewButton e) {
            // Исключение инлайн кнопки
            return new SendMessage(update.getCallbackQuery().getMessage().getChatId().toString(), BotMessageEnum.EXCEPTION_NOT_RIGHTS_TEMPLATE_NEW_BUTTON.getMessage());
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_WHAT_THE_FUCK.getMessage());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws IOException {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else {
            Message message = update.getMessage();
            if (message != null) {
                BotApiMethod<?> myResBAM = messageHandler.answerMessage(update.getMessage());
                return myResBAM;
            }
        }
        return null;
    }
}