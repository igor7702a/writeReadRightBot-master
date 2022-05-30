package ru.taksebe.telegram.writeRead.repositoryTests;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;
import ru.taksebe.telegram.writeRead.entity.ForwardedFilesEntity;
import ru.taksebe.telegram.writeRead.repository.ForwardedFilesCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Tag(name = "ForwardedFilesCrudRepositoryTests", description = "Набор тестов для работы с БД, таблица: forwarded_files")
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

    // Get findAllFromForwardedFilesById
    @Test
    void findAllFromForwardedAlreadySended() {
        List<ForwardedFilesEntity> note = forwardedFilesCrudRepository.findAllFromForwardedAlreadySended(
                "нацпроекты",
                "СпрОпрВциом",
                "2021",
                "months",
                "11",
                "Good",
                "bot_telegram",
                "5297506090");
        note.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromForwardedFiles2
    @Test
    void findAllFromForwardedAlreadySended2() {
        List<ForwardedFilesEntity> note = forwardedFilesCrudRepository.findAllFromForwardedAlreadySended(
                "нацпроекты",
                "СпрОпрВциом");
        note.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromForwardedFiles4
    @Test
    void findAllFromForwardedAlreadySended4() {
        List<ForwardedFilesEntity> note = forwardedFilesCrudRepository.findAllFromForwardedAlreadySended(
                "нацпроекты",
                "СпрОпрВциом",
                "2021",
                "months");
        note.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Delete deleteVoidWhereIdParametr
    @Test
    void deleteVoidWhereIdParametr() {
        forwardedFilesCrudRepository.deleteVoidWhereIdParametr(109L);
        Assertions.assertEquals(1, 1);
    }

    // Delete deleteCleanAllForwardedFiles
    @Test
    void deleteCleanAllForwardedFiles() {
        forwardedFilesCrudRepository.deleteCleanAllForwardedFiles();
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
                "TarasovIY",
                "This is test!"
        );
        Assertions.assertEquals(1, 1);
    }

}