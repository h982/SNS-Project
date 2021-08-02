package com.web.curation.feed.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
public class CommentDto {

    private int commentId;

    private int parentId;

    @NotNull
    private int feedId;

    @NotNull
    private int memberId;

    @NotNull
    private String contents;

    @NotNull
    private int className;

    @NotNull
    private int order;

}
