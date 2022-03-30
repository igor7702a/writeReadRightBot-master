package ru.taksebe.telegram.writeRead.service;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class DOCXDownloadLetterService {

    public void docxDownLoadEmptyLetter() throws IOException {
        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream( new File("c:/books/letters/createdocument.docx"));
        document.write(out);
        out.close();
        System.out.println("createdocument.docx written successully");
    }

    public void docxDownLoadParagrafLetter() throws IOException {
        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("c:/books/letters/createparagraph.docx"));

        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("At tutorialspoint.com, we strive hard to " +
                "provide quality tutorials for self-learning " +
                "purpose in the domains of Academics, Information " +
                "Technology, Management and Computer Programming Languages.");

        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph.createRun();
        run2.addCarriageReturn();
        run2.setText("new");

        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
    }

    public void docxDownLoadRealLetter() throws IOException {
        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("c:/books/letters/realletter.docx"));


        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("At tutorialspoint.com, we strive hard to " +
                "provide quality tutorials for self-learning " +
                "purpose in the domains of Academics, Information " +
                "Technology, Management and Computer Programming Languages.");

        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph.createRun();
        run2.addCarriageReturn();
        run2.setText("new");

        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
    }

}

//1+    itemName String
//2+    dateItemName LocalDate (String)
//3+    armName String
//4+    armLink String
//5+    officerFor String
//6+    rubricNumber int
//7+    rubricName String
//8+    bookNumber int
//9+    bookName String
//10+    fileName String
//11+    monthNumber int
//12+    timetable String
//13+    typeRecipient String
//14+    nameRecipient String
//15+    fioUpload String
//16+    datetimeUpload LocalDateTime
//17+    systemRubricName String
//18+    systemFileName String

//    Id SERIAL PRIMARY KEY,
//+    item_name VARCHAR(200),
//+    date_item_name DATE,
//+    arm_name VARCHAR(200),
//+    arm_link VARCHAR(200),
//+    officer_for VARCHAR(200),
//+    rubric_number INTEGER,
//+    rubric_name VARCHAR(150),
//+    book_number INTEGER,
//+    book_name VARCHAR(200),
//+    file_name VARCHAR(200),
//+    month_number INTEGER,
//+    timetable VARCHAR(100),
//+    type_recipient VARCHAR(100),
//+    name_recipient VARCHAR(200),
//+    fio_upload VARCHAR(200),
//+    datetime_upload TIMESTAMP,
//+    system_rubric_name VARCHAR(100),
//+    system_file_name VARCHAR(100)

//    itemName = "";
//    dateItemName = LocalDate.now();
//    armName = "";
//    armLink = "";
//    officerFor = "";
//    rubricNumber = 0;
//    rubricName = "";
//    bookNumber = 0;
//    bookName = "";
//    fileName = "";
//    monthNumber = 0;
//    timetable = "";
//    typeRecipient = "";
//    nameRecipient = "";
//    fioUpload = "";
//    datetimeUpload = LocalDateTime.now();
//    systemRubricName = "";
//    systemFileName = "";

//    itemName
//    dateItemName
//    armName
//    armLink
//    officerFor
//    rubricNumber
//    rubricName
//    bookNumber
//    bookName
//    fileName
//    monthNumber
//    timetable
//    typeRecipient
//    nameRecipient
//    fioUpload
//    datetimeUpload
//    systemRubricName
//    systemFileName


