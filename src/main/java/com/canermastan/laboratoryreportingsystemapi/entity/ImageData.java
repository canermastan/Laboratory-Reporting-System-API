package com.canermastan.laboratoryreportingsystemapi.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;
import java.util.Set;

@Entity
@Table(name = "images")
public class ImageData {
    public ImageData() {
    }

    public ImageData(String id, String name, String type, byte[] data, Report report) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
        this.report = report;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    @Lob
    @JdbcTypeCode(Types.LONGVARBINARY)
    private byte[] data;


    @OneToOne(mappedBy = "reportImageData")
    private Report report;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
