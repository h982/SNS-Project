package com.web.curation.member;

import java.util.Optional;

import com.web.curation.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberDao extends JpaRepository<Member, String> {
	public Optional<Member> getMemberByEmail(String email);
	public Optional<Member> getMemberByEmailAndPassword(String email, String password);
	
	@Modifying
    @Query(value = "update Member set authentication = 'authentication' where email = :email", nativeQuery = true)
    @Transactional
    int updateAuthentication(@Param("email") String email);

	public Member getMemberByEmailAndPassword(String email, String password);
}