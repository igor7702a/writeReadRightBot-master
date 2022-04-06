package ru.taksebe.telegram.writeRead.utils;

import lombok.Getter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnum;
import ru.taksebe.telegram.writeRead.constants.resources.TemplateResourcePathsEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Загрузчик шаблонов документов из resources
 */
@Component
public class ResourceLoader {
    @Getter
    private final Map<String, XSSFWorkbook> defaultDictionaries;

    public ResourceLoader() throws IOException {
        this.defaultDictionaries = loadAllDefaultDictionaryWorkbooks();
    }

    public XWPFDocument loadTemplateDocument() throws IOException {
        return new XWPFDocument(
                Objects.requireNonNull(
                        getClass()
                                .getClassLoader()
                                .getResourceAsStream(TemplateResourcePathsEnum.TEMPLATE_TASKS.getFilePath())
                )
        );
    }

    public XSSFWorkbook loadTemplateWorkbook() throws IOException {
        XSSFWorkbook myResult = loadWorkbook(TemplateResourcePathsEnum.TEMPLATE_DICTIONARY.getFilePath());
        return myResult;
    }

    // Для работы с файлами pdf
    public XSSFWorkbook loadTemplateWorkbookPdf() throws IOException {
        XSSFWorkbook myResult = loadWorkbookPdf(TemplateResourcePathsEnum.TEMPLATE_DICTIONARY.getFilePath());
        return myResult;
    }

    public XSSFWorkbook loadDefaultDictionaryWorkbook(DictionaryResourcePathEnum dictionaryResourcePath) throws IOException {
        return loadWorkbook(dictionaryResourcePath.getFilePath());
    }

    private Map<String, XSSFWorkbook> loadAllDefaultDictionaryWorkbooks() throws IOException {
        Map<String, XSSFWorkbook> defaultDictionaries = new HashMap<>();
        for (DictionaryResourcePathEnum path : DictionaryResourcePathEnum.values()) {
            defaultDictionaries.put(path.name(), loadWorkbook(path.getFilePath()));
        }
        return defaultDictionaries;
    }

    private XSSFWorkbook loadWorkbook(String filePath) throws IOException {
        return new XSSFWorkbook(
                Objects.requireNonNull(
                        getClass()
                                .getClassLoader()
                                .getResourceAsStream(filePath)
                )
        );
    }

    // Для работы с pdf
    private XSSFWorkbook loadWorkbookPdf(String filePath) throws IOException {
        return new XSSFWorkbook(
                Objects.requireNonNull(
                        getClass()
                                .getClassLoader()
                                .getResourceAsStream(filePath)
                )
        );
    }
}

//import java.io.File;
//        import java.io.FileInputStream;
//        import java.io.ByteArrayOutputStream;
//public class PdfToByteArray {
//    public static void main(String args[]) throws Exception {
//        File file = new File("sample.pdf");
//        FileInputStream fis = new FileInputStream(file);
//        byte [] data = new byte[(int)file.length()];
//        fis.read(data);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        data = bos.toByteArray();
//    }
//}