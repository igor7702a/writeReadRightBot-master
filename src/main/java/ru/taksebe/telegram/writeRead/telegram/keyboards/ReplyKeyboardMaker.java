package ru.taksebe.telegram.writeRead.telegram.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.taksebe.telegram.writeRead.constants.bot.ButtonNameEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Основная клавиатура, расположенная под строкой ввода текста в Telegram
 */
@Component
public class ReplyKeyboardMaker {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        //row1.add(new KeyboardButton(ButtonNameEnum.GET_TASKS_BUTTON.getButtonName()));
        //row1.add(new KeyboardButton(ButtonNameEnum.GET_DICTIONARY_BUTTON.getButtonName()));
        row1.add(new KeyboardButton(ButtonNameEnum.HELP_BUTTON.getButtonName()));

        KeyboardRow row2 = new KeyboardRow();
        //row2.add(new KeyboardButton(ButtonNameEnum.UPLOAD_DICTIONARY_BUTTON.getButtonName()));
        //row2.add(new KeyboardButton(ButtonNameEnum.HELP_BUTTON.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnum.UPLOAD_MATERIALS_WITH_FILES_NATIONAL_PROJECTS.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnum.UPLOAD_MATERIALS_WITH_FILES_RISKS.getButtonName()));
        row2.add(new KeyboardButton(ButtonNameEnum.UPLOAD_MATERIALS_WITH_FILES_NATIONAL_GOALS.getButtonName()));

        KeyboardRow row3 = new KeyboardRow();
        //row3.add(new KeyboardButton(ButtonNameEnum.UPLOAD_MATERIALS_BUTTON.getButtonName()));
        //row3.add(new KeyboardButton(ButtonNameEnum.UPLOAD_MATERIALS_WITH_FILES_BUTTON.getButtonName()));
        row3.add(new KeyboardButton(ButtonNameEnum.UPLOAD_MATERIALS_WITH_FILES_INSTITUT_DEV.getButtonName()));
        //row3.add(new KeyboardButton(ButtonNameEnum.UPLOAD_FILES_BUTTON.getButtonName()));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;
    }
}