package ru.taksebe.telegram.writeRead.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ooxml.POIXMLDocument;
import org.springframework.core.io.ByteArrayResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;

public class FileUtils {

    private FileUtils() {
    }

    public static ByteArrayResource createOfficeDocumentResource(POIXMLDocument document, String name, String suffix)
            throws IOException {

        Path paramreadAllBytes = createOfficeDocumentFile(document, name, suffix);

        byte[] param1 = Files.readAllBytes(paramreadAllBytes);

        ByteArrayResource myResBAR = new ByteArrayResource(param1) {

            @Override
            public String getFilename() {
                return MessageFormat.format("{0}.{1}", name, suffix);
            }
        };

        return myResBAR;
    }

    // Было
    public static ByteArrayResource createOfficeDocumentResourceNew(POIXMLDocument document, String name, String suffix)
            throws IOException {

        Path paramreadAllBytes = createOfficeDocumentFile(document, name, suffix);

        byte[] param1 = Files.readAllBytes(paramreadAllBytes);

        ByteArrayResource myResBAR = new ByteArrayResource(param1) {

            @Override
            public String getFilename() {
                return MessageFormat.format("{0}.{1}", name, suffix);
            }
        };

        return myResBAR;
    }

    // Стало
    public static ByteArrayResource createOfficeDocumentResourceNewType(PDDocument document, String name, String suffix)
            throws IOException {

        Path paramreadAllBytes = createOfficeDocumentFileNewType(document, name, suffix);
        byte[] param1 = Files.readAllBytes(paramreadAllBytes);
        ByteArrayResource myResBAR = new ByteArrayResource(param1) {
            @Override
            public String getFilename() {
                return MessageFormat.format("{0}.{1}", name, suffix);
            }
        };
        return myResBAR;
    }

    // Только PDF
    public static ByteArrayResource createOfficeDocumentResourceOnlyPDF(
            PDDocument document,
            String name,
            String suffix
            ,String chatId,
            String token,
            String upPath,
            String fullPath,
            String file_id
    )
            throws IOException {
        Path paramreadAllBytes = createOfficeDocumentFileOnlyPDF(document, name, suffix);
        byte[] param1 = Files.readAllBytes(paramreadAllBytes);
        ByteArrayResource myResBAR = new ByteArrayResource(param1) {
            @Override
            public String getFilename() {
                return MessageFormat.format("{0}.{1}", name, suffix);
            }
        };
        return myResBAR;
    }

    private static Path createOfficeDocumentFile(POIXMLDocument document, String name, String suffix) throws IOException {
        File file = File.createTempFile(name, suffix);
        try (FileOutputStream out = new FileOutputStream(file)) {
            document.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toPath();
    }

    // Для работы с одиночным файлом pdf
    // Было
    private static Path createOfficeDocumentFileNew(POIXMLDocument document, String name, String suffix) throws IOException {
        File file = File.createTempFile(name, suffix);
        try (FileOutputStream out = new FileOutputStream(file)) {
            document.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toPath();
    }

    private static Path createOfficeDocumentFileNewType(PDDocument document, String name, String suffix) throws IOException {
        File file = File.createTempFile(name, suffix);
        try (FileOutputStream out = new FileOutputStream(file)) {
            document.save(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toPath();
    }

    // Только для PDF
    private static Path createOfficeDocumentFileOnlyPDF(PDDocument document, String name, String suffix) throws IOException {
        File file = File.createTempFile(name, suffix);
        try (FileOutputStream out = new FileOutputStream(file)) {
            document.save(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toPath();
    }

    // Для работы с файлами pdf
    private static Path createOfficeDocumentFilePdf(POIXMLDocument document, String name, String suffix) throws IOException {
        File file = File.createTempFile(name, suffix);
        try (FileOutputStream out = new FileOutputStream(file)) {
            document.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.toPath();
    }

    // Для работы с файлами pdf
    public static ByteArrayResource createOfficeDocumentResourcePdf(POIXMLDocument document, String name, String suffix)
            throws IOException {

        Path paramreadAllBytes = createOfficeDocumentFilePdf(document, name, suffix);

        byte[] param1 = Files.readAllBytes(paramreadAllBytes);

        ByteArrayResource myResBAR = new ByteArrayResource(param1) {

            @Override
            public String getFilename() {
                return MessageFormat.format("{0}.{1}", name, suffix);
            }
        };

        return myResBAR;
    }
}