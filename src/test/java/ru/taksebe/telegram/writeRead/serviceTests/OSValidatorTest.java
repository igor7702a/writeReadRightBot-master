package ru.taksebe.telegram.writeRead.serviceTests;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.service.OSValidator;

@SpringBootTest
@Tag(name = "OSValidatorTest", description = "Тест по определению Операционной системы")
public class OSValidatorTest {

    @Autowired
    OSValidator osValidator;

    @Test
    void receiveOS() {
        osValidator.receiveOS();
        Assertions.assertEquals(1, 1);
    }

    @Test
    void OS() {
        String myOS = osValidator.returnOS();
        System.out.println("My OS is " + myOS);
        Assertions.assertEquals(1, 1);
    }
}


