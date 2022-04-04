package ru.taksebe.telegram.writeRead.constants.bot;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
    GET_TASKS_BUTTON("Создать файл с заданиями"),
    //GET_TASKS_BUTTON("Переслать материалы пользователям"),

    GET_DICTIONARY_BUTTON("Скачать словарь"),
    //GET_DICTIONARY_BUTTON("Скачать настройки рассылки файлов"),

    UPLOAD_DICTIONARY_BUTTON("Загрузить мой словарь"),

    HELP_BUTTON("Помощь"),

    //Новые кнопки для рассылки материалов
    UPLOAD_MATERIALS_BUTTON("Загрузить настройки рассылки файлов"); // две подкнопки: Настройки рассылки и настройки наименования файлов



    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}