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
import ru.taksebe.telegram.writeRead.entity.SamplesFileNameEntity;
import ru.taksebe.telegram.writeRead.repository.SamplesFileNameCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Tag(name = "SamplesFileNameCrudRepositoryTests", description = "Набор тестов для работы с БД, таблица: samples_filename")
public class SamplesFileNameCrudRepositoryTests {

    @Autowired
    SamplesFileNameCrudRepository samplesFileNameCrudRepository;

    @Operation(summary = "Get", description = "findAllFromSampleFileName")
    @Test
    void findAllFromxlsLoadSettingsFiles() {
        List<SamplesFileNameEntity> notes = samplesFileNameCrudRepository.findAllFromSampleFileName();
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesById")
    @Test
    void findAllFromMaterialsById() {
        List<SamplesFileNameEntity> SamplesFilesName = samplesFileNameCrudRepository.findAllFromSamplesFileNameById(2L);
        SamplesFilesName.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameByFileNameV1")
    @Test
    void findAllFromSamplesFileNameByFileNameV1() {
        List<SamplesFileNameEntity> SamplesFilesName =
                samplesFileNameCrudRepository.findAllFromSamplesFileNameByFileNameV1("3_3_Статус_по_мониторингу_мероприятий_ЭР");
        SamplesFilesName.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "Get", description = "findAllFromSamplesByRubricNumber")
    @Test
    void findAllFromSamplesByRubricNumber() {
        List<SamplesFileNameEntity> SamplesFilesName =
                samplesFileNameCrudRepository.findAllFromSamplesByRubricNumber("3.1");
        SamplesFilesName.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

//    @Operation(summary = "Get", description = "findAllFromSamplesByRubricNumber")
//    @Transactional(readOnly = true)
//    @Query(value = "SELECT * FROM samples_filename WHERE rubric_book_number=:rubricNumber ORDER BY date_setting DESC LIMIT 1", nativeQuery = true)
//    List<SamplesFileNameEntity> findAllFromSamplesByRubricNumber(String rubricNumber);

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameRangeByDate")
    @Test
    void findAllFromSamplesFileNameRangeByDate() {
        List<SamplesFileNameEntity> SamplesFilesName =
                samplesFileNameCrudRepository.findAllFromSamplesFileNameRangeByDate();
        SamplesFilesName.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameFirst Выбрать строку с максимальным занчением даты")
    @Test
    void findAllFromSamplesFileNameFirst() {
        List<SamplesFileNameEntity> SamplesFilesName =
                samplesFileNameCrudRepository.findAllFromSamplesFileNameFirst();
        SamplesFilesName.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Test
    void findAllFromSamplesFileNameFirstParam1() {
        List<SamplesFileNameEntity> SamplesFilesName = samplesFileNameCrudRepository.findAllFromSamplesFileNameFirstParam1("3_3_Статус_по_мониторингу_мероприятий_ЭР");
        SamplesFilesName.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    @Operation(summary = "Delete", description = "deleteVoidWhereIdParametr")
    @Test
    void deleteVoidWhereIdParametr() {
        samplesFileNameCrudRepository.deleteVoidWhereIdParametr(1L);
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_SampleFileNames_All13v1() {
        samplesFileNameCrudRepository.create_SampleFileNames_All13(
                "3.1",
                "наццели",
                "СпрДостНЦР",
                "3.1_Справка НЦР",
                "3.1_Справка НЦР",
                "31cправканцр",
                LocalDateTime.now(),
                "",
                "month",
                "",
                "",
                "TarasovIY"
        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_SampleFileNames_All13v2() {
        samplesFileNameCrudRepository.create_SampleFileNames_All13(
                "1.1",
                "нацпроекты",
                "ТабУровДост",
                "1.1_УД_НП_проектный_офис",
                "1.1_УД_НП_проектный_офис",
                "11удннппроектныйофис",
                LocalDateTime.now(),
                "",
                "month",
                "",
                "",
                "TarasovIY"
        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_SampleFileNames_All13v3() {
        samplesFileNameCrudRepository.create_SampleFileNames_All13(
                "3.3",
                "наццели",
                "ТабМонМерЭтРее",
                "3_3_Статус_по_мониторингу_мероприятий_ЭР",
                "3_3_Статус_по_мониторингу_мероприятий_ЭР",
                "33статуспомониторингумероприятийэр",
                LocalDateTime.now(),
                "",
                "month",
                "",
                "",
                "TarasovIY"
        );
        Assertions.assertEquals(1, 1);
    }



}
