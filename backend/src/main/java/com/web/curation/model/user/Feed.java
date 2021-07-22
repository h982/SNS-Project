package com.web.curation.model.user;

import java.time.LocalDateTime;

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
public class Feed {

	@Id
	@Column(name="feed_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedId;
	
//	@Id
//	@ManyToOne
//	@JoinColumn(name = "TEAM_ID")
//	private int team_id;
//	
//	@Id
//	@ManyToOne
//	@JoinColumn(name = "MEMBER_ID")
//	private int member_id;
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
	
	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public LocalDateTime getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(LocalDateTime writeDate) {
		this.writeDate = writeDate;
	}

	public String getContents() {
		return contents;
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




//	public int getTeam_id() {
//		return team_id;
//	}
//
//	public void setTeam_id(int team_id) {
//		this.team_id = team_id;
//	}
//
//	public int getMember_id() {
//		return member_id;
//	}
//
//	public void setMember_id(int member_id) {
//		this.member_id = member_id;
//	}
//
//	public int getTeam_challenge_id() {
//		return team_challenge_id;
//	}
//
//	public void setTeam_challenge_id(int team_challenge_id) {
//		this.team_challenge_id = team_challenge_id;
//	}

}
