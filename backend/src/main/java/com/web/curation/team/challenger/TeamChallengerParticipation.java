package com.web.curation.team.challenger;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.web.curation.member.Member;
import com.web.curation.team.TeamDto;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.challenge.TeamChallengeUpdateRequest;
import com.web.curation.team.challenge.TeamChallengeUpdateRequest.TeamChallengeUpdateRequestBuilder;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamChallengerParticipation implements Serializable{
	
	@ApiModelProperty(required = true)
	@NotNull
	private int memberId;
	
	@ApiModelProperty(required = true)
	@NotNull
	private int teamChallengeId;
}
