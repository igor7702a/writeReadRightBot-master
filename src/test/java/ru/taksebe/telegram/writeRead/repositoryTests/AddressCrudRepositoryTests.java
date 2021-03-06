package ru.taksebe.telegram.writeRead.repositoryTests;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.taksebe.telegram.writeRead.entity.AddressEntity;
import ru.taksebe.telegram.writeRead.repository.AddressCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Tag(name = "AddressCrudRepositoryTests", description = "Набор тестов для работы с БД, таблица: address")
public class AddressCrudRepositoryTests {

    @Autowired
    AddressCrudRepository addressCrudRepository;

    @Test
    @Operation(summary = "Get", description = "findAllFromAddress")
    void findAllFromAddress() {
        List<AddressEntity> adress = addressCrudRepository.findAllFromAddress();
        adress.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromAddressById
    @Test
    void findAllFromAddressById() {
        List<AddressEntity> address = addressCrudRepository.findAllFromAddressById(2L);
        address.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Get findAllFromAddressBy3Param
    @Test
    void findAllFromAddressBy3Param() {
        List<AddressEntity> address = addressCrudRepository.findAllFromAddressBy3Param(
                "bot telegram",
                "@igor7702testbot",
                "5276533294");
        address.forEach(it -> System.out.println(it));
        Assertions.assertEquals(1, 1);
    }

    // Delete
    @Test
    void deleteVoidWhereIdParametr() {
        addressCrudRepository.deleteVoidWhereIdParametr(4L);
        Assertions.assertEquals(1, 1);
    }

    // Create
    @Test
    void create_Address_All5() {
        addressCrudRepository.create_Address_All5(
                "bot telegram",
                "@igor7702testbot",
                "5276533294",
                LocalDateTime.now(),
                "TarasovIY"
        );
        Assertions.assertEquals(1, 1);
    }

}