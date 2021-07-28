package com.web.curation.feed.like;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.feed.Feed;
import com.web.curation.member.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedLike {
	
	@Id
	@Column(name = "FEEDLIKE_ID")
	private int feedlikeId;
	
	@ManyToOne
	@JoinColumn(name = "FEED_ID")
	private Feed feed;
	
	@Column(name = "feed_like")
	private int feedLike;
	
}
