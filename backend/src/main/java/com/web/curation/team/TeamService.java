package com.web.curation.team;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import com.web.curation.files.PhotoDto;
import com.web.curation.files.PhotoService;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamDao teamDao;

    private JoinTeamDao joinTeamDao;
    
    private MemberDao memberDao;
    
//    private FileHandler fileHandler;
    private PhotoService photoService;

    List<TeamDto> getTeamlist(){
        return teamDao.findAll();
    }

    public boolean checkNameDuplicate(String name){
        return teamDao.existsByName(name);
    }

    
    public List<TeamDto> getMyTeamList(int memberId){
    	List<TeamDto> teamList = new ArrayList<>();
    	Member member = memberDao.findById(memberId).get();
    	List<JoinTeam> joinTeams = joinTeamDao.findJoinTeamByMember(member);
    	
    	for(JoinTeam joinTeam : joinTeams) {
    		teamList.add(joinTeam.getTeam());
    	}
    	
    	return teamList;
    }
    
    public TeamDto registerTeam(TeamDto teamDto, PhotoDto savedPhoto) throws Exception{

//        List<PhotoDto> photoList = fileHandler.parseFileInfo(files);
//        PhotoDto photoForId = new PhotoDto();
//        if(!photoList.isEmpty()){
//            for(PhotoDto photo : photoList){
//                photoForId = photoService.addPhoto(photo);
//            }
//        }
        if(savedPhoto.getPhotoId() != null){
            teamDto.setPhotoDto(savedPhoto);
        }else{
            teamDto.setPhotoDto(null);
        }
        teamDto.setMemberCount(1);

        return teamDao.save(teamDto);
    }

}
