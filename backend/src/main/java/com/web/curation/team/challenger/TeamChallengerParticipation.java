package com.web.curation.team.challenger;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
