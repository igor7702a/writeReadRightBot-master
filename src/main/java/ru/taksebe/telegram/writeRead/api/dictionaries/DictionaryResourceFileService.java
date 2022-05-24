package ru.taksebe.telegram.writeRead.api.dictionaries;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.utils.FileUtils;
import ru.taksebe.telegram.writeRead.utils.ResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DictionaryResourceFileService {
    private final ResourceLoader resourceLoader;

    public ByteArrayResource getTemplateWorkbook() throws IOException {
        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbook();
        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResource(
                myResourceLoader,
                "Template",
                "xlsx");
        return myResBAR;
    }

    // Для PPTX
    public ByteArrayResource getTemplateWorkbookPPTX() throws IOException {
        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbook();
        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResource(
                myResourceLoader,
                "TemplatePptx",
                "pptx");
        return myResBAR;
    }

    // Для одиночного файла Pdf
    // Было
    public ByteArrayResource getTemplateWorkbookNew() throws IOException {

        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbookNew();
        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResourceNew(
                myResourceLoader,
                "TemplatePdf", // Здесь должно быть имя реального файла
                "pdf");
        return myResBAR;
    }

    // Стало новый тип
    public ByteArrayResource getTemplateWorkbookNewType() throws IOException {

        PDDocument myResourceLoader = resourceLoader.loadTemplateWorkbookNewType();
        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResourceNewType(
                myResourceLoader,
                "TemplatePdf", // Здесь должно быть имя реального файла
                "pdf");
        return myResBAR;
    }

    // Стало новый тип Только PDF
    public ByteArrayResource getTemplateWorkbookOnlyPDF(
            String chatId,
            String token,
            String upPath,
            String fullPath,
            String file_name,
            String file_suffix,
            String file_id
    ) throws IOException {

        PDDocument myResourceLoader = resourceLoader.loadTemplateWorkbookOnlyPDF(
                chatId,
                token,
                upPath,
                fullPath,
                file_name,
                file_suffix,
                file_id
        );
        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResourceOnlyPDF(
                myResourceLoader,
                file_name, // Здесь должно быть имя реального файла для отображения в телеграм
                file_suffix,
                chatId,
                token,
                upPath,
                fullPath,
                file_id
                );
        return myResBAR;
    }

    public ByteArrayResource getTemplateWorkbookPdfExample() throws IOException {

        String fileName = "Template";
        String extentionName = "xlsx";

        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbook();

        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResource(
                myResourceLoader,
                fileName,
                extentionName);

        return myResBAR;
    }

    public ByteArrayResource getTemplateWorkbookPdf() throws IOException {

        String fileName = "TemplatePdf";
        String extentionName = "pdf";

        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbook();

        ByteArrayResource myResBAR = FileUtils.createOfficeDocumentResource(
                myResourceLoader,
                fileName,
                extentionName);

        //File file = new File("sample.pdf");
        File file = new File("c:\\Books\\files\\2021\\months\\11\\нацпроекты\\ТабКасИсп\\1.2_НП_касса_2020vs2021_220331_153802.pdf");
        FileInputStream fis = new FileInputStream(file);
        byte [] data = new byte[(int)file.length()];
        fis.read(data);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        data = bos.toByteArray();

        ByteArrayResource myResPdf = new ByteArrayResource(data);

        return myResPdf;
    }

    public ByteArrayResource getTemplateWorkbookPdfVar2() throws IOException {

        File file = new File("c:\\Books\\files\\2021\\months\\11\\нацпроекты\\ТабКасИсп\\1.2_НП_касса_2020vs2021_220331_153802.pdf");
        FileInputStream fis = new FileInputStream(file);
        byte [] data = new byte[(int)file.length()];
        fis.read(data);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        data = bos.toByteArray();

        ByteArrayResource myResPdf = new ByteArrayResource(data);

        return myResPdf;
    }
}