package com.web.curation.team;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamDao teamDao;

    List<TeamDto> getTeamlist(){
        return teamDao.findAll();
    }

    public boolean checkNameDuplicate(String name){
        return teamDao.existsByName(name);
    }

    public TeamDto registerTeam(TeamDto teamDto){
        return teamDao.save(teamDto);
    }

}
