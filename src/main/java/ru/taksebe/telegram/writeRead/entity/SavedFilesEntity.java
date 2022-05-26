package ru.taksebe.telegram.writeRead.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "saved_files")
public class SavedFilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter
    @Setter
    private String file_name;

    @Column
    @Getter
    @Setter
    private String file_path;

    @Column
    @Getter
    @Setter
    private LocalDateTime save_date;

    @Column
    @Getter
    @Setter
    private String responsible;

    @Column
    @Getter
    @Setter
    private String decription;

    @Column
    @Getter
    @Setter
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

// Id
// file_name VARCHAR(200),
// file_path VARCHAR(250),
// save_date TIMESTAMP,
// responsible VARCHAR(100),
// decription VARCHAR(250),
// harvest VARCHAR(50)

// Id Long
// file_name String,
// file_path VARCHAR(250),
// save_date TIMESTAMP,
// responsible VARCHAR(100),
// decription VARCHAR(250),
// harvest VARCHAR(50)