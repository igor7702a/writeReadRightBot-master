package ru.taksebe.telegram.writeRead.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_profiles")

public class UsersProfilesEntity {

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
    private String access_type;

    @Column
    @Getter
    @Setter
    private String tg_user;

    @Column
    @Getter
    @Setter
    private LocalDateTime date_setting;

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
                '}';
    }

}

// rubric_book_number VARCHAR(15),
// system_rubric_name VARCHAR(100),
// system_file_name VARCHAR(100),
// full_file_name VARCHAR(250),
// access_type VARCHAR(50),
// tg_user VARCHAR(150),
// date_setting timestamp,
// responsible VARCHAR(100)

