package com.web.curation.team.challenge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.member.Member;
import com.web.curation.team.TeamDto;

public interface TeamChallengeDao extends JpaRepository<TeamChallenge, Integer>{

	public TeamChallenge getTeamChallengeByTeamChallengeId(int teamChallengeId);
	public List<TeamChallenge> findTeamChallengeByTeam(TeamDto team);
}
