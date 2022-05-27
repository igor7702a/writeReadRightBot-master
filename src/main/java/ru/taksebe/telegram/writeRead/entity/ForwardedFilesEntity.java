package ru.taksebe.telegram.writeRead.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "forwarded_files")
@Data
@Schema(description = "Лог пересланных файлов")
public class ForwardedFilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    private AddressEntity addressEntity;

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
    @Schema(description = "Год", accessMode = Schema.AccessMode.READ_WRITE)
    private int st_year;

    @Column
    @Getter
    @Setter
    @Schema(description = "Период рассылки", accessMode = Schema.AccessMode.READ_WRITE)
    private String st_period;

    @Column
    @Getter
    @Setter
    @Schema(description = "Номер периода рассылки", accessMode = Schema.AccessMode.READ_WRITE)
    private String number_period;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата пересылки файла", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime delivery_date;

    @Column
    @Getter
    @Setter
    @Schema(description = "Результат пересылки файла", accessMode = Schema.AccessMode.READ_WRITE)
    private String harvest;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип адреса пересылки", accessMode = Schema.AccessMode.READ_WRITE)
    private String type_address;

    @Column
    @Getter
    @Setter
    @Schema(description = "Содержание адреса пересылки", accessMode = Schema.AccessMode.READ_WRITE)
    private String matter_address;

    @Column
    @Getter
    @Setter
    @Schema(description = "Описание адреса пересылки", accessMode = Schema.AccessMode.READ_WRITE)
    private String description_address;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата загрузки", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime datetime_upload;

    @Column
    @Getter
    @Setter
    @Schema(description = "Ответственный", accessMode = Schema.AccessMode.READ_WRITE)
    private String responsible;

    @Column
    @Getter
    @Setter
    @Schema(description = "Комментарий", accessMode = Schema.AccessMode.READ_WRITE)
    private String comment;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "forwarded_files{" +
                "id=" + id +
                ", rubric_book_number='" + rubric_book_number + '\'' +
                ", system_rubric_name='" + system_rubric_name + '\'' +
                ", system_file_name='" + system_file_name + '\'' +
                ", full_file_name='" + full_file_name + '\'' +
                ", st_year='" + st_year + '\'' +
                ", st_period='" + st_period + '\'' +
                ", number_period='" + number_period + '\'' +
                ", delivery_date='" + delivery_date + '\'' +
                ", harvest='" + harvest + '\'' +
                ", type_address='" + type_address + '\'' +
                ", matter_address='" + matter_address + '\'' +
                ", description_address='" + description_address + '\'' +
                ", datetime_upload='" + datetime_upload + '\'' +
                ", responsible='" + responsible + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

}

