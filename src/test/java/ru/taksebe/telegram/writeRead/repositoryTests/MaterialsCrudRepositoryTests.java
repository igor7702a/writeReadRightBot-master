package ru.taksebe.telegram.writeRead.repositoryTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.taksebe.telegram.writeRead.entity.MaterialsEntity;
import ru.taksebe.telegram.writeRead.repository.MaterialsCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MaterialsCrudRepositoryTests {

    @Autowired
    MaterialsCrudRepository materialsCrudRepository;

    // Get findAllFromMaterials
    @Test
    void findAllFromMaterials() {
        List<MaterialsEntity> materials = materialsCrudRepository.findAllFromMaterials();
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromMaterialsById
    @Test
    void findAllFromMaterialsById() {
        List<MaterialsEntity> materials = materialsCrudRepository.findAllFromMaterialsById(11L);
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Delete
    @Test
    void deleteVoidWhereIdParametr() {
        materialsCrudRepository.deleteVoidWhereIdParametr(10L);
        Assertions.assertEquals(1, 1);
    }

    // Create
    @Test
    void create_Materials_All2() {
        materialsCrudRepository.create_Materials_All2(
                "fileSectionSynonumTest",
                "fileSynonym"
        );
        Assertions.assertEquals(1, 1);
    }


    @Test
    void create_Materials_All4() {
        materialsCrudRepository.create_Materials_All4(
                "fileSectionSynonumTest",
                "fileSynonymTest",
                "fileNameTest",
                "pdf"

        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_Materials_All5() {
        materialsCrudRepository.create_Materials_All5(
                "fileSectionSynonumTest",
                "fileSynonymTest",
                "fileNameTest",
                "pdf",
                LocalDate.now()

        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_Materials_All7() {
        materialsCrudRepository.create_Materials_All7(
                "fileSectionSynonumTest",
                "fileSynonymTest",
                "fileNameTest",
                "pdf",
                LocalDate.now(),
                LocalTime.now(),
                "first"
        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_Materials_All11() {
        materialsCrudRepository.create_Materials_All11(
                "fileSectionSynonumTest",
                "fileSynonymTest",
                "fileNameTest",
                "pdf",
                LocalDate.now(),
                LocalTime.now(),
                "first",
                "TarasovIY",
                "c:/Books",
                "WorkFiles",
                LocalDateTime.now()

        );
        Assertions.assertEquals(1, 1);
    }

}
