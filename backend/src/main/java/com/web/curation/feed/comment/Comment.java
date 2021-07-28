package com.web.curation.feed.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

	@Id
	@Column(name="COMMANT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Comment parent;
	
	@ManyToOne
	@JoinColumn(name = "FEED_ID")
	private Feed feed;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", insertable = false, updatable = false)
	private Member member;
	
	@Column(nullable = false, length = 255)
	private String contents;
	
	@Column(nullable = false, name="class")
	private int className;
	
	@Column(nullable = false)
	private int order;
	
	@Column(insertable= false, updatable = false)
	private LocalDateTime create_date;
	
}
