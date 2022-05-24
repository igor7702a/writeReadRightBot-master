package ru.taksebe.telegram.writeRead.constants.bot;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
    //GET_TASKS_BUTTON("Создать файл с заданиями"),
    GET_TASKS_BUTTON("Тест №1"),

    //GET_DICTIONARY_BUTTON("Скачать словарь"),
    GET_DICTIONARY_BUTTON("Тест №2"),

    //UPLOAD_DICTIONARY_BUTTON("Загрузить мой словарь"),
    UPLOAD_DICTIONARY_BUTTON("Тест №3"),

    HELP_BUTTON("Помощь"),

    //Новые кнопки для рассылки материалов
    UPLOAD_MATERIALS_BUTTON("Тест рассылки материалов"),
    UPLOAD_MATERIALS_WITH_FILES_BUTTON("Все рубрики"),
    UPLOAD_MATERIALS_WITH_FILES_NATIONAL_PROJECTS("1. НацПроекты (1.1, 1.2, 1.3)"),
    UPLOAD_MATERIALS_WITH_FILES_RISKS("2. Риски (2.1, 2.2, 2.3, 2.4)"),
    UPLOAD_MATERIALS_WITH_FILES_NATIONAL_GOALS("3. НацЦели (3.1, 3.2, 3.3, 3.4)"),
    UPLOAD_MATERIALS_WITH_FILES_INSTITUT_DEV("4. ИнстРазвития (4.1)"),
    UPLOAD_FILES_BUTTON("Тест рассылки файлов");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}