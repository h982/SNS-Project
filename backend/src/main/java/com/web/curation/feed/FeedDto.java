package com.web.curation.feed;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@Valid
public class FeedDto {
    private int feedId;

    @ApiModelProperty(required = true)
    private int teamchallengeId;

    @ApiModelProperty(required = true)
    private int joinTeamId;

    @ApiModelProperty(required = true)
    private String teamName;

    @ApiModelProperty(required = true)
    private String contents;

    @ApiModelProperty(required = true)
    private String writer;

    @ApiModelProperty(required = true)
    private List<MultipartFile> multipartFiles;
}