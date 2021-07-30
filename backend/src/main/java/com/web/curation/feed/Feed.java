package com.web.curation.feed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.files.PhotoDto;
import com.web.curation.member.Member;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.join.JoinTeam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Feed {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedId;
	
	@ManyToOne
	@JoinColumn(name = "TEAMCHALLENGE_ID")
	private TeamChallenge teamchallenge;

	@ManyToOne
	@JoinColumn(name = "JOINTEAM_ID")
	private JoinTeam joinTeam;
	
	@Column(name = "team_name", nullable = false, length = 45)
	private String teamName;
	
	@Column(nullable = false, length = 255)
	private String contents;
	
	@Column(nullable = false, length = 45)
	private String writer;
	
	@Column(name="write_date", insertable = false, updatable = false)
	private LocalDateTime writeDate;

	@OneToMany
	@JoinColumn(name = "feed_id")
	private List<PhotoDto> photos = new ArrayList<>();

}
