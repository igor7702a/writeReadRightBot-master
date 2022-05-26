package ru.taksebe.telegram.writeRead.api.dictionaries;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.taksebe.telegram.writeRead.entity.SamplesFileNameEntity;
import ru.taksebe.telegram.writeRead.repository.SamplesFileNameCrudRepository;
import ru.taksebe.telegram.writeRead.entity.UsersProfilesEntity;
import ru.taksebe.telegram.writeRead.repository.UsersProfilesCrudRepository;
import ru.taksebe.telegram.writeRead.exceptions.DictionaryTooBigException;
import ru.taksebe.telegram.writeRead.model.Dictionary;
import ru.taksebe.telegram.writeRead.model.Word;
import ru.taksebe.telegram.writeRead.service.SaveFiles;
import ru.taksebe.telegram.writeRead.service.OSValidator;
import ru.taksebe.telegram.writeRead.repository.SavedFilesCrudRepository;
import ru.taksebe.telegram.writeRead.service.XLSLoadSamplesFilesService;
import ru.taksebe.telegram.writeRead.service.XLSLoadUsersProfilesService;
import ru.taksebe.telegram.writeRead.service.XLSNewFormatLoadSettingFilesService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DictionaryMaterialsAdditionService {

    @Autowired
    SaveFiles saveFiles;
    @Autowired
    OSValidator osValidator;
    @Autowired
    SavedFilesCrudRepository savedFilesCrudRepository;
    @Autowired
    XLSLoadSamplesFilesService xlsLoadSamplesFilesService;
    @Autowired
    UsersProfilesCrudRepository usersProfilesCrudRepository;
    @Autowired
    XLSLoadUsersProfilesService xlsLoadUsersProfilesService;
    @Autowired
    XLSNewFormatLoadSettingFilesService xlsNewFormatLoadSettingFilesService;
    @Autowired
    SamplesFileNameCrudRepository samplesFileNameCrudRepository;

    private final DictionaryRepository repository;

    public void addUserDictionaryMaterials(String userId, File file, String fileName, String userName) throws IOException {

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            String fileSourceName = fileName;
            String fileDestName = fileName;
            String pathFile = "";
            String pathFileNextWeek = "";
            String fileNameExtension = "";
            String fileNameWithoutExtension = "";
            String DateForFilename = "";
            Boolean thisIsWeekSend = false;

            // Получить права пользователя на загрузку переданного файла
            List<UsersProfilesEntity> result12 = usersProfilesCrudRepository.findAllFromUsersProfilesBy3Param(
                    "LoadSettings", userName, fileName.toString());
            result12.forEach(it12-> System.out.println(it12));
            System.out.println("result12.size = " + result12.size());
            int resultExists12 = result12.size();

            // Получить и передать тип данных fis (file input stream)
            if(fileSourceName.equals("samples.xlsx") && resultExists12 != 0){
                xlsLoadSamplesFilesService.xlsLoadSamplesFileNewFormat(fileInputStream);
            } else if(fileSourceName.equals("usersprofiles.xlsx") && resultExists12 != 0){
                xlsLoadUsersProfilesService.xlsLoadUsersProfilesNewFormat(fileInputStream);
            } else if(fileSourceName.equals("xlsloadsettingsfiles.xlsx") && resultExists12 != 0){
                xlsNewFormatLoadSettingFilesService.xlsLoadFileNewFormat(fileInputStream);
            }
            else {
                // Здесь основной код для загрузки материалов
                // - Протестировать изменение кода
                // For OS +
                pathFile = saveFiles.GetPathFileReal(fileName, userName);
                thisIsWeekSend = getThisIsWeekSend(fileName);
                fileNameExtension = saveFiles.getFileExtension(fileName);
                fileNameWithoutExtension = fileName.replace(("." + fileNameExtension), "");
                DateForFilename = saveFiles.GetStringDateForNameFile();
                if(thisIsWeekSend){
                    pathFileNextWeek = saveFiles.GetPathFileRealNextWeek(fileName, userName);
                }
                String myOS = osValidator.returnOS();
                String pathOS = "";

                if(myOS == "This is Windows"){
                    pathOS = "c:/Books/files/";
                }
                else if(myOS == "This is Unix or Linux"){
                    pathOS = "/home/svc_chatbot/Books/files/";
                }
                else {
                }
                StringBuilder sbPath = new StringBuilder(pathOS);
                StringBuilder fullPathSource = new StringBuilder(pathOS + pathFile + fileSourceName);
                StringBuilder fullPathDest = new StringBuilder(
                        pathOS + pathFile + fileNameWithoutExtension + "_" + DateForFilename + "." + fileNameExtension);
                StringBuilder fullPathDestNextWeek = new StringBuilder(
                        pathOS + pathFileNextWeek + fileNameWithoutExtension + "_" + DateForFilename + "." + fileNameExtension);
                File source = file;
                File dest = new File(fullPathDest.toString());
                File destNextWeek = new File(fullPathDestNextWeek.toString());

                saveFiles.copyFileUsingApacheCommonsIO(source, dest);
                if(thisIsWeekSend){
                    saveFiles.copyFileUsingApacheCommonsIO(source, destNextWeek);
                }

                // Запись информации в таблицу Лог в БД
                savedFilesCrudRepository.create_SavedFiles_All6(
                        fileNameWithoutExtension + "_" + DateForFilename + "." + fileNameExtension,
                        fullPathDest.toString(),
                        LocalDateTime.now(),
                        userName,
                        "DescriptionFromTelegram",
                        "Good"
                );
                if(thisIsWeekSend){
                    savedFilesCrudRepository.create_SavedFiles_All6(
                            fileNameWithoutExtension + "_" + DateForFilename + "." + fileNameExtension,
                            fullPathDestNextWeek.toString(),
                            LocalDateTime.now(),
                            userName,
                            "DescriptionFromTelegram",
                            "Good"
                    );
                }
            }
        }
    }

    public void addDefaultDictionary(String dictionaryId, XSSFWorkbook workbook) {
        repository.save(Dictionary.builder().id(dictionaryId).wordList(createDictionary(workbook)).build());
    }

    private List<Word> createDictionary(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        List<Word> result = new ArrayList<>();
        while (rowIterator.hasNext()) {
            result.add(createDictionaryWord(rowIterator.next()));
        }
        result.remove(0);

        if (result.size() > 1000) {
            throw new DictionaryTooBigException();
        }
        return result;
    }

    private Word createDictionaryWord(Row row) {
        Iterator<Cell> cellIterator = row.iterator();

        List<String> line = new ArrayList<>();
        while (cellIterator.hasNext()) {
            line.add(cellIterator.next().getStringCellValue());
        }

        String key = line.get(0);
        line.remove(0);

        return new Word(key, new HashSet<>(line));
    }

    public Boolean getThisIsWeekSend(String fileName){
        Boolean ThisIsWeekSend = false;

        List<SamplesFileNameEntity> result13 = samplesFileNameCrudRepository.findAllFromSamplesFileNameFirstParam1(fileName);
        result13.forEach(it13-> System.out.println(it13));
        System.out.println("result13.size = " + result13.size());
        int resultExists13 = result13.size();
        if(resultExists13 != 0){
            ThisIsWeekSend = true;
        }
        return ThisIsWeekSend;
    }
}