package ru.taksebe.telegram.writeRead.constants.resources;

import lombok.Getter;

/**
 * Расположение файлов словарей по умолчанию в resources
 */
public enum DictionaryResourcePathEnumPDF {
    TYPE_1("dictionaries/разделитель1 gradeразделитель.xlsx", "1 тип"),
    TYPE_2("dictionaries/разделитель2 gradeразделитель.xlsx", "2 тип"),
    TYPE_3("dictionaries/разделитель3 gradeразделитель.xlsx", "3 тип"),
    TYPE_4("dictionaries/разделитель4 gradeразделитель.xlsx", "4 тип");

    private final String filePath;
    @Getter
    private final String buttonName;

    DictionaryResourcePathEnumPDF(String filePath, String buttonName) {
        this.filePath = filePath;
        this.buttonName = buttonName;
    }

    public String getFilePath() {
        return filePath.replace("разделитель", "");
    }

    public String getFileName() {
        return filePath.split("разделитель")[1];
    }
}