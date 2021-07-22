package com.web.curation.feed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.web.curation.member.Member;

import lombok.Data;

@Data
@Embeddable
public class FeedId implements Serializable{

	@Column(name="feed_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedId;
	
//	@Id
//	@ManyToOne
//	@JoinColumn(name = "TEAM_ID")
//	private int team_id;
//	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
}
