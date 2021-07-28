package com.web.curation.request;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.member.Member;
import com.web.curation.team.TeamDto;

public interface RequestDao extends JpaRepository<Request, Integer>{
	public Optional<Request> getRequestByTeamAndMember(TeamDto team, Member member);
	public List<Request> findAllByTeam(TeamDto team);
//	@Query(value = "select * from Request where team_id = :teamId and member_id = :memberId", nativeQuery = true)
//	public Optional<Request> getRequestByTeamIdAndMemberId(long teamId, int memberId);
//	public List<Request> getRequestByTeamId(int teamId);
}
