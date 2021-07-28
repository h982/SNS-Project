package com.web.curation.team.challenge;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.feed.Feed;
import com.web.curation.team.TeamDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamChallenge {
	
	@Id
	@Column(name="teamchallenge_id")
	private int teamChallengeId;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private TeamDto team;
	
	@Column(name = "TITLE", length = 128)
	private String title;
	
	@Column(nullable = false, length = 255)
	private String contents;
	
	@Column(nullable = false)
	int status;
	
	@Column(name = "member_count")
	int memberCount;
	
	@Column(name = "goal_count")
	int goalCount;
	
	@Column(name="start_date")
	private LocalDateTime startDate;
	
	@Column(name="end_date")
	private LocalDateTime endDate;
}