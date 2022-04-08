package ru.taksebe.telegram.writeRead.constants.resources;

import lombok.Getter;

/**
 * Расположение файлов словарей по умолчанию в resources
 */
public enum DictionaryPdfResourcePathEnum {
    PDF_1("dictionariesPdf/1 test.pdf", "1 testpdf");

    private final String filePath;
    @Getter
    private final String buttonName;

    DictionaryPdfResourcePathEnum(String filePath, String buttonName) {
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