package com.web.curation.team.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.TeamDao;
import com.web.curation.team.TeamDto;
import com.web.curation.team.challenger.TeamChallenger;
import com.web.curation.team.challenger.TeamChallengerDao;
import com.web.curation.team.challenger.TeamChallengerParticipation;

@Service
@AllArgsConstructor
public class TeamChallengeService {

	private TeamChallengeDao teamChallengeDao;
	private TeamDao teamDao;
	private TeamChallengerDao teamChallengerDao;
	private MemberDao memberDao;
	private JoinTeamDao joinTeamDao;

	public boolean addTeamChallenge(TeamChallengeCreationRequest creationRequest) {

		TeamChallenge teamChallenge = TeamChallenge.builder()
				.contents(creationRequest.getContents())
				.title(creationRequest.getTitle())
				.memberCount(1)
				.status(Status.BEGIN)
				.team(teamDao.findById((long)creationRequest.getTeamId()).get())
				.startDate(creationRequest.getStartDate())
				.endDate(creationRequest.getEndDate())
				.build();
		
		TeamChallenge result = teamChallengeDao.save(teamChallenge);
		System.out.println(result);
		if(result == null) {
			return false;
		}
		return true;
	}
	
	public List<TeamChallenge> updateTeamChallenge(TeamChallengeUpdateRequest updationRequest){
		TeamChallenge teamChallenge = TeamChallenge.builder()
				.teamChallengeId(updationRequest.getTeamChallengeId())
				.contents(updationRequest.getContents())
				.title(updationRequest.getTitle())
				.memberCount(1)
				.status(Status.BEGIN)
				.team(teamDao.getOne(updationRequest.getTeamId()))
				.startDate(updationRequest.getStartDate())
				.endDate(updationRequest.getEndDate())
				.build();
		
		List<TeamChallenge> list = new ArrayList<>();
		TeamChallenge result = teamChallengeDao.save(teamChallenge);
		if(result != null) {
			list = teamChallengeDao.findTeamChallengeByTeam(teamChallenge.getTeam());
		}
		return list;
	}
	
	public List<TeamChallenge> findTeamChallengeByTeam(int teamId){
		TeamDto team = teamDao.getOne((long)teamId);
		List<TeamChallenge> list = teamChallengeDao.findTeamChallengeByTeam(teamDao.getOne((long)teamId));
		
		return list;
	}

//	 join team 이 구현되야함
	public List<TeamChallenge> getTeamChallengeList(@Valid int memberId) {
		Member member = memberDao.findById(memberId).get();
		List<JoinTeam> joinTeams = joinTeamDao.findJoinTeamByMember(member);

		List<TeamChallenge> list = new ArrayList<>();
		for(JoinTeam join: joinTeams){
			List<TeamChallenge> tc = teamChallengeDao.findTeamChallengeByTeam(join.getTeam());
			list.addAll(tc);
		}

		return list;
	}

	public boolean participateTeamChallenge(@Valid TeamChallengerParticipation participation) {
		TeamChallenge teamChallenge = teamChallengeDao.getTeamChallengeByTeamChallengeId(participation.getTeamChallengeId());
		Member member = memberDao.findById(participation.getMemberId()).get();
		
		TeamChallenger teamChallenger = TeamChallenger.builder()
				.done(false)
				.teamChallenge(teamChallenge)
				.member(member)
				.build();
		
		TeamChallenger isExist = teamChallengerDao.findTeamChallengerByTeamChallengeAndMember(teamChallenge, member);
		if(isExist != null) {
			return false;
		}
		
		teamChallengerDao.save(teamChallenger);
		return true;
	}

	public boolean giveupTeamChallenge(@Valid TeamChallengerParticipation participation) {
		TeamChallenge teamChallenge = teamChallengeDao.getTeamChallengeByTeamChallengeId(participation.getTeamChallengeId());
		Member member = memberDao.findById(participation.getMemberId()).get();
		
		TeamChallenger isExist = teamChallengerDao.findTeamChallengerByTeamChallengeAndMember(teamChallenge, member);
		if(isExist == null) {
			return false;
		}
		
		teamChallengerDao.delete(isExist);
		return true;
	}
}
