package com.web.curation.service;

import java.util.Optional;

import com.web.curation.model.Member;

public interface MemberService {
	Optional<Member> checkEmail(String email);
	Member registMember(Member member);
}
