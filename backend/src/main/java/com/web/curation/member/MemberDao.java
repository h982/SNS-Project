package com.web.curation.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member, String> {
	public Optional<Member> getMemberByEmail(String email);
}