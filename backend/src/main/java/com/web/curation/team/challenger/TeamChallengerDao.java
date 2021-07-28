package com.web.curation.team.challenger;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.member.Member;
import com.web.curation.team.TeamDto;
import com.web.curation.team.challenge.TeamChallenge;



public interface TeamChallengerDao extends JpaRepository<TeamChallenger, Integer>{

	TeamChallenger findTeamChallengerByTeamChallengeAndMember(TeamChallenge teamChallenge, Member member);
}
