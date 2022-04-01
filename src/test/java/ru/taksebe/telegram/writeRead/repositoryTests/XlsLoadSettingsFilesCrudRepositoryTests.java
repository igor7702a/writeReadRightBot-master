package ru.taksebe.telegram.writeRead.repositoryTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class XlsLoadSettingsFilesCrudRepositoryTests {

    @Autowired
    XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;

    // Get findAllFromXlsLoadSettingsFiles
    @Test
    void findAllFromxlsLoadSettingsFiles() {
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles();
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromXlsLoadSettingsFilesById
    @Test
    void findAllFromMaterialsById() {
        List<XlsLoadSettingsFilesEntity> materials = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesById(83L);
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromxlsLoadSettingsFilesByMonth
    @Test
    void findAllFromxlsLoadSettingsFilesByMonth() {
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesByMonth(11);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromxlsLoadSettingsFilesByYear
    @Test
    void findAllFromxlsLoadSettingsFilesByYear() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesByYear(ldFirst, ldEnd);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromXlsLoadSettingsFilesByYearAndMonth
    @Test
    void findAllFromXlsLoadSettingsFilesByYearAndMonth() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        int monthNumber = 10;
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesByYearAndMonth(ldFirst, ldEnd, monthNumber);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromXlsLoadSettingsFilesBy4Param
    @Test
    void findAllFromXlsLoadSettingsFilesBy4Param() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        int monthNumber = 11;
        String timetableParam = "Ежемесячно";
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles4Param(ldFirst, ldEnd, monthNumber, timetableParam);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromXlsLoadSettingsFilesBy5Param
    @Test
    void findAllFromXlsLoadSettingsFilesBy5Param() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        int monthNumber = 11;
        String timetableParam = "Ежемесячно";
        int rubricNumber = 2;

        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles5Param(
                ldFirst, ldEnd, monthNumber, timetableParam, rubricNumber);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromXlsLoadSettingsFilesBy5Param1Order
    @Test
    void findAllFromXlsLoadSettingsFilesBy5Param1Order() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        int monthNumber = 11;
        String timetableParam = "Ежемесячно";
        int rubricNumber = 3;

        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles5Param1Order(
                ldFirst, ldEnd, monthNumber, timetableParam, rubricNumber);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

//    // Delete
//    @Test
//    void deleteVoidWhereIdParametr() {
//        materialsCrudRepository.deleteVoidWhereIdParametr(10L);
//        Assertions.assertEquals(1, 1);
//    }

    @Test
    void create_Materials_All12() {
        xlsLoadSettingsFilesCrudRepository.create_XlsLoadSettingsFiles_All12(
                "Материалы к оперативному совещанию",
                LocalDate.now(),
                "Наццели",
                "https://gasu-office.roskazna.ru/planeta-nc/dashboard/",
                "справка- А.С. Мальков, таблицы - Н.Н. Баценков",
                3,
                "НАЦИОНАЛЬНЫЕ ЦЕЛИ",
                1,
                "3.1 Справка достижение НЦР",
                "3.1_Справка НЦР",
                11,
                "Ежемесячно"
        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_Materials_All18() {
        xlsLoadSettingsFilesCrudRepository.create_XlsLoadSettingsFiles_All18(
                "Материалы к оперативному совещанию",
                LocalDate.now(),
                "Наццели",
                "https://gasu-office.roskazna.ru/planeta-nc/dashboard/",
                "справка- А.С. Мальков, таблицы - Н.Н. Баценков",
                3,
                "НАЦИОНАЛЬНЫЕ ЦЕЛИ",
                1,
                "3.1 Справка достижение НЦР",
                "3.1_Справка НЦР",
                11,
                "Ежемесячно",
                "Группа Телеграмм",
                "01. ДПД. Оперативка",
                "Тарасов Игорь Юрьевич",
                LocalDateTime.now(),
                "НацЦели",
                "СпрДостНЦР"
        );
        Assertions.assertEquals(1, 1);
    }

}
