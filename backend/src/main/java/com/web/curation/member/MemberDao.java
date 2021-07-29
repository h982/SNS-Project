package com.web.curation.member;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberDao extends JpaRepository<Member, Integer> {
	public Optional<Member> getMemberByEmail(String email);

	public Optional<Member> getMemberByEmailAndPassword(String email, String password);

    @Modifying
    @Query(value = "update Member set authenticated = true where email = :email", nativeQuery = true)
    @Transactional
    int updateAuthentication(@Param("email") String email);
}