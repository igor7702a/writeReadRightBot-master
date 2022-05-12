package ru.taksebe.telegram.writeRead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.AddressEntity;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface AddressCrudRepository extends JpaRepository<AddressEntity, Long> {

    //Get findAllFromAddress
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM address", nativeQuery = true)
    List<AddressEntity> findAllFromAddress();

    //Get findAllFromAddressById
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM address WHERE id=:id", nativeQuery = true)
    List<AddressEntity> findAllFromAddressById(long id);

    //Get findAllFromAddressBy3Param
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM address WHERE (" +
            "type_address=:typeAddress " +
            "and matter_address=:matterAddress " +
            "and description_address =:descriptionAddress) " +
            "LIMIT 1", nativeQuery = true)
    List<AddressEntity> findAllFromAddressBy3Param(String typeAddress, String matterAddress, String descriptionAddress);

    //Delete
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from address where id=:id")
    void deleteVoidWhereIdParametr(long id);

    //Create
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into address (" +
            "type_address," +
            "matter_address," +
            "description_address," +
            "datetime_upload," +
            "responsible" +
            ") " +
            "VALUES (" +
            ":typeAddress," +
            ":matterAddress," +
            ":descriptionAddress," +
            ":datetimeUpload," +
            ":responsible" +
            ")"
    )
    void create_Address_All5(
            String typeAddress,
            String matterAddress,
            String descriptionAddress,
            LocalDateTime datetimeUpload,
            String responsible
    );

    //Update
}

//    id SERIAL PRIMARY KEY,
//    type_address VARCHAR(150),
//    matter_address VARCHAR(150),
//    description_address VARCHAR(150),
//    datetime_upload timestamp,
//    responsible VARCHAR(100)