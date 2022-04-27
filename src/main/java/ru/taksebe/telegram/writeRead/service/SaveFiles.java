package ru.taksebe.telegram.writeRead.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

@Component
public class SaveFiles {

    public void CopyFile() throws IOException {
        File source = new File("c:/Books/TemplatePdf.pdf");
        File  dest = new File("c:/Books/TemplatePdf1.pdf");
        copyFileUsingApacheCommonsIO(source, dest);
    }

    public void CopyFile(String fileSourceName, String fileDestname) throws IOException {
        File source = new File(fileSourceName);
        File  dest = new File(fileDestname);
        copyFileUsingApacheCommonsIO(source, dest);
    }

    public void CopyFile(String fileSourceName, String fileDestName, String pathFile, String pathOS) throws IOException {
        StringBuilder fullPathSource = new StringBuilder(pathOS + pathFile + fileSourceName);
        StringBuilder fullPathDest = new StringBuilder(pathOS + pathFile + fileDestName);
        File source = new File(fullPathSource.toString());
        File  dest = new File(fullPathDest.toString());
        copyFileUsingApacheCommonsIO(source, dest);
    }

    private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }
}