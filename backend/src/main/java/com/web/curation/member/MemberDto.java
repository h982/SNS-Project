package com.web.curation.member;

import java.time.LocalDateTime;

import com.web.curation.files.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
	private LocalDateTime createDate;
	private Boolean authenticated;
	private MultipartFile image;
	private Photo photo;
}
