package ru.taksebe.telegram.writeRead.repositoryTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
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
