package com.web.curation.member;

import java.util.Optional;

import com.web.curation.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<Member, String> {
	public Optional<Member> getMemberByEmail(String email);
	public Member getMemberByEmailAndPassword(String email, String password);
}