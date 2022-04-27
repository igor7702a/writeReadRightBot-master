package ru.taksebe.telegram.writeRead.utils;

import lombok.Getter;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.io.ScratchFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.poifs.crypt.dsig.SignaturePart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.constants.resources.DictionaryResourcePathEnum;
import ru.taksebe.telegram.writeRead.constants.resources.TemplateResourcePathsEnum;
import ru.taksebe.telegram.writeRead.service.OSValidator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Загрузчик шаблонов документов из resources
 */
@Component
public class ResourceLoader {

    @Autowired
    OSValidator osValidator;

    @Getter
    private final Map<String, XSSFWorkbook> defaultDictionaries;
    private SignaturePart signatureObjPAdES;

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
    public PDDocument loadTemplateWorkbookNewType() throws IOException {
        String myTemplateDictionary = TemplateResourcePathsEnum.TEMPLATE_PDF.getFilePath();
        PDDocument myResult = loadWorkbookNewType(myTemplateDictionary);
        return myResult;
    }

    // Только для PDF
    public PDDocument loadTemplateWorkbookOnlyPDF(
        String chatId,
        String token,
        String upPath,
        String fullPath,
        String file_name,
        String file_suffix,
        String file_id

    ) throws IOException {
        //String myTemplateDictionary = TemplateResourcePathsEnum.TEMPLATE_PDF.getFilePath();
        String myTemplateDictionary = fullPath;
                PDDocument myResult = loadWorkbookOnlyPDF(
                myTemplateDictionary
                    ,chatId,
                    token,
                    upPath,
                    fullPath,
                    file_name,
                    file_suffix,
                    file_id
        );
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
    private PDDocument loadWorkbookNewType(String filePath) throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(TemplateResourcePathsEnum.TEMPLATE_PDF.getFilePath());

        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/Books/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/Books/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);
        // -

        OutputStream outputStream = new FileOutputStream(new File(sbPath.toString() + "temp.pdf"));

        byte[] buffer = new byte[1000];
        while (inputStream.available() > 0) //пока есть еще непрочитанные байты
        {
            // прочитать очередной блок байт в переменную buffer и реальное количество в count
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count); //записать блок(часть блока) во второй поток
        }

        inputStream.close(); //закрываем оба потока. Они больше не нужны.
        outputStream.close();

        PDDocument myResult = PDDocument.load(new File(sbPath.toString() + "temp.pdf"));

        return myResult;
    }

    // Только для PDF
    private PDDocument loadWorkbookOnlyPDF(
            String filePath
            ,String chatId,
            String token,
            String upPath,
            String fullPath,
            String file_name,
            String file_suffix,
            String file_id
    ) throws IOException {

        File initialFile = new File(fullPath);
        InputStream inputStream = new FileInputStream(initialFile);

        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/Books/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/Books/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);
        // -

        OutputStream outputStream = new FileOutputStream(new File(sbPath.toString() + "temp.pdf"));

        byte[] buffer = new byte[1000];
        while (inputStream.available() > 0) //пока есть еще непрочитанные байты
        {
            // прочитать очередной блок байт в переменную buffer и реальное количество в count
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count); //записать блок(часть блока) во второй поток
        }

        inputStream.close(); //закрываем оба потока. Они больше не нужны.
        outputStream.close();

        PDDocument myResult = PDDocument.load(new File(sbPath.toString() + "temp.pdf"));

        return myResult;
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