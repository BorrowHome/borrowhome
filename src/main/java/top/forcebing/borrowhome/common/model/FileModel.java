package top.forcebing.borrowhome.common.model;

import lombok.Data;

import javax.persistence.*;

import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="file")
@Data
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;

    private String fileName;

    private String contentType;

    private String storePath;

    private long size;

    private long userId;

    private Date uploadDate;

    private long downloadCount;

    public FileModel(String fileName, String contentType, String storePath, long size, long userId) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.storePath = storePath;
        this.size = size;
        this.userId = userId;
        this.uploadDate = new Date();
        this.downloadCount = 0;
    }
}