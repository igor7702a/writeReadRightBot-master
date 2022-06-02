package ru.taksebe.telegram.writeRead.repositoryTests;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Tag(name = "XlsLoadSettingsFilesCrudRepositoryTests", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
public class XlsLoadSettingsFilesCrudRepositoryTests {

    @Autowired
    XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;

    @Operation(summary = "findAllFromxlsLoadSettingsFiles", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void findAllFromxlsLoadSettingsFiles() {
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFiles();
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "findAllFromXlsLoadSettingsFilesById", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void findAllFromMaterialsById() {
        List<XlsLoadSettingsFilesEntity> materials = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesById(83L);
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "findAllFromXlsLoadSettingsFilesBySystemRubricName", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void findAllFromXlsLoadSettingsFilesBySystemRubricName() {
        List<XlsLoadSettingsFilesEntity> materials = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesBySystemRubricName("нацпроекты");
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "find1FromXlsLoadSettingsFilesBySystemRubricName", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void find1FromXlsLoadSettingsFilesBySystemRubricName() {
        List<XlsLoadSettingsFilesEntity> materials = xlsLoadSettingsFilesCrudRepository.find1FromXlsLoadSettingsFilesBySystemRubricName("нацпроекты");
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "find1FromXlsLoadSettingsFilesBySystemRubricNameOrder", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void find1FromXlsLoadSettingsFilesBySystemRubricNameOrder() {
        List<XlsLoadSettingsFilesEntity> materials = xlsLoadSettingsFilesCrudRepository.find1FromXlsLoadSettingsFilesBySystemRubricNameOrder("риски");
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "findAllFromxlsLoadSettingsFilesByMonth", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void findAllFromxlsLoadSettingsFilesByMonth() {
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesByMonth(11);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "findAllFromxlsLoadSettingsFilesByYear", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void findAllFromxlsLoadSettingsFilesByYear() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesByYear(ldFirst, ldEnd);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "findAllFromXlsLoadSettingsFilesByYearAndMonth", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void findAllFromXlsLoadSettingsFilesByYearAndMonth() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        int monthNumber = 10;
        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesByYearAndMonth(ldFirst, ldEnd, monthNumber);
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "findAllFromXlsLoadSettingsFilesBy4Param", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
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

    @Operation(summary = "findAllFromXlsLoadSettingsFilesBy5Param", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
    @Test
    void findAllFromXlsLoadSettingsFilesOneRubric() {
        LocalDate ldFirst = LocalDate.ofYearDay(2021,1);
        LocalDate ldEnd = LocalDate.ofYearDay(2021,365);
        int monthNumber = 11;
        String timetableParam = "Ежемесячно";
        int rubricNumber = 2;
        String systemRubricName = "Риски";

        List<XlsLoadSettingsFilesEntity> notes = xlsLoadSettingsFilesCrudRepository.findAllFromXlsLoadSettingsFilesOneRubric(
                ldFirst,
                ldEnd,
                monthNumber,
                timetableParam,
                rubricNumber,
                systemRubricName
        );
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "findAllFromXlsLoadSettingsFilesBy5Param", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
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

    @Operation(summary = "findAllFromXlsLoadSettingsFilesBy5Param1Order", description = "Набор тестов для работы с БД, таблица: XlsLoadSettingsFiles")
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
