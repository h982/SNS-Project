package com.web.curation.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;

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
	@ManyToOne
	@JoinColumn(name = "member_id")
	@Column(name = "member_id")
	private int memberId;

	@Id
	@ManyToOne
	@JoinColumn(name = "feed_id")
	@Column(name = "feed_id")
	private int feedId;
	
	@Column(name = "feed_like")
	private int feedLike;
	
}
