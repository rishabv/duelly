package com.duelly.entities;

import jakarta.persistence.*;

@Entity
@Table(name="media_files")
public class MediaFile extends BaseEntity {
    private String fileName = "";
    private String fileType;
    private String url;
    @Column(name = "size_type")
    private Long size;
}
