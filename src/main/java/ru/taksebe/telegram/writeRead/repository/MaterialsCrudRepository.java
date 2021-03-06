package ru.taksebe.telegram.writeRead.repository;

import io.swagger.v3.oas.annotations.Operation;
import ru.taksebe.telegram.writeRead.entity.MaterialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MaterialsCrudRepository extends JpaRepository<MaterialsEntity, Long> {

    @Operation(summary = "Get", description = "findAllFromMaterials")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM materials", nativeQuery = true)
    List<MaterialsEntity> findAllFromMaterials();

    @Operation(summary = "Get", description = "findAllFromMaterialsById")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM materials WHERE id=:id", nativeQuery = true)
    List<MaterialsEntity> findAllFromMaterialsById(long id);

    @Operation(summary = "Delete", description = "deleteVoidWhereIdParametr")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from materials where id=:id")
    void deleteVoidWhereIdParametr(long id);

    @Operation(summary = "Create", description = "create_Materials_All2")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into materials (" +
            "file_section_synonym," +
            "file_synonym" +
            ") " +
            "VALUES (" +
            ":fileSectionSynonym," +
            ":fileSynonym" +
            ")"
    )
    void create_Materials_All2(
            String fileSectionSynonym,
            String fileSynonym
    );

    @Operation(summary = "Create", description = "create_Materials_All4")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into materials (" +
            "file_section_synonym," +
            "file_synonym," +
            "file_name," +
            "file_extension" +
            ") " +
            "VALUES (" +
            ":fileSectionSynonym," +
            ":fileSynonym," +
            ":fileName," +
            ":fileExtension" +
            ")"
    )
    void create_Materials_All4(
            String fileSectionSynonym,
            String fileSynonym,
            String fileName,
            String fileExtension
    );

    @Operation(summary = "Create", description = "create_Materials_All5")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into materials (" +
            "file_section_synonym," +
            "file_synonym," +
            "file_name," +
            "file_extension," +
            "file_date_upload" +
            ") " +
            "VALUES (" +
            ":fileSectionSynonym," +
            ":fileSynonym," +
            ":fileName," +
            ":fileExtension," +
            ":fileDateUpload" +
            ")"
    )
    void create_Materials_All5(
            String fileSectionSynonym,
            String fileSynonym,
            String fileName,
            String fileExtension,
            LocalDate fileDateUpload
    );

    @Operation(summary = "Create", description = "create_Materials_All7")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into materials (" +
            "file_section_synonym," +
            "file_synonym," +
            "file_name," +
            "file_extension," +
            "file_date_upload," +
            "file_time_upload," +
            "file_type_upload" +
            ") " +
            "VALUES (" +
            ":fileSectionSynonym," +
            ":fileSynonym," +
            ":fileName," +
            ":fileExtension," +
            ":fileDateUpload," +
            ":fileTimeUpload," +
            ":fileTypeUpload" +
            ")"
    )
    void create_Materials_All7(
            String fileSectionSynonym,
            String fileSynonym,
            String fileName,
            String fileExtension,
            LocalDate fileDateUpload,
            LocalTime fileTimeUpload,
            String fileTypeUpload
    );

    @Operation(summary = "Create", description = "create_Materials_All11")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into materials (" +
            "file_section_synonym," +
            "file_synonym," +
            "file_name," +
            "file_extension," +
            "file_date_upload," +
            "file_time_upload," +
            "file_type_upload," +
            "file_username_upload," +
            "file_path," +
            "file_type_function," +
            "file_datetime_upload" +
            ") " +
            "VALUES (" +
            ":fileSectionSynonym," +
            ":fileSynonym," +
            ":fileName," +
            ":fileExtension," +
            ":fileDateUpload," +
            ":fileTimeUpload," +
            ":fileTypeUpload," +
            ":fileUsernameUpload," +
            ":filePath," +
            ":fileTypeFunction," +
            ":fileDatetimeUpload" +
            ")"
    )
    void create_Materials_All11(
            String fileSectionSynonym,
            String fileSynonym,
            String fileName,
            String fileExtension,
            LocalDate fileDateUpload,
            LocalTime fileTimeUpload,
            String fileTypeUpload,
            String fileUsernameUpload,
            String filePath,
            String fileTypeFunction,
            LocalDateTime fileDatetimeUpload
    );
    //Update
}

