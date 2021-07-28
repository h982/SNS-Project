package com.web.curation.feed;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.member.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Feed {

	@Id
	private FeedId feedId;
//	
//	@ManyToOne
//	@JoinColumn(name = "TEAM_CHALLENGE_ID")
//	private int team_challenge_id;
	
	@Column(name = "team_name", nullable = false, length = 45)
	private String teamName;
	
	@Column(nullable = false, length = 255)
	private String contents;
	
	@Column(nullable = false, length = 45)
	private String writer;
	
	@Column(name="write_date", insertable = false, updatable = false)
	private LocalDateTime writeDate;
	
	@Column(name = "filepath", nullable = false, length = 255)
	private String filepath;

}
