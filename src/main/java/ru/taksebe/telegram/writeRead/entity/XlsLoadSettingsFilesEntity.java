package ru.taksebe.telegram.writeRead.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "XlsLoadSettingsFilesEntity")

public class XlsLoadSettingsFilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter
    @Setter
    private String item_name;

    @Column
    @Getter
    @Setter
    private LocalDate date_item_name;

    @Column
    @Getter
    @Setter
    private String arm_name;

    @Column
    @Getter
    @Setter
    private String arm_link;

    @Column
    @Getter
    @Setter
    private String officer_for;

    @Column
    @Getter
    @Setter
    private int rubric_number;

    @Column
    @Getter
    @Setter
    private String rubric_name;

    @Column
    @Getter
    @Setter
    private int book_number;

    @Column
    @Getter
    @Setter
    private String book_name;

    @Column
    @Getter
    @Setter
    private String file_name;

    @Column
    @Getter
    @Setter
    private int month_number;

    @Column
    @Getter
    @Setter
    private String timetable;

    @Column
    @Getter
    @Setter
    private String type_recipient;

    @Column
    @Getter
    @Setter
    private String name_recipient;

    @Column
    @Getter
    @Setter
    private String fio_upload;

    @Column
    @Getter
    @Setter
    private LocalDateTime datetime_upload;

    @Column
    @Getter
    @Setter
    private String system_rubric_name;

    @Column
    @Getter
    @Setter
    private String system_file_name;

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

//    Id SERIAL PRIMARY KEY,
//++    item_name VARCHAR(200),
//++    date_item_name DATE,
//++    arm_name VARCHAR(200),
//++    arm_link VARCHAR(200),
//++    officer_for VARCHAR(200),
//++    rubric_number INTEGER,
//++    rubric_name VARCHAR(150),
//++    book_number INTEGER,
//++    book_name VARCHAR(200),
//++    file_name VARCHAR(200),
//++    month_number INTEGER,
//++    timetable VARCHAR(100),
//++    type_recipient VARCHAR(100),
//++    name_recipient VARCHAR(200),
//++    fio_upload VARCHAR(200),
//++    datetime_upload TIMESTAMP,
//++    system_rubric_name VARCHAR(100),
//+    system_file_name VARCHAR(100)
