package ru.taksebe.telegram.writeRead.constants.bot;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
    GET_TASKS_BUTTON("Создать файл с заданиями"),
    //GET_TASKS_BUTTON("Тест №1"),

    GET_DICTIONARY_BUTTON("Скачать словарь"),
    //GET_DICTIONARY_BUTTON("Тест №2"),

    UPLOAD_DICTIONARY_BUTTON("Загрузить мой словарь"),
    //UPLOAD_DICTIONARY_BUTTON("Тест №3"),

    HELP_BUTTON("Помощь"),

    //Новые кнопки для рассылки материалов
    UPLOAD_MATERIALS_BUTTON("Тест рассылки материалов"),
    UPLOAD_MATERIALS_WITH_FILES_BUTTON("Тест рассылки материалов c файлами"),
    UPLOAD_FILES_BUTTON("Тест рассылки файлов");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}