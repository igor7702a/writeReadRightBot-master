package ru.taksebe.telegram.writeRead.service;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
public class CreateFileStructureForNextYear {

    public void CreateFolder(){
        String path = "c:/Books/files/2022/weeks/1";
        File D = new File(path);
        boolean D1 = D.mkdir();
        if(D1){
            System.out.println("Directory is created successfully");
        }else{
            System.out.println("Error !");
        }

//        System.out.println("Path of Directory? ");
//        Scanner obj = new Scanner(System.in);
//        String path = obj.next();
//        System.out.println("Directory Name? ");
//        path = path+obj.next();
//        File D = new File(path);
//        boolean D1 = D.mkdir();
//        if(D1){
//            System.out.println("Directory is created successfully");
//        }else{
//            System.out.println("Error !");
//        }
    }

}
