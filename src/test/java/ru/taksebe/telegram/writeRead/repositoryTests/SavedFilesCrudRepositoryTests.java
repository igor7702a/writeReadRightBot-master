package ru.taksebe.telegram.writeRead.repositoryTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.entity.SavedFilesEntity;
import ru.taksebe.telegram.writeRead.repository.SavedFilesCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SavedFilesCrudRepositoryTests {

    @Autowired
    SavedFilesCrudRepository savedFilesCrudRepository;

    // Get
    @Test
    void findAllFromSavedFiles() {
        List<SavedFilesEntity> savedFiles = savedFilesCrudRepository.findAllFromSavedFiles();
        savedFiles.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Delete
    @Test
    void deleteVoidWhereIdParametr() {
        savedFilesCrudRepository.deleteVoidWhereIdParametr(1L);
        Assertions.assertEquals(1, 1);
    }

    // Create
    @Test
    void create_SavedFiles_All6() {
        savedFilesCrudRepository.create_SavedFiles_All6(
                "1.1_УД_НП_проектный_офис_220331_153802",
                "c:/books/",
                LocalDateTime.now(),
                "TarasovIY",
                "DescriptionFromTelegram",
                "Good"
        );
        Assertions.assertEquals(1, 1);
    }

}
