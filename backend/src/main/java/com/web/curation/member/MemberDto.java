package com.web.curation.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
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
	private Boolean authenticated;
}
