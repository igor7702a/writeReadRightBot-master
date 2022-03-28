package ru.taksebe.telegram.writeRead.serviceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.CreateFileStructureForNextYear;

@SpringBootTest
public class FilesTests {

    @Autowired
    CreateFileStructureForNextYear createFileStructureForNextYear;

    // Create folder
    @Test
    void CreateFolder() {
        createFileStructureForNextYear.CreateFolder();
        Assertions.assertEquals(1, 1);
    }

}
