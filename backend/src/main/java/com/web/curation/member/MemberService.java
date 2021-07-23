package com.web.curation.member;

import java.util.Optional;


public interface MemberService {
	Optional<Member> checkEmail(String email);
	Member registMember(Member member);
	Member getUser(String email, String password);
	Optional<Member> getMemberByEmail(String email);
}
