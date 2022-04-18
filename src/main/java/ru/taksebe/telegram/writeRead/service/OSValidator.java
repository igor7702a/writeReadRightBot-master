package ru.taksebe.telegram.writeRead.service;

import org.springframework.stereotype.Component;

@Component
public class OSValidator {

    public static String OS = System.getProperty("os.name").toLowerCase();

    public void receiveOS(){
        System.out.println("os.name: " + OS);

        if (isWindows()) {
            System.out.println("This is Windows");
        } else if (isMac()) {
            System.out.println("This is Mac");
        } else if (isUnix()) {
            System.out.println("This is Unix or Linux");
        } else if (isSolaris()) {
            System.out.println("This is Solaris");
        } else {
            System.out.println("Your OS is not support!!");
        }
    }

    public String returnOS(){

        System.out.println("os.name: " + OS);
        String myOS = "";

        if (isWindows()) {
            System.out.println("This is Windows");
            myOS = "This is Windows";
            return myOS;
        } else if (isMac()) {
            System.out.println("This is Mac");
            myOS = "This is Mac";
            return myOS;
        } else if (isUnix()) {
            System.out.println("This is Unix or Linux");
            myOS = "This is Unix or Linux";
            return myOS;
        } else if (isSolaris()) {
            System.out.println("This is Solaris");
            myOS = "This is Solaris";
            return myOS;
        } else {
            System.out.println("Your OS is not support!!");
            myOS = "Your OS is not support!!";
            return myOS;
        }
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0
                || OS.indexOf("nux") >= 0
                || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

}
