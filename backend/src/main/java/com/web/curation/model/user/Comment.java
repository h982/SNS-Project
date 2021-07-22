package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;

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
	@Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private int parentId;
	
	@ManyToOne
	@JoinColumn(name = "FEED_ID")
	private int feedId;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private int memberId;
	
	@Column(nullable = false, length = 255)
	private String contents;
	
	@Column(nullable = false, name="class")
	private int className;
	
	@Column(nullable = false)
	private int order;
	
}
