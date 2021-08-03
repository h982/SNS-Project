package com.web.curation.team.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.team.Team;
import com.web.curation.team.challenger.TeamChallengerDto;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.TeamDao;
import com.web.curation.team.challenger.TeamChallenger;
import com.web.curation.team.challenger.TeamChallengerDao;

@Service
@AllArgsConstructor
public class TeamChallengeService {

	private TeamChallengeDao teamChallengeDao;
	private TeamDao teamDao;
	private TeamChallengerDao teamChallengerDao;
	private MemberDao memberDao;
	private JoinTeamDao joinTeamDao;

	public boolean addTeamChallenge(TeamChallengeDto teamChallengeDto) {

		TeamChallenge teamChallenge = TeamChallenge.builder()
				.contents(teamChallengeDto.getContents())
				.title(teamChallengeDto.getTitle())
				.memberCount(1)
				.status(Status.BEGIN)
				.team(teamDao.findById((long)teamChallengeDto.getTeamId()).get())
				.startDate(teamChallengeDto.getStartDate())
				.endDate(teamChallengeDto.getEndDate())
				.build();
		
		TeamChallenge result = teamChallengeDao.save(teamChallenge);
		System.out.println(result);
		if(result == null) {
			return false;
		}
		return true;
	}
	
	public List<TeamChallenge> updateTeamChallenge(TeamChallengeDto teamChallengeDto){
		TeamChallenge teamChallenge = TeamChallenge.builder()
				.teamChallengeId(teamChallengeDto.getTeamChallengeId())
				.contents(teamChallengeDto.getContents())
				.title(teamChallengeDto.getTitle())
				.memberCount(1)
				.status(Status.BEGIN)
				.team(teamDao.getOne(teamChallengeDto.getTeamId()))
				.startDate(teamChallengeDto.getStartDate())
				.endDate(teamChallengeDto.getEndDate())
				.build();
		
		List<TeamChallenge> list = new ArrayList<>();
		TeamChallenge result = teamChallengeDao.save(teamChallenge);
		if(result != null) {
			Optional<List<TeamChallenge>> chkList = teamChallengeDao.findTeamChallengeByTeam(teamChallenge.getTeam());
			if(chkList.isPresent()){
				list = chkList.get();
			}
		}
		return list;
	}
	
	public List<TeamChallenge> findTeamChallengeByTeam(int teamId){
		Team team = teamDao.getOne((long)teamId);
		List<TeamChallenge> list = new ArrayList<>();
		Optional<List<TeamChallenge>> chkList = teamChallengeDao.findTeamChallengeByTeam(team);
		if(chkList.isPresent()){
			list = chkList.get();
		}
		return list;
	}

	public List<TeamChallenge> getTeamChallengeList(@Valid int memberId) {
		Member member = memberDao.findById(memberId).get();
		List<JoinTeam> joinTeams = joinTeamDao.findJoinTeamByMember(member);

		List<TeamChallenge> list = new ArrayList<>();
		for(JoinTeam join: joinTeams){
			List<TeamChallenge> tc = teamChallengeDao.findTeamChallengeByTeam(join.getTeam()).get();
			list.addAll(tc);
		}

		return list;
	}

	public boolean participateTeamChallenge(@Valid TeamChallengerDto teamChallengerDto) {
		TeamChallenge teamChallenge = teamChallengeDao.findById(teamChallengerDto.getTeamChallengeId()).get();
		Member member = memberDao.findById(teamChallengerDto.getMemberId()).get();
		
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

	public boolean giveupTeamChallenge(@Valid TeamChallengerDto teamChallengerDto) {
		TeamChallenge teamChallenge = teamChallengeDao.findById(teamChallengerDto.getTeamChallengeId()).get();
		Member member = memberDao.findById(teamChallengerDto.getMemberId()).get();
		
		TeamChallenger isExist = teamChallengerDao.findTeamChallengerByTeamChallengeAndMember(teamChallenge, member);
		if(isExist == null) {
			return false;
		}
		
		teamChallengerDao.delete(isExist);
		return true;
	}
}
