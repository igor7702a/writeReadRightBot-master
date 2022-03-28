package ru.taksebe.telegram.writeRead.service;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class CreateFileStructureForNextYear {

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

            String str = "c:/Books/files/2021/months/";
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
