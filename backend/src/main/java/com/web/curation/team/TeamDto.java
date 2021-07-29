package com.web.curation.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.curation.files.PhotoDto;
import com.web.curation.member.Member;
import com.web.curation.request.Request;
import com.web.curation.sport.SportDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.nio.file.FileStore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Team")
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder(builderMethodName = "TeamDtoBuilder")
public class TeamDto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private long teamId;

    private String name;

    private String introduction;

    private String leader;

    @Column(name = "member_count")
    @ColumnDefault("1")
    @JsonIgnore
    private int memberCount;

    @Column(name = "img_path")
    private String imgPath;

    @JsonIgnore
    @Column(columnDefinition="DATETIME default CURRENT_TIMESTAMP")
    private LocalDateTime createDate;

    //참조키
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    private SportDto sportDto;
    
    public TeamDto(int teamId) {
    	this.teamId = teamId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private PhotoDto photoDto;

}
