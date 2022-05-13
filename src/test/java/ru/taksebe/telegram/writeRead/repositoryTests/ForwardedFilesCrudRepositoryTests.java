package ru.taksebe.telegram.writeRead.repositoryTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;
import ru.taksebe.telegram.writeRead.entity.ForwardedFilesEntity;
import ru.taksebe.telegram.writeRead.repository.ForwardedFilesCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ForwardedFilesCrudRepositoryTests {

    @Autowired
    XlsLoadSettingsFilesCrudRepository xlsLoadSettingsFilesCrudRepository;
    @Autowired
    ForwardedFilesCrudRepository forwardedFilesCrudRepository;

    // Get findAllFromXlsLoadSettingsFiles
    @Test
    void findAllFromForwardedFiles() {
        List<ForwardedFilesEntity> notes = forwardedFilesCrudRepository.findAllFromForwardedFiles();
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromForwardedFilesById
    @Test
    void findAllFromForwardedFilesById() {
        List<ForwardedFilesEntity> note = forwardedFilesCrudRepository.findAllFromForwardedFilesById(4L);
        note.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Delete
    @Test
    void deleteVoidWhereIdParametr() {
        forwardedFilesCrudRepository.deleteVoidWhereIdParametr(15L);
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_ForwardedFiles_All16() {
        forwardedFilesCrudRepository.create_ForwardedFiles_All16(
                "3.1",
                "наццели",
                "СпрДостНЦР",
                "3.1_Справка НЦР_220505_144236.pdf",
                "2022",
                "months",
                "5",
                LocalDateTime.now(),
                "Good",
                "bot telegram",
                "@igor7702testbot",
                "5276533294",
                LocalDateTime.now(),
                "TarasovIY"
        );
        Assertions.assertEquals(1, 1);
    }

}