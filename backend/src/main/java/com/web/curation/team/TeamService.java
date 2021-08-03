package com.web.curation.team;


import com.web.curation.amazonS3.S3Uploader;
import com.web.curation.files.Photo;
import com.web.curation.files.PhotoAndDtoAdapter;
import com.web.curation.files.PhotoDao;
import com.web.curation.files.PhotoDto;
import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamDao teamDao;
    private JoinTeamDao joinTeamDao;
    private MemberDao memberDao;
    private S3Uploader s3Uploader;
    private PhotoDao photoDao;
    
//    private FileHandler fileHandler;
//    private PhotoService photoService;


    List<TeamDto> getTeamlist(){
        List<Team> teamList = teamDao.findAll();
        List<TeamDto> responseTeams = new ArrayList<>();
        for(Team team : teamList){
            TeamDto teamDto = TeamAndDtoAdapter.entityToDto(team);
            if(team.getPhoto() != null){
                teamDto.setPhotoDto(PhotoAndDtoAdapter.entityToDto(team.getPhoto()));
            }
            responseTeams.add(teamDto);
        }
        return responseTeams;
    }

    public boolean checkNameDuplicate(String name){
        return teamDao.existsByName(name);
    }

    
    public List<Team> getMyTeamList(int memberId){
    	List<Team> teamList = new ArrayList<>();
    	Member member = memberDao.findById(memberId).get();
    	List<JoinTeam> joinTeams = joinTeamDao.findJoinTeamByMember(member);
    	
    	for(JoinTeam joinTeam : joinTeams) {
    		teamList.add(joinTeam.getTeam());
    	}
    	
    	return teamList;
    }

    public TeamDto registerTeam(TeamDto teamDto) throws IOException {
        PhotoDto savedPhoto = new PhotoDto();
        if(teamDto.getMultipartFile() != null){
            PhotoDto uploadPhoto = s3Uploader.upload(teamDto.getMultipartFile(),"static");
            savedPhoto = PhotoAndDtoAdapter.entityToDto(photoDao.save(PhotoAndDtoAdapter.dtoToEntity(uploadPhoto)));
        }
        if(savedPhoto.getPhotoId() != null){
            teamDto.setPhotoId(savedPhoto.getPhotoId());
        }else{
            teamDto.setPhotoId(null);
        }
        teamDto.setMemberCount(1);
        Team team = TeamAndDtoAdapter.dtoToEntity(teamDto);

        return TeamAndDtoAdapter.entityToDto(teamDao.save(team));
    }


//    public Team registerTeam(Team team, PhotoDto savedPhoto) throws Exception{
//
////        List<PhotoDto> photoList = fileHandler.parseFileInfo(files);
////        PhotoDto photoForId = new PhotoDto();
////        if(!photoList.isEmpty()){
////            for(PhotoDto photo : photoList){
////                photoForId = photoService.addPhoto(photo);
////            }
////        }
//        if(savedPhoto.getPhotoId() != null){
//            team.setPhotoDto(savedPhoto);
//        }else{
//            team.setPhotoDto(null);
//        }
//        team.setMemberCount(1);
//
//        return teamDao.save(team);
//    }

}
