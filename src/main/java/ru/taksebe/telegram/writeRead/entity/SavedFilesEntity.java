package ru.taksebe.telegram.writeRead.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "saved_files")
@Schema(description = "Лог загруженных файлов")
public class SavedFilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column
    @Getter
    @Setter
    @Schema(description = "Имя файла", accessMode = Schema.AccessMode.READ_WRITE)
    private String file_name;

    @Column
    @Getter
    @Setter
    @Schema(description = "Путь к файлу", accessMode = Schema.AccessMode.READ_WRITE)
    private String file_path;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата записи", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime save_date;

    @Column
    @Getter
    @Setter
    @Schema(description = "Ответственный", accessMode = Schema.AccessMode.READ_WRITE)
    private String responsible;

    @Column
    @Getter
    @Setter
    @Schema(description = "Описание", accessMode = Schema.AccessMode.READ_WRITE)
    private String decription;

    @Column
    @Getter
    @Setter
    @Schema(description = "Результат", accessMode = Schema.AccessMode.READ_WRITE)
    private String harvest;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SavedFiles{" +
                "id=" + id +
                ", file_name='" + file_name + '\'' +
                ", file_path='" + file_path + '\'' +
                ", save_date='" + save_date + '\'' +
                ", responsible='" + responsible + '\'' +
                ", decription='" + decription + '\'' +
                ", harvest='" + harvest + '\'' +
                '}';
    }

}