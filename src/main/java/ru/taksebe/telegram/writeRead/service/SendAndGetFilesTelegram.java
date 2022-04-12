package ru.taksebe.telegram.writeRead.service;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.taksebe.telegram.writeRead.telegram.TelegramApiClient;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Component
public class SendAndGetFilesTelegram {

    public void uploadFile(String file_name, String file_id) throws IOException {
        String token = "";
        String upPath = "";
        URL url = new URL("https://api.telegram.org/bot"+token+"/getFile?file_id="+file_id);
        BufferedReader in = new BufferedReader(new InputStreamReader( url.openStream()));
        String res = in.readLine();
        JSONObject jresult = new JSONObject(res);
        JSONObject path = jresult.getJSONObject("result");
        String file_path = path.getString("file_path");
        URL downoload = new URL("https://api.telegram.org/file/bot" + token + "/" + file_path);
        FileOutputStream fos = new FileOutputStream(upPath + file_name);
        System.out.println("Start upload");
        ReadableByteChannel rbc = Channels.newChannel(downoload.openStream());
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        int uploadFlag = 0;
        System.out.println("Uploaded!");
    }

    public void sendDocument(String file_name, String file_id) throws IOException {
        String token = "5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4";
        String upPath = "c:/books/";
        file_name= "new.jpg";
        file_id = "AAMCBAADGQMAAgHiYk6ZEvv6ciQtEMp90nF16o_j-owAAhcDAAKuGnVSxKpibmP79SABAAdtAAMjBA";

        URL url = new URL("https://api.telegram.org/bot"+token+"/getFile?file_id="+file_id);
        BufferedReader in = new BufferedReader(new InputStreamReader( url.openStream()));
        String res = in.readLine();
        JSONObject jresult = new JSONObject(res);
        JSONObject path = jresult.getJSONObject("result");
        String file_path = path.getString("file_path");
        URL downoload = new URL("https://api.telegram.org/file/bot" + token + "/" + file_path);
        FileOutputStream fos = new FileOutputStream(upPath + file_name);
        System.out.println("Start upload");
        ReadableByteChannel rbc = Channels.newChannel(downoload.openStream());
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        int uploadFlag = 0;
        System.out.println("Uploaded!");

    }

    // Information about telegram bot
    public void getMe() throws IOException {
        // https://api.telegram.org/bot5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4/getMe
        URL url = new URL("https://api.telegram.org/bot5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4/getMe");
        BufferedReader in = new BufferedReader(new InputStreamReader( url.openStream()));
        String res = in.readLine();
        JSONObject jresult = new JSONObject(res);
        JSONObject path = jresult.getJSONObject("result");
        System.out.println("JSONObject jresult - " + jresult.toString());
    }

    // Information about telegram bot
    public void sendPhotoJPG() throws IOException {
        // https://api.telegram.org/bot5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4/sendDocument?chat_id=5297506090&document=https://telegram.org/img/SiteiOS.jpg
        URL url = new URL("https://api.telegram.org/bot5276533294:AAFwk5tSnqX3pZ4Ttp-u2oA6WRjHvPQI_F4/sendDocument?chat_id=5297506090&document=https://telegram.org/img/SiteiOS.jpg");
        BufferedReader in = new BufferedReader(new InputStreamReader( url.openStream()));
        String res = in.readLine();
        JSONObject jresult = new JSONObject(res);
        JSONObject path = jresult.getJSONObject("result");
        System.out.println("JSONObject jresult - " + jresult.toString());
    }

    public void sendImageUploadingAFile(String filePath, String chatId) {
        filePath = "c:/books/new.jpg";
        chatId = "5297506090";
        // Create send method
        SendPhoto sendPhotoRequest = new SendPhoto();
        // Set destination chat id
        sendPhotoRequest.setChatId(chatId);
        // Set the photo file as a new photo (You can also use InputStream with a constructor overload)
        sendPhotoRequest.setPhoto(new InputFile(new File(filePath)));
//        try {
//            // Execute the method
//            execute(sendPhotoRequest);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }

}
