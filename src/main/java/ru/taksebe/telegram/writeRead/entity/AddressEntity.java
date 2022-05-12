package ru.taksebe.telegram.writeRead.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
public class AddressEntity {

//    datetime_upload timestamp,
//    responsible VARCHAR(100)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

//    id SERIAL PRIMARY KEY,
//    type_address VARCHAR(150),
//    matter_address VARCHAR(150),
//    description_address VARCHAR(150),
//    datetime_upload timestamp,
//    responsible VARCHAR(100)
