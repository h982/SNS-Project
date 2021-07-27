package com.web.curation.team;

import com.web.curation.files.PhotoDao;
import com.web.curation.files.PhotoDto;
import com.web.curation.files.PhotoService;
import com.web.curation.handlers.FileHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private TeamDao teamDao;
    private FileHandler fileHandler;
    private PhotoService photoService;

    List<TeamDto> getTeamlist(){
        return teamDao.findAll();
    }

    public boolean checkNameDuplicate(String name){
        return teamDao.existsByName(name);
    }

    public TeamDto registerTeam(TeamDto teamDto, PhotoDto savedPhoto) throws Exception{

//        List<PhotoDto> photoList = fileHandler.parseFileInfo(files);
//        PhotoDto photoForId = new PhotoDto();
//        if(!photoList.isEmpty()){
//            for(PhotoDto photo : photoList){
//                photoForId = photoService.addPhoto(photo);
//            }
//        }
        teamDto.setPhotoDto(savedPhoto);
        teamDto.setMemberCount(1);

        return teamDao.save(teamDto);
    }

}
