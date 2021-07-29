package com.web.curation.team.challenger;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.member.Member;
import com.web.curation.team.TeamDto;
import com.web.curation.team.challenge.TeamChallenge;
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
public class TeamChallenger {
	@Id
	@Column(name="teamchallenger_id")
	private int teamchallengerId;
	
	@ManyToOne
	@JoinColumn(name = "TEAMCHALLENGE_ID")
	private TeamChallenge teamChallenge;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@Column(name="done")
	private boolean done;
}
