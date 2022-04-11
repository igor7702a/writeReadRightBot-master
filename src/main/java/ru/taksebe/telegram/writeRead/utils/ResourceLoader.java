package ru.taksebe.telegram.writeRead.utils;

import lombok.Getter;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnum;
import ru.taksebe.telegram.writeRead.constants.resources.TemplateResourcePathsEnum;

import java.io.IOException;
import java.io.InputStream;
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

    // Для работы с одиночным файлом pdf
    // Заменить на тип файла для Pdf
    // Было
    public XSSFWorkbook loadTemplateWorkbookNew() throws IOException {
        String myTemplateDictionary = TemplateResourcePathsEnum.TEMPLATE_PDF.getFilePath();
        XSSFWorkbook myResult = loadWorkbookNew(myTemplateDictionary);
        return myResult;
    }

    // Стало новый тип
    public byte[] loadTemplateWorkbookNewType() throws IOException {
        String myTemplateDictionary = TemplateResourcePathsEnum.TEMPLATE_PDF.getFilePath();
        byte[] myResult = loadWorkbookNewType(myTemplateDictionary);
        return myResult;
    }

    // Для работы с файлами pdf
    public XSSFWorkbook loadTemplateWorkbookPdf() throws IOException {
        String param1 = TemplateResourcePathsEnum.TEMPLATE_PDF.getFilePath();
        XSSFWorkbook myResult = loadWorkbookPdf(param1);
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

    // Для работы с одиночным файлом pdf
    // Было
    private XSSFWorkbook loadWorkbookNew(String filePath) throws IOException {
        InputStream param1 = Objects.requireNonNull(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream(filePath)
        );

        XSSFWorkbook myResult = new XSSFWorkbook(param1);
        return myResult;
    }

    // Стало
    private byte[] loadWorkbookNewType(String filePath) throws IOException {
        InputStream myInputStream = Objects.requireNonNull(
                getClass()
                        .getClassLoader()
                        .getResourceAsStream(filePath)
        );
        byte[] arrayByte = IOUtils.toByteArray(myInputStream);
        return arrayByte;
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