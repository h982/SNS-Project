package com.web.curation.feed;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.member.Member;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.join.JoinTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Feed {

	@Id
	private int feedId;
	
	@ManyToOne
	@JoinColumn(name = "TEAMCHALLENGE_ID")
	private TeamChallenge teamchallengeId;

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
	
	@Column(name = "filepath", nullable = false, length = 255)
	private String filepath;

}
