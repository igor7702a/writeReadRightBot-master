package ru.taksebe.telegram.writeRead.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "materials")
public class MaterialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter
    @Setter
    private String file_section_synonym;

    @Column
    @Getter
    @Setter
    private String file_synonym;

    @Column
    @Getter
    @Setter
    private String file_name;

    @Column
    @Getter
    @Setter
    private String file_extension;

    @Column
    @Getter
    @Setter
    private LocalDate file_date_upload;

    @Column
    @Getter
    @Setter
    private LocalTime file_time_upload;

    @Column
    @Getter
    @Setter
    private String file_type_upload;

    @Column
    @Getter
    @Setter
    private String file_username_upload;

    @Column
    @Getter
    @Setter
    private String file_path;

    @Column
    @Getter
    @Setter
    private String file_type_function;

    @Column
    @Getter
    @Setter
    private LocalDateTime file_datetime_upload;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "id=" + id +
                ", file_section_synonym='" + file_section_synonym + '\'' +
                ", file_synonym='" + file_synonym + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_extension='" + file_extension + '\'' +
                ", file_date_upload='" + file_date_upload + '\'' +
                ", file_time_upload='" + file_time_upload + '\'' +
                ", file_type_upload='" + file_type_upload + '\'' +
                ", file_username_upload='" + file_username_upload + '\'' +
                ", file_path='" + file_path + '\'' +
                ", file_type_function='" + file_type_function + '\'' +
                ", file_datetime_upload='" + file_datetime_upload + '\'' +
                '}';
    }

}

//++  Id
//++  file_section_synonym
//++  file_synonym
//++  file_name
//++  file_extension
//++  file_date_upload
//++  file_time_upload
//++  file_type_upload
//++  file_username_upload
//++  file_path
//++  file_type_function
//++  file_datetime_upload
