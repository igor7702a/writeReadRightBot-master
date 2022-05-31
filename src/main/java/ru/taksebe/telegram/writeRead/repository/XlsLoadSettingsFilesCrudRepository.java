package ru.taksebe.telegram.writeRead.repository;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface XlsLoadSettingsFilesCrudRepository extends JpaRepository<XlsLoadSettingsFilesEntity, Long> {

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFiles")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFiles();

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesById")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE id=:id", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesById(long id);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesBySystemRubricName")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE system_rubric_name=:systemRubricName", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesBySystemRubricName(String systemRubricName);

    @Operation(summary = "Get", description = "find1FromXlsLoadSettingsFilesBySystemRubricName")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE system_rubric_name=:systemRubricName LIMIT 1", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> find1FromXlsLoadSettingsFilesBySystemRubricName(String systemRubricName);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesByMonth")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE month_number=:monthNumber", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesByMonth(int monthNumber);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesByYear")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE (date_item_name>=:dateFirst and date_item_name<=:dateEnd)", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesByYear(LocalDate dateFirst, LocalDate dateEnd);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesByYearAndMonth")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles " +
            "WHERE (" +
            "date_item_name>=:dateFirst " +
            "and date_item_name<=:dateEnd " +
            "and month_number=:monthNumber" +
            ")", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesByYearAndMonth(LocalDate dateFirst, LocalDate dateEnd, int monthNumber);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesBy4Param")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles " +
            "WHERE (" +
            "date_item_name>=:dateFirst " +
            "and date_item_name<=:dateEnd " +
            "and month_number=:monthNumber " +
            "and timetable=:timetableParam" +
            ")", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFiles4Param(
            LocalDate dateFirst, LocalDate dateEnd, int monthNumber, String timetableParam);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFilesOneRubric")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles " +
            "WHERE (" +
            "date_item_name>=:dateFirst " +
            "and date_item_name<=:dateEnd " +
            "and month_number=:monthNumber " +
            "and timetable=:timetableParam " +
            "and rubric_number=:rubricNumber " +
            "and system_rubric_name =:systemRubricName" +
            ")", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesOneRubric(
            LocalDate dateFirst, LocalDate dateEnd, int monthNumber, String timetableParam, int rubricNumber, String systemRubricName);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFiles5Param String systemRubricName")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles " +
            "WHERE (" +
            "date_item_name>=:dateFirst " +
            "and date_item_name<=:dateEnd " +
            "and month_number=:monthNumber " +
            "and timetable=:timetableParam " +
            "and system_rubric_name=:systemRubricName" +
            ")", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFiles5Param(
            LocalDate dateFirst, LocalDate dateEnd, int monthNumber, String timetableParam, String systemRubricName);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFiles5Param")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles " +
            "WHERE (" +
            "date_item_name>=:dateFirst " +
            "and date_item_name<=:dateEnd " +
            "and month_number=:monthNumber " +
            "and timetable=:timetableParam " +
            "and rubric_number=:rubricNumber" +
            ")", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFiles5Param(
            LocalDate dateFirst, LocalDate dateEnd, int monthNumber, String timetableParam, int rubricNumber);

    @Operation(summary = "Get", description = "findAllFromXlsLoadSettingsFiles5Param1Order")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles " +
            "WHERE (" +
            "date_item_name>=:dateFirst " +
            "and date_item_name<=:dateEnd " +
            "and month_number=:monthNumber " +
            "and timetable=:timetableParam " +
            "and rubric_number=:rubricNumber" +
            ") ORDER BY book_number", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFiles5Param1Order(
            LocalDate dateFirst, LocalDate dateEnd, int monthNumber, String timetableParam, int rubricNumber);

//    //Delete
//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true, value = "delete from materials where id=:id")
//    void deleteVoidWhereIdParametr(long id);

    @Operation(summary = "Get", description = "create_XlsLoadSettingsFiles_All12")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into XlsLoadSettingsFiles (" +
            "item_name," +
            "date_item_name," +
            "arm_name," +
            "arm_link," +
            "officer_for," +
            "rubric_number," +
            "rubric_name," +
            "book_number," +
            "book_name," +
            "file_name," +
            "month_number," +
            "timetable" +
            ") " +
            "VALUES (" +
            ":itemName," +
            ":dateItemName," +
            ":armName," +
            ":armLink," +
            ":officerFor," +
            ":rubricNumber," +
            ":rubricName," +
            ":bookNumber," +
            ":bookName," +
            ":fileName," +
            ":monthNumber," +
            ":timetable" +
            ")"
    )
    void create_XlsLoadSettingsFiles_All12(
            String itemName,
            LocalDate dateItemName,
            String armName,
            String armLink,
            String officerFor,
            int rubricNumber,
            String rubricName,
            int bookNumber,
            String bookName,
            String fileName,
            int monthNumber,
            String timetable
    );

    @Operation(summary = "Get", description = "create_XlsLoadSettingsFiles_All18")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into XlsLoadSettingsFiles (" +
            "item_name," +
            "date_item_name," +
            "arm_name," +
            "arm_link," +
            "officer_for," +
            "rubric_number," +
            "rubric_name," +
            "book_number," +
            "book_name," +
            "file_name," +
            "month_number," +
            "timetable," +
            "type_recipient," +
            "name_recipient," +
            "fio_upload," +
            "datetime_upload," +
            "system_rubric_name," +
            "system_file_name" +
            ") " +
            "VALUES (" +
            ":itemName," +
            ":dateItemName," +
            ":armName," +
            ":armLink," +
            ":officerFor," +
            ":rubricNumber," +
            ":rubricName," +
            ":bookNumber," +
            ":bookName," +
            ":fileName," +
            ":monthNumber," +
            ":timetable," +
            ":typeRecipient," +
            ":nameRecipient," +
            ":fioUpload," +
            ":datetimeUpload," +
            ":systemRubricName," +
            ":systemFileName" +
            ")"
    )
    void create_XlsLoadSettingsFiles_All18(
            String itemName,
            LocalDate dateItemName,
            String armName,
            String armLink,
            String officerFor,
            int rubricNumber,
            String rubricName,
            int bookNumber,
            String bookName,
            String fileName,
            int monthNumber,
            String timetable,
            String typeRecipient,
            String nameRecipient,
            String fioUpload,
            LocalDateTime datetimeUpload,
            String systemRubricName,
            String systemFileName
    );

    //Update
}
