package ru.taksebe.telegram.writeRead.api.dictionaries;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
        ByteArrayResource myResult = FileUtils.createOfficeDocumentResource(
                myResourceLoader,
                "Template",
                "xlsx");
        return myResult;
    }

    // Для одиночного файла Pdf
    // Было
    public ByteArrayResource getTemplateWorkbookNew() throws IOException {
        // XSSFWorkbook - надо заменить на тип для Pdf
        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbookNew();
        ByteArrayResource myResult = FileUtils.createOfficeDocumentResourceNew(
                myResourceLoader,
                "TemplatePdf", // Здесь должно быть имя реального файла
                "pdf");
        return myResult;
    }

    public ByteArrayResource getTemplateWorkbookPdfExample() throws IOException {

        String fileName = "Template";
        String extentionName = "xlsx";

        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbook();

        ByteArrayResource myResult = FileUtils.createOfficeDocumentResource(
                myResourceLoader,
                fileName,
                extentionName);

        return myResult;
    }

    public ByteArrayResource getTemplateWorkbookPdf() throws IOException {

        String fileName = "TemplatePdf";
        String extentionName = "pdf";

        XSSFWorkbook myResourceLoader = resourceLoader.loadTemplateWorkbook();

        ByteArrayResource myResult = FileUtils.createOfficeDocumentResource(
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

        ByteArrayResource myResultPdf = new ByteArrayResource(data);

        return myResultPdf;
    }

    public ByteArrayResource getTemplateWorkbookPdfVar2() throws IOException {

        File file = new File("c:\\Books\\files\\2021\\months\\11\\нацпроекты\\ТабКасИсп\\1.2_НП_касса_2020vs2021_220331_153802.pdf");
        FileInputStream fis = new FileInputStream(file);
        byte [] data = new byte[(int)file.length()];
        fis.read(data);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        data = bos.toByteArray();

        ByteArrayResource myResultPdf = new ByteArrayResource(data);

        return myResultPdf;
    }
}

//    public static void main(String args[]) throws Exception {
//        File file = new File("sample.pdf");
//        FileInputStream fis = new FileInputStream(file);
//        byte [] data = new byte[(int)file.length()];
//        fis.read(data);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        data = bos.toByteArray();
//    }
//}