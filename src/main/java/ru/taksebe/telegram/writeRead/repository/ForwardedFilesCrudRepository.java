package ru.taksebe.telegram.writeRead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.ForwardedFilesEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface ForwardedFilesCrudRepository extends JpaRepository<ForwardedFilesEntity, Long> {

    //Get findAllFromForwardedFiles
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM forwarded_files", nativeQuery = true)
    List<ForwardedFilesEntity> findAllFromForwardedFiles();

    //Get findAllFromForwardedFilesById
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM forwarded_files WHERE id=:id", nativeQuery = true)
    List<ForwardedFilesEntity> findAllFromForwardedFilesById(long id);

    //Get findAllFromForwardedAlreadySended
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM forwarded_files WHERE (" +
            "system_rubric_name=:systemRubricName " +
            "and system_file_name = :systemFileName " +
            "and st_year = :stYear " +
            "and st_period = :stPeriod " +
            "and number_period = :numberPeriod " +
            "and harvest = :harvest " +
            "and type_address = :typeAddress " +
            "and description_address = :descriptionAddress" +
            ")" +
            "ORDER BY datetime_upload DESC " +
            "LIMIT 1"
            , nativeQuery = true)
    List<ForwardedFilesEntity> findAllFromForwardedAlreadySended(
            String systemRubricName,
            String systemFileName,
            String stYear,
            String stPeriod,
            String numberPeriod,
            String harvest,
            String typeAddress,
            String descriptionAddress);

    //Get findAllFromForwardedAlreadySended2
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM forwarded_files WHERE (" +
            "system_rubric_name=:systemRubricName " +
            "and system_file_name = :systemFileName " +
            ")"
            , nativeQuery = true)
    List<ForwardedFilesEntity> findAllFromForwardedAlreadySended(
            String systemRubricName,
            String systemFileName
           );

    //Get findAllFromForwardedAlreadySended4
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM forwarded_files WHERE (" +
            "system_rubric_name=:systemRubricName " +
            "and system_file_name = :systemFileName " +
            "and st_year = :stYear " +
            "and st_period = :stPeriod " +
            ") " +
            "ORDER BY datetime_upload DESC " +
            "LIMIT 1"
            , nativeQuery = true)
    List<ForwardedFilesEntity> findAllFromForwardedAlreadySended(
            String systemRubricName,
            String systemFileName,
            String stYear,
            String stPeriod);

    //Delete deleteVoidWhereIdParametr
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from forwarded_files where id=:id")
    void deleteVoidWhereIdParametr(long id);

    //Delete deleteCleanAllForwardedFiles
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from forwarded_files where id>0")
    void deleteCleanAllForwardedFiles();

    //Create create_XlsLoadSettingsFiles_All
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into forwarded_files (" +
            "rubric_book_number," +
            "system_rubric_name," +
            "system_file_name," +
            "full_file_name," +
            "st_year," +
            "st_period," +
            "number_period," +
            "delivery_date," +
            "harvest," +
            "type_address," +
            "matter_address," +
            "description_address," +
            "datetime_upload," +
            "responsible," +
            "comment" +
            ") " +
            "VALUES (" +
            ":rubricBookNumber," +
            ":systemRubricName," +
            ":systemFileName," +
            ":fullFileNname," +
            ":stYear," +
            ":stPeriod," +
            ":numberPeriod," +
            ":deliveryDate," +
            ":harvest," +
            ":typeAddress," +
            ":matterAddress," +
            ":descriptionAddress," +
            ":datetimeUpload," +
            ":responsible," +
            ":comment" +
            ")"
    )
    void create_ForwardedFiles_All16(
            String rubricBookNumber,
            String systemRubricName,
            String systemFileName,
            String fullFileNname,
            String stYear,
            String stPeriod,
            String numberPeriod,
            LocalDateTime deliveryDate,
            String harvest,
            String typeAddress,
            String matterAddress,
            String descriptionAddress,
            LocalDateTime datetimeUpload,
            String responsible,
            String comment
    );

    //Update
}