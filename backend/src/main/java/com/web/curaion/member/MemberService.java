package com.web.curaion.member;

import java.util.Optional;

public interface MemberService {
	Optional<Member> checkEmail(String email);
	Member registMember(Member member);
	Optional<Member> getUser(String email, String password);
}
