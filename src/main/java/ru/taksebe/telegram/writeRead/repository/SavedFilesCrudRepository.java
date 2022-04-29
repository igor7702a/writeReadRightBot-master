package ru.taksebe.telegram.writeRead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.SavedFilesEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface SavedFilesCrudRepository extends JpaRepository<SavedFilesEntity, Long> {

    //Get
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM saved_files", nativeQuery = true)
    List<SavedFilesEntity> findAllFromSavedFiles();

    //Delete
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from saved_files where id=:id")
    void deleteVoidWhereIdParametr(long id);

    //Create
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into saved_files (" +
            "file_name," +
            "file_path," +
            "save_date," +
            "responsible," +
            "decription," +
            "harvest" +
            ") " +
            "VALUES (" +
            ":fileName," +
            ":filePath," +
            ":saveDate," +
            ":responsible," +
            ":decription," +
            ":harvest" +
            ")"
    )
    void create_SavedFiles_All6(
            String fileName,
            String filePath,
            LocalDateTime saveDate,
            String responsible,
            String decription,
            String harvest
    );

    //Update
}