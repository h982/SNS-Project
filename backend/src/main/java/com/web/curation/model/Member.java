package com.web.curation.model;

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

	public int getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public String getZonecode() {
		return zonecode;
	}

	public String getSex() {
		return sex;
	}

	public int getPoint() {
		return point;
	}

	public String getMbti() {
		return mbti;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public String getAuthentication() {
		return authentication;
	}

}
