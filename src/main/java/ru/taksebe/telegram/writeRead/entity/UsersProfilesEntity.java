package ru.taksebe.telegram.writeRead.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_profiles")
@Schema(description = "Описание прав пользователей")
public class UsersProfilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column
    @Getter
    @Setter
    @Schema(description = "Номер рубрики", accessMode = Schema.AccessMode.READ_WRITE)
    private String rubric_book_number;

    @Column
    @Getter
    @Setter
    @Schema(description = "Системное имя рубрики", accessMode = Schema.AccessMode.READ_WRITE)
    private String system_rubric_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Системное имя файла", accessMode = Schema.AccessMode.READ_WRITE)
    private String system_file_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Полное имя файла", accessMode = Schema.AccessMode.READ_WRITE)
    private String full_file_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип доступа", accessMode = Schema.AccessMode.READ_WRITE)
    private String access_type;

    @Column
    @Getter
    @Setter
    @Schema(description = "Логин телеграм", accessMode = Schema.AccessMode.READ_WRITE)
    private String tg_user;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата установки", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime date_setting;

    @Column
    @Getter
    @Setter
    @Schema(description = "Ответственный", accessMode = Schema.AccessMode.READ_WRITE)
    private String responsible;

    @Column
    @Getter
    @Setter
    @Schema(description = "ФИО пользователя", accessMode = Schema.AccessMode.READ_WRITE)
    private String user_fio;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UsersProfilesEntity{" +
                "id=" + id +
                ", rubric_book_number='" + rubric_book_number + '\'' +
                ", system_rubric_name='" + system_rubric_name + '\'' +
                ", system_file_name='" + system_file_name + '\'' +
                ", full_file_name='" + full_file_name + '\'' +
                ", access_type='" + access_type + '\'' +
                ", tg_user='" + tg_user + '\'' +
                ", date_setting='" + date_setting + '\'' +
                ", responsible='" + responsible + '\'' +
                ", user_fio='" + user_fio + '\'' +
                '}';
    }

}
