package ru.taksebe.telegram.writeRead.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class DateFile {

    private Path pathFile;

    public Path getpathFile() {
        return pathFile;
    }

    public void setpathFile(Path pathFile) {
        this.pathFile = pathFile;
    }

    @Getter
    @Setter
    private File byteFile;

    @Getter
    @Setter
    private FileTime dataCreated;

    public DateFile(Path pathFile, File byteFile, FileTime dataCreated) {

        this.pathFile = pathFile;
        this.byteFile = byteFile;
        this.dataCreated = dataCreated;
    }

}
