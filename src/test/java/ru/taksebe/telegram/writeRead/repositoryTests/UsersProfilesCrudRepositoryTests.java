package ru.taksebe.telegram.writeRead.repositoryTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import ru.taksebe.telegram.writeRead.repository.XlsLoadSettingsFilesCrudRepository;
import ru.taksebe.telegram.writeRead.entity.UsersProfilesEntity;
import ru.taksebe.telegram.writeRead.repository.UsersProfilesCrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UsersProfilesCrudRepositoryTests {

    @Autowired
    UsersProfilesCrudRepository usersProfilesCrudRepository;

    // Get findAllFromUsersProfiles
    @Test
    void findAllFromUsersProfiles() {
        List<UsersProfilesEntity> notes = usersProfilesCrudRepository.findAllFromUsersProfiles();
        notes.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromXlsLoadSettingsFilesById
    @Test
    void findAllFromMaterialsById() {
        List<UsersProfilesEntity> materials = usersProfilesCrudRepository.findAllFromUsersProfilesById(2L);
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Delete
    @Test
    void deleteVoidWhereIdParametr() {
        usersProfilesCrudRepository.deleteVoidWhereIdParametr(1L);
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_UsersProfiles_All8() {
        usersProfilesCrudRepository.create_UserProfiles_All8(
                "3.1",
                "наццели",
                "СпрДостНЦР",
                "3.1_Справка НЦР.pdf",
                "LoadAndSave",
                "TarasovIY",
                LocalDateTime.now(),
                "TarasovIY"
        );
        Assertions.assertEquals(1, 1);
    }

}