package ru.taksebe.telegram.writeRead.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Schema(description = "Описание адреса для рассылки")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column
    @Getter
    @Setter
    @Schema(description = "Тип адреса", accessMode = Schema.AccessMode.READ_WRITE)
    private String type_address;

    @Column
    @Getter
    @Setter
    @Schema(description = "Содержание адреса", accessMode = Schema.AccessMode.READ_WRITE)
    private String matter_address;

    @Column
    @Getter
    @Setter
    @Schema(description = "Описание адреса", accessMode = Schema.AccessMode.READ_WRITE)
    private String description_address;

    @Column
    @Getter
    @Setter
    @Schema(description = "Дата загрузки", accessMode = Schema.AccessMode.READ_WRITE)
    private LocalDateTime datetime_upload;

    @Column
    @Getter
    @Setter
    @Schema(description = "Ответственный за загрузку", accessMode = Schema.AccessMode.READ_WRITE)
    private String responsible;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", type_address='" + type_address + '\'' +
                ", matter_address='" + matter_address + '\'' +
                ", description_address='" + description_address + '\'' +
                ", datetime_upload='" + datetime_upload + '\'' +
                ", responsible='" + responsible + '\'' +
                '}';
    }

}
