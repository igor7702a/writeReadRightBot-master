package ru.taksebe.telegram.writeRead.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.service.OSValidator;

import java.io.File;
import java.util.Scanner;

@Component
public class CreateFileStructureForNextYear {

    @Autowired
    OSValidator osValidator;

    public void CreateFolder(){

//        // Weeks
//        for (int i = 3; i<53; i++){
//
//            String str = "c:/Books/files/2022/weeks/";
//            StringBuilder sb = new StringBuilder(str);
//            sb.append(i);
//
//            String path = sb.toString();
//            File D = new File(path);
//            boolean D1 = D.mkdir();
//            if(D1){
//                System.out.println("Directory is created successfully");
//            }else{
//                System.out.println("Error !");
//            }
//
//        }

        // Months
        for (int i = 1; i<13; i++){

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
            sbPath.append("files/2021/months/");

            String str = sbPath.toString();
            StringBuilder sb = new StringBuilder(str);
            sb.append(i);

            String path = sb.toString();
            File D = new File(path);
            boolean D1 = D.mkdir();
            if(D1){
                System.out.println("Directory is created successfully");
            }else{
                System.out.println("Error !");
            }

        }

    }

}
