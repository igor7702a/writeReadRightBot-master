package ru.taksebe.telegram.writeRead.repositoryTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.UsersProfilesEntity;
import ru.taksebe.telegram.writeRead.repository.UsersProfilesCrudRepository;

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
        List<UsersProfilesEntity> materials = usersProfilesCrudRepository.findAllFromUsersProfilesById(4L);
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromUsersProfilesBy4Test
    @Test
    void findAllFromUsersProfilesBy4Test() {
        List<UsersProfilesEntity> materials = usersProfilesCrudRepository.findAllFromUsersProfilesBy4Test();
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromUsersProfilesBy4Param - only 3
    @Test
    void findAllFromUsersProfilesBy3Param() {
        List<UsersProfilesEntity> materials = usersProfilesCrudRepository.findAllFromUsersProfilesBy4Param(
            "TarasovIY",
            "наццели",
            "СпрДостНЦР"
        );
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromUsersProfilesBy4Param - all 4
    @Test
    void findAllFromUsersProfilesBy4Param() {
        List<UsersProfilesEntity> materials = usersProfilesCrudRepository.findAllFromUsersProfilesBy4Param(
                "PressMainButtonUPLOAD_MATERIALS_BUTTON",
                "TarasovIY",
                "Нет",
                "Нет"
        );
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromUsersProfilesBy2Param
    @Test
    void findAllFromUsersProfilesBy2Param() {
        List<UsersProfilesEntity> materials = usersProfilesCrudRepository.findAllFromUsersProfilesBy2Param(
                "PressMainButtonUPLOAD_MATERIALS_BUTTON",
                "TarasovIY"
        );
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromUsersProfilesBy3Param
    @Test
    void findAllFromUsersProfilesBy3ParamLoadSettings() {
        List<UsersProfilesEntity> materials = usersProfilesCrudRepository.findAllFromUsersProfilesBy3Param(
                "LoadSettings",
                "TarasovIY",
                "usersprofiles.xlsx"
        );
        materials.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Delete
    @Test
    void deleteVoidWhereIdParametr() {
        usersProfilesCrudRepository.deleteVoidWhereIdParametr(5L);
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
                "TarasovIY",
                "Тарасов Игорь Юрьевич"
        );
        Assertions.assertEquals(1, 1);
    }

    @Test
    void create_UsersProfiles_ForFileWithSettings() {
        usersProfilesCrudRepository.create_UserProfiles_All8(
                "None",
                "None",
                "None",
                "usersprofiles.xlsx",
                "LoadSettings",
                "TarasovIY",
                LocalDateTime.now(),
                "TarasovIY",
                "Тарасов Игорь Юрьевич"
        );
        Assertions.assertEquals(1, 1);
    }

}
