package com.web.curation.model.user;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("피드 생성 RequestBody 객체 도메인")
public class FeedCreationRequest {
//    @ApiModelProperty(required = true)
//    @NotNull
//	private int member_id;
//    
//    @ApiModelProperty(required = true)
//    @NotNull	
//	private int team_id;
//    
//    @ApiModelProperty	
//	private int team_challenge_id;
    
    @ApiModelProperty(required = true) 
    @NotNull	
	private String teamName;
    
    @ApiModelProperty(required = true) 
    @NotNull
	private String contents;
    
    @ApiModelProperty(required = true)
    @NotNull
	private String writer;
    
    @ApiModelProperty(required = true)
    @NotNull
	private String filepath;

//	public int getMember_id() {
//		return member_id;
//	}
//
//	public void setMember_id(int member_id) {
//		this.member_id = member_id;
//	}
//
//	public int getTeam_id() {
//		return team_id;
//	}
//
//	public void setTeam_id(int team_id) {
//		this.team_id = team_id;
//	}
//
//	public int getTeam_challenge_id() {
//		return team_challenge_id;
//	}
//
//	public void setTeam_challenge_id(int team_challenge_id) {
//		this.team_challenge_id = team_challenge_id;
//	}


	public String getContents() {
		return contents;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
