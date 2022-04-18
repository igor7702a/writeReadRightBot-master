package ru.taksebe.telegram.writeRead.telegram.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.taksebe.telegram.writeRead.constants.bot.CallbackDataPartsEnum;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnum;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnumPDF;

import java.util.ArrayList;
import java.util.List;

/**
 * Клавиатуры, формируемые в ленте Telegram для получения файлов
 */
@Component
public class InlineKeyboardMakerPdf {

    public InlineKeyboardMarkup getInlineMessageButtonsWithTemplate(String prefix, boolean isUserDictionaryNeed) {
        InlineKeyboardMarkup inlineKeyboardMarkup = getInlineMessageButtons(prefix, isUserDictionaryNeed);
        inlineKeyboardMarkup.getKeyboard().add(getButton(
                "Шаблон Pdf",
                prefix + CallbackDataPartsEnum.TEMPLATE_NEW.name()
        ));
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtons(String prefix, boolean isUserDictionaryNeed) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (DictionaryResourcePathEnumPDF dictionary : DictionaryResourcePathEnumPDF.values()) {
            String myDictionaryButtonName = dictionary.getButtonName();
            String myDictionaryName = dictionary.name();

            List<InlineKeyboardButton> myGetButton = getButton(myDictionaryButtonName,prefix
                    + myDictionaryName);

            rowList.add(myGetButton);
        }

        if (!rowList.isEmpty()) {
            rowList.add(getButton(
                    "Все файлы Pdf",
                    prefix + CallbackDataPartsEnum.ALL_GRADES_PDF.name()
            ));
        }

        if (isUserDictionaryNeed) {
            rowList.add(getButton(
                    "Ваш файл Pdf",
                    prefix + CallbackDataPartsEnum.USER_DICTIONARY.name()
            ));
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButton(String buttonName, String buttonCallBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setCallbackData(buttonCallBackData);

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }
}