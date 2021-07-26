package com.web.curation.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.curation.member.Member;
import com.web.curation.sport.SportDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(builderMethodName = "TeamDtoBuilder")
public class TeamDto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private long teamId;

    private String name;

    private String introduction;

    private String leader;

    @Column(name = "member_count")
    private int memberCount;

    @Column(name = "img_path")
    private String imgPath;

    @JsonIgnore
    private LocalDateTime createDate;

    //참조키
    @OneToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_id")
    private long leaderId;

    @OneToOne(targetEntity = SportDto.class)
    @JoinColumn(name = "sport_id")
    private long sportId;

    public static TeamDtoBuilder builder(TeamDto teamDto){
        return TeamDtoBuilder()
                .name(teamDto.getName())
                .introduction(teamDto.getIntroduction())
                .leader(teamDto.getLeader())
                .imgPath(teamDto.getImgPath())
                .leaderId(teamDto.getLeaderId())
                .sportId(teamDto.getSportId());
    }


}
