package com.web.curation.feed;

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
//  @ApiModelProperty(required = true)
//  @NotNull
//	private int memberId;
//    
//  @ApiModelProperty(required = true)
//  @NotNull	
//	private int teamId;
//    
//  @ApiModelProperty	
//	private int teamChallengeId;
    
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
}
