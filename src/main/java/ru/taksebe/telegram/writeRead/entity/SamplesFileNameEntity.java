package ru.taksebe.telegram.writeRead.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "samples_filename")

public class SamplesFileNameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter
    @Setter
    private String rubric_book_number;

    @Column
    @Getter
    @Setter
    private String system_rubric_name;

    @Column
    @Getter
    @Setter
    private String system_file_name;

    @Column
    @Getter
    @Setter
    private String full_file_name;

    @Column
    @Getter
    @Setter
    private String mid_file_name;

    @Column
    @Getter
    @Setter
    private String short_file_name;

    @Column
    @Getter
    @Setter
    private LocalDateTime date_setting;

    @Column
    @Getter
    @Setter
    private String period1;

    @Column
    @Getter
    @Setter
    private String period2;

    @Column
    @Getter
    @Setter
    private String period3;

    @Column
    @Getter
    @Setter
    private String period4;

    @Column
    @Getter
    @Setter
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

//        id SERIAL PRIMARY KEY,
//        rubric_book_number  VARCHAR(15),
//        system_rubric_name VARCHAR(100),
//        system_file_name VARCHAR(100),
//        full_file_name VARCHAR(250),
//        mid_file_name VARCHAR(150),
//        short_file_name VARCHAR(150),
//        date_setting timestamp,
//        period1 name VARCHAR(50),
//        period2 name VARCHAR(50),
//        period3 name VARCHAR(50),
//        period4 name VARCHAR(50),
//        responsible VARCHAR(100)