package ru.taksebe.telegram.writeRead.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "samples_filename")
@Schema(description = "Описание образцов для загрузки файлов")
public class SamplesFileNameEntity {

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
    @Schema(description = "Среднее имя файла", accessMode = Schema.AccessMode.READ_WRITE)
    private String mid_file_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Короткое имя файла", accessMode = Schema.AccessMode.READ_WRITE)
    private String short_file_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата настройки", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime date_setting;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип периода рассылки 1", accessMode = Schema.AccessMode.READ_WRITE)
    private String period1;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип периода рассылки 2", accessMode = Schema.AccessMode.READ_WRITE)
    private String period2;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип периода рассылки 3", accessMode = Schema.AccessMode.READ_WRITE)
    private String period3;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип периода рассылки 4", accessMode = Schema.AccessMode.READ_WRITE)
    private String period4;

    @Column
    @Getter
    @Setter
    @Schema(description = "Ответственный", accessMode = Schema.AccessMode.READ_WRITE)
    private String responsible;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SamplesFileName{" +
                "id=" + id +
                ", rubric_book_number='" + rubric_book_number + '\'' +
                ", system_rubric_name='" + system_rubric_name + '\'' +
                ", system_file_name='" + system_file_name + '\'' +
                ", full_file_name='" + full_file_name + '\'' +
                ", mid_file_name='" + mid_file_name + '\'' +
                ", short_file_name='" + short_file_name + '\'' +
                ", date_setting='" + date_setting + '\'' +
                ", period1='" + period1 + '\'' +
                ", period2='" + period2 + '\'' +
                ", period3='" + period3 + '\'' +
                ", period4='" + period4 + '\'' +
                ", responsible='" + responsible + '\'' +
                '}';
    }

}