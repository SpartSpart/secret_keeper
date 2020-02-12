package ru.spart.passwordkeeper.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "Files")

public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "DOC_ID",nullable = false)
    private long docId;

    @Column(name = "FILE_PATH")
    private String filePath;

    public FileData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
