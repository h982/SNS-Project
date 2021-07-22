package com.web.curation.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberId;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String address;
	private String addressDetail;
	private String zonecode;
	private String sex;
	private int point;
	private String mbti;
	@Column(insertable = false, updatable = false)
	private LocalDateTime createDate;
	private String authentication;

}
