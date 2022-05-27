package ru.taksebe.telegram.writeRead.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "XlsLoadSettingsFilesEntity")
@Schema(description = "Настройка рассылки файлов")
public class XlsLoadSettingsFilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column
    @Getter
    @Setter
    @Schema(description = "Имя пункта", accessMode = Schema.AccessMode.READ_WRITE)
    private String item_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата имени пункта", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDate date_item_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Имя арма", accessMode = Schema.AccessMode.READ_WRITE)
    private String arm_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Ссылка на арм", accessMode = Schema.AccessMode.READ_WRITE)
    private String arm_link;

    @Column
    @Getter
    @Setter
    @Schema(description = "Ответственный за пункт", accessMode = Schema.AccessMode.READ_WRITE)
    private String officer_for;

    @Column
    @Getter
    @Setter
    @Schema(description = "Номер рубрики", accessMode = Schema.AccessMode.READ_WRITE)
    private int rubric_number;

    @Column
    @Getter
    @Setter
    @Schema(description = "Имя рубрики", accessMode = Schema.AccessMode.READ_WRITE)
    private String rubric_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Номер книжки", accessMode = Schema.AccessMode.READ_WRITE)
    private int book_number;

    @Column
    @Getter
    @Setter
    @Schema(description = "Имя книжки", accessMode = Schema.AccessMode.READ_WRITE)
    private String book_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Имя файла", accessMode = Schema.AccessMode.READ_WRITE)
    private String file_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Номер месяца", accessMode = Schema.AccessMode.READ_WRITE)
    private int month_number;

    @Column
    @Getter
    @Setter
    @Schema(description = "Расписание", accessMode = Schema.AccessMode.READ_WRITE)
    private String timetable;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип получателя", accessMode = Schema.AccessMode.READ_WRITE)
    private String type_recipient;

    @Column
    @Getter
    @Setter
    @Schema(description = "Имя получателя", accessMode = Schema.AccessMode.READ_WRITE)
    private String name_recipient;

    @Column
    @Getter
    @Setter
    @Schema(description = "Фамилия загрузившего", accessMode = Schema.AccessMode.READ_WRITE)
    private String fio_upload;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата и время загрузки", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime datetime_upload;

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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "XlsLoadSettingsFilesEntity{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", date_item_name='" + date_item_name + '\'' +
                ", arm_name='" + arm_name + '\'' +
                ", arm_link='" + arm_link + '\'' +
                ", officer_for='" + officer_for + '\'' +
                ", rubric_number='" + rubric_number + '\'' +
                ", rubric_name='" + rubric_name + '\'' +
                ", book_number='" + book_number + '\'' +
                ", book_name='" + book_name + '\'' +
                ", file_name='" + file_name + '\'' +
                ", month_number='" + month_number + '\'' +
                ", timetable='" + timetable + '\'' +
                ", type_recipient='" + type_recipient + '\'' +
                ", name_recipient='" + name_recipient + '\'' +
                ", fio_upload='" + fio_upload + '\'' +
                ", datetime_upload='" + datetime_upload + '\'' +
                ", system_rubric_name='" + system_rubric_name + '\'' +
                ", system_file_name='" + system_file_name + '\'' +
                '}';
    }

}