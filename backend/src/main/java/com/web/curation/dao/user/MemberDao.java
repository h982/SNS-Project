package com.web.curation.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.model.Member;
import com.web.curation.model.user.User;

public interface MemberDao extends JpaRepository<Member, String> {
	public Optional<Member> getMemberByEmail(String email);
}