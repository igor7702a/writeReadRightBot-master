package ru.taksebe.telegram.writeRead.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "forwarded_files")
@Data
public class ForwardedFilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    private AddressEntity addressEntity;

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
    private int st_year;

    @Column
    @Getter
    @Setter
    private String st_period;

    @Column
    @Getter
    @Setter
    private String number_period;

    @Column
    @Getter
    @Setter
    private LocalDateTime delivery_date;

    @Column
    @Getter
    @Setter
    private String harvest;

    @Column
    @Getter
    @Setter
    private String type_address;

    @Column
    @Getter
    @Setter
    private String matter_address;

    @Column
    @Getter
    @Setter
    private String description_address;

    @Column
    @Getter
    @Setter
    private LocalDateTime datetime_upload;

    @Column
    @Getter
    @Setter
    private String responsible;

    @Column
    @Getter
    @Setter
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
//    id integer
//    address_id integer
//    rubric_book_number VARCHAR(15);
//    system_rubric_name VARCHAR(100);
//    system_file_name VARCHAR(100);
//    full_file_name VARCHAR(250);
//    st_year VARCHAR(10);
//    st_period VARCHAR(50);
//    number_period VARCHAR(10);
//    delivery_date timestamp;
//    harvest VARCHAR(50);
//    type_address VARCHAR(150);
//    matter_address VARCHAR(150);
//    description_address VARCHAR(150);
//    datetime_upload timestamp;
//    responsible VARCHAR(100)
//    comment VARCHAR(100)

