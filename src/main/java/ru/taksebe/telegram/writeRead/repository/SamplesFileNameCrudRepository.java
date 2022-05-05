package ru.taksebe.telegram.writeRead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.SamplesFileNameEntity;

import java.time.LocalDateTime;
import java.util.List;


public interface SamplesFileNameCrudRepository extends JpaRepository<SamplesFileNameEntity, Long> {

    //Get findAllFromSampleFileName
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSampleFileName();

    //Get findAllFromSamplesFileNameById
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename WHERE id=:id", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameById(long id);

    //Get findAllFromSamplesFileNameByFileNameV1
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename WHERE full_file_name=:fullFileName", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameByFileNameV1(String fullFileName);

    //Get findAllFromSamplesFileNameRangeByDate
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename ORDER BY date_setting DESC", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameRangeByDate();

    //Get findAllFromSamplesFileNameFirst
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename ORDER BY date_setting DESC LIMIT 1", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameFirst();

    //Get findAllFromSamplesFileNameFirstParam1
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename WHERE full_file_name=:fullFileName ORDER BY date_setting DESC LIMIT 1", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameFirstParam1(String fullFileName);

    //Delete
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from samples_filename where id=:id")
    void deleteVoidWhereIdParametr(long id);

    //Create create_SampleFileNames_All13
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into samples_filename (" +
            "rubric_book_number," +
            "system_rubric_name," +
            "system_file_name," +
            "full_file_name," +
            "mid_file_name," +
            "short_file_name," +
            "date_setting," +
            "period1," +
            "period2," +
            "period3," +
            "period4," +
            "responsible" +
            ") " +
            "VALUES (" +
            ":rubricBookNumber," +
            ":systemRubricName," +
            ":systemFileName," +
            ":fullFileName," +
            ":midFileName," +
            ":shortFileName," +
            ":dateSetting," +
            ":period1," +
            ":period2," +
            ":period3," +
            ":period4," +
            ":responsible" +
            ")"
    )

    void create_SampleFileNames_All13(
            String rubricBookNumber,
            String systemRubricName,
            String systemFileName,
            String fullFileName,
            String midFileName,
            String shortFileName,
            LocalDateTime dateSetting,
            String period1,
            String period2,
            String period3,
            String period4,
            String responsible
    );

    //Update
}

//        id SERIAL PRIMARY KEY,
//        rubric_book_number  VARCHAR(15),
//        system_rubric_name VARCHAR(100),
//        system_file_name VARCHAR(100),
//        full_file_name VARCHAR(250),
//        mid_file_name VARCHAR(150),
//        short_file_name VARCHAR(150),
//        date_setting timestamp,
//        period1 name VARCHAR(50),
//        period2 name VARCHAR(50),
//        period3 name VARCHAR(50),
//        period4 name VARCHAR(50),
//        responsible VARCHAR(100)