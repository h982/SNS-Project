package com.web.curation.team.challenge;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.web.curation.team.challenge.TeamChallengeCreationRequest.TeamChallengeCreationRequestBuilder;

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
public class TeamChallengeUpdateRequest {
	@ApiModelProperty(required = true)
	@NotNull
	private int teamChallengeId;
	
	
	@ApiModelProperty(required = true)
	@NotNull
	private int teamId;
	
	@ApiModelProperty(required = true)
	@NotNull
	private String title;
	
	@ApiModelProperty(required = true)
	@NotNull
	private String contents;
	
	@ApiModelProperty(required = true)
	@NotNull
	private LocalDateTime startDate;
	
	@ApiModelProperty(required = true)
	@NotNull
	private LocalDateTime endDate;
}
