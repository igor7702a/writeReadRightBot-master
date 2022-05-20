package ru.taksebe.telegram.writeRead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.XlsLoadSettingsFilesEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface XlsLoadSettingsFilesCrudRepository extends JpaRepository<XlsLoadSettingsFilesEntity, Long> {

    //Get findAllFromXlsLoadSettingsFiles
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFiles();

    //Get findAllFromXlsLoadSettingsFilesById
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE id=:id", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesById(long id);

    //Get findAllFromXlsLoadSettingsFilesBySystemRubricName
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE system_rubric_name=:systemRubricName", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesBySystemRubricName(String systemRubricName);

    //Get find1FromXlsLoadSettingsFilesBySystemRubricName
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE system_rubric_name=:systemRubricName LIMIT 1", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> find1FromXlsLoadSettingsFilesBySystemRubricName(String systemRubricName);

    //Get findAllFromXlsLoadSettingsFilesByMonth
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE month_number=:monthNumber", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesByMonth(int monthNumber);

    //Get findAllFromXlsLoadSettingsFilesByYear
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles WHERE (date_item_name>=:dateFirst and date_item_name<=:dateEnd)", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesByYear(LocalDate dateFirst, LocalDate dateEnd);

    //Get findAllFromXlsLoadSettingsFilesByYearAndMonth
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM XlsLoadSettingsFiles " +
            "WHERE (" +
            "date_item_name>=:dateFirst " +
            "and date_item_name<=:dateEnd " +
            "and month_number=:monthNumber" +
            ")", nativeQuery = true)
    List<XlsLoadSettingsFilesEntity> findAllFromXlsLoadSettingsFilesByYearAndMonth(LocalDate dateFirst, LocalDate dateEnd, int monthNumber);

    //Get findAllFromXlsLoadSettingsFilesBy4Param
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

    //Get findAllFromXlsLoadSettingsFilesOneRubric
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

    //Get findAllFromXlsLoadSettingsFiles5Param
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

    //Get findAllFromXlsLoadSettingsFiles5Param1Order
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

    //Create create_XlsLoadSettingsFiles_All12
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

    //Create create_XlsLoadSettingsFiles_All18
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

//    Id SERIAL PRIMARY KEY,
//+    item_name VARCHAR(200),
//+    date_item_name DATE,
//+    arm_name VARCHAR(200),
//+    arm_link VARCHAR(200),
//+    officer_for VARCHAR(200),
//+    rubric_number INTEGER,
//+    rubric_name VARCHAR(150),
//+    book_number INTEGER,
//+    book_name VARCHAR(200),
//+    file_name VARCHAR(200),
//+    month_number INTEGER,
//+    timetable VARCHAR(100),
//+    type_recipient VARCHAR(100),
//+    name_recipient VARCHAR(200),
//+    fio_upload VARCHAR(200),
//+    datetime_upload TIMESTAMP,
//+    system_rubric_name VARCHAR(100),
//+    system_file_name VARCHAR(100)
