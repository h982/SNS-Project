package com.web.curation.team.join;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.member.Member;

public interface JoinTeamDao extends JpaRepository<JoinTeam, Integer>{
	
	public List<JoinTeam> findJoinTeamByMember(Member member);

}
