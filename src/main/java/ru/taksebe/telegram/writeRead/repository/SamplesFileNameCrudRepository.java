package ru.taksebe.telegram.writeRead.repository;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.SamplesFileNameEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface SamplesFileNameCrudRepository extends JpaRepository<SamplesFileNameEntity, Long> {

    @Operation(summary = "Get", description = "findAllFromSampleFileName")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSampleFileName();

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameById")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename WHERE id=:id", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameById(long id);

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameByFileNameV1")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename WHERE full_file_name=:fullFileName", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameByFileNameV1(String fullFileName);

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameRangeByDate")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename ORDER BY date_setting DESC", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameRangeByDate();

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameFirst")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename ORDER BY date_setting DESC LIMIT 1", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameFirst();

    @Operation(summary = "Get", description = "findAllFromSamplesFileNameFirstParam1")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename WHERE full_file_name=:fullFileName ORDER BY date_setting DESC LIMIT 1", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesFileNameFirstParam1(String fullFileName);

    @Operation(summary = "Get", description = "findAllFromSamplesByRubricNumber")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM samples_filename WHERE rubric_book_number=:rubricNumber ORDER BY date_setting DESC LIMIT 1", nativeQuery = true)
    List<SamplesFileNameEntity> findAllFromSamplesByRubricNumber(String rubricNumber);

    @Operation(summary = "Delete", description = "deleteVoidWhereIdParametr")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from samples_filename where id=:id")
    void deleteVoidWhereIdParametr(long id);

    @Operation(summary = "Create", description = "create_SampleFileNames_All13")
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
