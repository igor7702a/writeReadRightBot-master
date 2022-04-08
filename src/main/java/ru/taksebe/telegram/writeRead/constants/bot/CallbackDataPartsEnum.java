package ru.taksebe.telegram.writeRead.constants.bot;

/**
 * Элементы ответов кнопок инлайн-клавиатур
 */
public enum CallbackDataPartsEnum {
    TASK_,
    DICTIONARY_,
    USER_DICTIONARY,
    TEMPLATE,
    TEMPLATE_NEW, // Для одиночного файла Pdf
    ALL_GRADES,
    ALL_GRADES_PDF // Для множественного файла Pdf
}