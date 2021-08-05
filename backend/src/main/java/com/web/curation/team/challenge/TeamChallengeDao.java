package com.web.curation.team.challenge;

import java.util.List;
import java.util.Optional;

import com.web.curation.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.member.Member;
import com.web.curation.team.TeamDto;

public interface TeamChallengeDao extends JpaRepository<TeamChallenge, Integer>{

	List<TeamChallenge> findTeamChallengeByTeam(Team team);
}
