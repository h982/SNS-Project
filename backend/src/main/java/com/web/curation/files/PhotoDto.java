package com.web.curation.files;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.curation.feed.Feed;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Photo")
public class PhotoDto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer photoId;

    @Column(name = "original_filename")
    private String originalName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    private String imageName;

    private String imageExtension;

    @JsonIgnore
    @JoinColumn(name = "feed_id")
    @ManyToOne
    private Feed feed;


    @Builder
    public PhotoDto(String originalName, String filePath, Long fileSize,String imageName,String imageExtension) {
        this.originalName = originalName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.imageName = imageName;
        this.imageExtension = imageExtension;
    }
}