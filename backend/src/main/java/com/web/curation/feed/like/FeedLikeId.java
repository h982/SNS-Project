package com.web.curation.feed.like;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.web.curation.feed.Feed;
import com.web.curation.member.Member;

import lombok.Data;

@Data
@Embeddable
public class FeedLikeId implements Serializable{

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "FEED_ID"),
		@JoinColumn(name = "MEMBER_ID")
	})
	private Feed feed;
}
