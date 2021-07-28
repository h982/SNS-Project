package com.web.curation.team;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamDao teamDao;

    @Autowired
    private JoinTeamDao joinTeamDao;
    
    @Autowired
    private MemberDao memberDao;
    
    List<TeamDto> getTeamlist(){
        return teamDao.findAll();
    }

    public boolean checkNameDuplicate(String name){
        return teamDao.existsByName(name);
    }

    public TeamDto registerTeam(TeamDto teamDto){
        return teamDao.save(teamDto);
    }
    
    public List<TeamDto> getMyTeamList(int memberId){
    	List<TeamDto> teamList = new ArrayList<>();
    	Member member = memberDao.getMemberByMemberId(memberId);
    	List<JoinTeam> joinTeams = joinTeamDao.findJoinTeamByMember(member);
    	
    	for(JoinTeam joinTeam : joinTeams) {
    		teamList.add(joinTeam.getTeam());
    	}
    	
    	return teamList;
    }
}
