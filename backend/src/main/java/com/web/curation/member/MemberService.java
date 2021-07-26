package com.web.curation.member;

import java.util.Optional;


public interface MemberService {
	Optional<Member> checkEmail(String email);
	Member registMember(Member member);
	Optional<Member> getUser(String email);
	Optional<Member> getUser(String email, String password);
	void certifyMemberByEmail(String email);
	Optional<Member> getMemberByEmail(String email);
}
