package com.web.curation.team.challenger;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.Team;
import com.web.curation.team.TeamDao;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.challenge.TeamChallengeDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamChallengerService {

	private TeamChallengerDao teamChallengerDao;
	private TeamChallengeDao teamChallengeDao;
	private MemberDao memberDao;
	private JoinTeamDao joinTeamDao;
	private TeamDao teamDao;


	public List<TeamChallenger> getTeamChallengerList(@Valid int memberId) {

		Member member = memberDao.findById(memberId).get();
		List<TeamChallenger> teamChallengers = teamChallengerDao.findTeamChallengerByMember(member);
		return teamChallengers;
	}


}
