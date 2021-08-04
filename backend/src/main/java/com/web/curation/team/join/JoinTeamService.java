package com.web.curation.team.join;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberAdapter;
import com.web.curation.team.Team;
import com.web.curation.team.TeamAndDtoAdapter;

@Service
public class JoinTeamService{
	@Autowired
	JoinTeamDao joinTeamDao;

	public void createJoin(JoinTeamDto joinTeam) {
		joinTeamDao.save(JoinTeamAdapter.dtoToEntity(joinTeam));
	}

	public Optional<JoinTeamDto> getSameContent(JoinTeamDto joinTeam) {
		Optional<JoinTeam> joinTeamEntity = 
				joinTeamDao.findByMemberAndTeam(new Member(joinTeam.getMember().getMemberId()), new Team(joinTeam.getTeam().getTeamId()));
		
		Optional<JoinTeamDto> responseJoinTeam = Optional.ofNullable(null);
		if(!joinTeamEntity.isPresent()) return responseJoinTeam;
		return Optional.of(JoinTeamAdapter.entityToDto(joinTeamEntity.get()));
	}

}
