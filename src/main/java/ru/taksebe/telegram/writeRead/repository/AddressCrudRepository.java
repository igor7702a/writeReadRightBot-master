package ru.taksebe.telegram.writeRead.repository;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.AddressEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface AddressCrudRepository extends JpaRepository<AddressEntity, Long> {

    @Operation(summary = "Get", description = "findAllFromAddress")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM address", nativeQuery = true)
    List<AddressEntity> findAllFromAddress();

    @Operation(summary = "Get", description = "findAllFromAddressById")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM address WHERE id=:id", nativeQuery = true)
    List<AddressEntity> findAllFromAddressById(long id);

    @Operation(summary = "Get", description = "findAllFromAddressBy3Param")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM address WHERE (" +
            "type_address=:typeAddress " +
            "and matter_address=:matterAddress " +
            "and description_address =:descriptionAddress) " +
            "LIMIT 1", nativeQuery = true)
    List<AddressEntity> findAllFromAddressBy3Param(String typeAddress, String matterAddress, String descriptionAddress);

    @Operation(summary = "Delete", description = "deleteVoidWhereIdParametr")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from address where id=:id")
    void deleteVoidWhereIdParametr(long id);

    @Operation(summary = "Create", description = "create_Address_All5")
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