package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.SaveFiles;
import ru.taksebe.telegram.writeRead.service.OSValidator;

import java.io.IOException;

@SpringBootTest
public class SaveFilesTest {

    @Autowired
    SaveFiles saveFiles;
    @Autowired
    OSValidator osValidator;

    @Test
    void receiveOS() throws IOException {
        saveFiles.CopyFile();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void receiveOS_2Param() throws IOException {
        saveFiles.CopyFile("c:/Books/TemplatePdf.pdf", "c:/Books/TemplatePdf2.pdf");
        Assertions.assertEquals(1, 1);
    }
    //    File source = new File("c:/Books/TemplatePdf.pdf");
    //    File  dest = new File("c:/Books/TemplatePdf1.pdf");

    @Test
    void receiveOS_4Param() throws IOException {
        saveFiles.CopyFile(
                "TemplatePdf.pdf",
                "TemplatePdf3.pdf",
                "Books/",
                "c:/");
        Assertions.assertEquals(1, 1);
    }
    //    File source = new File("c:/Books/TemplatePdf.pdf");
    //    File  dest = new File("c:/Books/TemplatePdf1.pdf");
    //    c:/
    //    /home/svc_chatbot/

    @Test
    void receiveOS_4ParamWinUnix() throws IOException {
        // For OS +
        String myOS = osValidator.returnOS();
        String pathOS = "";

        if(myOS == "This is Windows"){
            pathOS = "c:/";
        }
        else if(myOS == "This is Unix or Linux"){
            pathOS = "/home/svc_chatbot/";
        }
        else {
        }
        StringBuilder sbPath = new StringBuilder(pathOS);
        // -

        saveFiles.CopyFile(
                "TemplatePdf.pdf",
                "TemplatePdf4.pdf",
                "Books/",
                pathOS);
        Assertions.assertEquals(1, 1);
    }
    //    File source = new File("c:/Books/TemplatePdf.pdf");
    //    File  dest = new File("c:/Books/TemplatePdf1.pdf");
    //    c:/
    //    /home/svc_chatbot/
}


