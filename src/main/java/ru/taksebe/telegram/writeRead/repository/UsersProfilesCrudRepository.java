package ru.taksebe.telegram.writeRead.repository;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.taksebe.telegram.writeRead.entity.UsersProfilesEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface UsersProfilesCrudRepository extends JpaRepository<UsersProfilesEntity, Long> {

    @Operation(summary = "Get", description = "findAllFromUsersProfiles")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM users_profiles", nativeQuery = true)
    List<UsersProfilesEntity> findAllFromUsersProfiles();

    @Operation(summary = "Get", description = "findAllFromUsersProfilesById")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM users_profiles WHERE id=:id", nativeQuery = true)
    List<UsersProfilesEntity> findAllFromUsersProfilesById(long id);

    @Operation(summary = "Get", description = "findAllFromUsersProfilesBy4Test")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM users_profiles " +
            "WHERE (" +
            "access_type = 'LoadAndSave'" +
            "and tg_user = 'TarasovIY'" +
            "and system_rubric_name = 'наццели'" +
            "and system_file_name = 'СпрДостНЦР'" +
            ") " +
            "ORDER BY date_setting DESC " +
            "LIMIT 1",
            nativeQuery = true)
    List<UsersProfilesEntity> findAllFromUsersProfilesBy4Test();

    @Operation(summary = "Get", description = "findAllFromUsersProfilesBy4Param")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM users_profiles " +
            "WHERE (" +
            "access_type = 'LoadAndSave' " +
            "and tg_user = :tgUser " +
            "and system_rubric_name = :systemRubricName " +
            "and system_file_name = :systemFilename " +
            ") " +
            "ORDER BY date_setting DESC " +
            "LIMIT 1",
            nativeQuery = true)
    List<UsersProfilesEntity> findAllFromUsersProfilesBy4Param(String tgUser, String systemRubricName, String systemFilename);
//    SELECT * FROM users_profiles
//    WHERE
//            (access_type = 'LoadAndSave'
//                    and tg_user = 'TarasovIY'
//                    and system_rubric_name = 'наццели'
//                    and system_file_name = 'СпрДостНЦР'
//            )
//    ORDER BY date_setting DESC
//    LIMIT 1

    @Operation(summary = "Get", description = "findAllFromUsersProfilesBy4Param")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM users_profiles " +
            "WHERE (" +
            "access_type = :accessType  " +
            "and tg_user = :tgUser " +
            "and system_rubric_name = :systemRubricName " +
            "and system_file_name = :systemFilename " +
            ") " +
            "ORDER BY date_setting DESC " +
            "LIMIT 1",
            nativeQuery = true)
    List<UsersProfilesEntity> findAllFromUsersProfilesBy4Param(String accessType, String tgUser, String systemRubricName, String systemFilename);

    @Operation(summary = "Get", description = "findAllFromUsersProfilesBy2Param")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM users_profiles " +
            "WHERE (" +
            "access_type = :accessType  " +
            "and tg_user = :tgUser " +
            ") " +
            "ORDER BY date_setting DESC " +
            "LIMIT 1",
            nativeQuery = true)
    List<UsersProfilesEntity> findAllFromUsersProfilesBy2Param(String accessType, String tgUser);

    @Operation(summary = "Get", description = "findAllFromUsersProfilesBy3Param")
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM users_profiles " +
            "WHERE (" +
            "access_type = :accessType  " +
            "and tg_user = :tgUser " +
            "and full_file_name = :fullFileName " +
            ") " +
            "ORDER BY date_setting DESC " +
            "LIMIT 1",
            nativeQuery = true)
    List<UsersProfilesEntity> findAllFromUsersProfilesBy3Param(String accessType, String tgUser, String fullFileName);

    @Operation(summary = "Delete", description = "deleteVoidWhereIdParametr")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from users_profiles where id=:id")
    void deleteVoidWhereIdParametr(long id);

    @Operation(summary = "Create", description = "create_UserProfiles_All8")
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into users_profiles (" +
            "rubric_book_number," +
            "system_rubric_name," +
            "system_file_name," +
            "full_file_name," +
            "access_type," +
            "tg_user," +
            "date_setting," +
            "responsible," +
            "user_fio" +
            ") " +
            "VALUES (" +
            ":rubricBookNumber," +
            ":systemRubricName," +
            ":systemFileName," +
            ":fullFileName," +
            ":accessType," +
            ":tgUser," +
            ":dateSetting," +
            ":responsible," +
            ":userFio" +
            ")"
    )
    void create_UserProfiles_All8(
            String rubricBookNumber,
            String systemRubricName,
            String systemFileName,
            String fullFileName,
            String accessType,
            String tgUser,
            LocalDateTime dateSetting,
            String responsible,
            String userFio
    );

    //Update
}
