package com.web.curation.team.join;

import static com.web.curation.error.ErrorCode.JOIN_TEAM_NOT_FOUND;
import static com.web.curation.error.ErrorCode.TEAM_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.curation.error.CustomException;
import com.web.curation.member.Member;
import com.web.curation.team.Team;
import com.web.curation.team.TeamDao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JoinTeamService{
	
	JoinTeamDao joinTeamDao;
	TeamDao teamDao;

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
	
	public List<JoinTeamDto> findMemberByTeam(int teamId){
		Team team = teamDao.findById(teamId)
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
        List<JoinTeam> joinTeams = joinTeamDao.findJoinTeamsByTeam(team)
                .orElseThrow(() -> new CustomException(JOIN_TEAM_NOT_FOUND));

        List<JoinTeamDto> list = new ArrayList<JoinTeamDto>();
        for(JoinTeam joinTeam : joinTeams) {
        	list.add(JoinTeamAdapter.entityToDto(joinTeam));
        }
        
        return list;
	}

}
