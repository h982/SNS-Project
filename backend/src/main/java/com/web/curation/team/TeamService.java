package com.web.curation.team;


import com.web.curation.amazonS3.S3Uploader;
import com.web.curation.error.CustomException;
import com.web.curation.error.NotFoundDataException;
import com.web.curation.files.PhotoAndDtoAdapter;
import com.web.curation.files.PhotoDao;
import com.web.curation.files.PhotoDto;
import com.web.curation.member.Member;
import com.web.curation.member.MemberAdapter;
import com.web.curation.member.MemberDao;
import com.web.curation.member.MemberDto;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import com.web.curation.team.join.JoinTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.web.curation.error.ErrorCode.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final TeamDao teamDao;
    private final JoinTeamDao joinTeamDao;
    private final MemberDao memberDao;
    private final S3Uploader s3Uploader;
    private final PhotoDao photoDao;

    
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
        Team team = null;
        if(savedPhoto.getPhotoId() != null){
            teamDto.setPhotoId(savedPhoto.getPhotoId());
            team = TeamAndDtoAdapter.dtoToEntityPhoto(teamDto);
        }else{
            teamDto.setPhotoId(null);
            team = TeamAndDtoAdapter.dtoToEntity(teamDto);
        }
        teamDto.setMemberCount(1);
        TeamDto resultTeamDto = TeamAndDtoAdapter.entityToDto(teamDao.save(team));

        Team chkTeam = teamDao.findById(resultTeamDto.getTeamId())
                .orElseThrow(NotFoundDataException::new);
        Member chkMember = memberDao.findById(resultTeamDto.getMemberId())
                .orElseThrow(NotFoundDataException::new);
        JoinTeam jointeam = JoinTeam.builder()
                .team(chkTeam)
                .member(chkMember)
                .build();
        joinTeamDao.save(jointeam);

        return resultTeamDto;
    }

    public boolean changeTeamLeader(int teamId, int memberId) {
    	Member member = memberDao.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    	Team team = teamDao.findById(teamId)
    			.orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
    	
    	TeamDto teamDto = TeamAndDtoAdapter.entityToDto(team);
    	teamDto.setSportId(teamDto.getSportDto().getSportId());
    	teamDto.setMemberId(member.getMemberId());
    	teamDto.setLeader(member.getName());
    	
    	teamDao.save(TeamAndDtoAdapter.dtoToEntity(teamDto));
    	return true;
    }
    
    public boolean updateTeam(TeamDto teamDto) {
    	Team team = teamDao.findById(teamDto.getTeamId())
    			.orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
    	
    	PhotoDto savedPhoto = new PhotoDto();
        if(teamDto.getMultipartFile() != null){
            PhotoDto uploadPhoto;
			try {
				uploadPhoto = s3Uploader.upload(teamDto.getMultipartFile(),"static");
				savedPhoto = PhotoAndDtoAdapter.entityToDto(photoDao.save(PhotoAndDtoAdapter.dtoToEntity(uploadPhoto)));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        if(savedPhoto.getPhotoId() != null){
            teamDto.setPhotoId(savedPhoto.getPhotoId());
            team = TeamAndDtoAdapter.dtoToEntityPhoto(teamDto);
        }else{
            teamDto.setPhotoId(null);
            team = TeamAndDtoAdapter.dtoToEntity(teamDto);
        }
        teamDao.save(team);
        
    	return true;
    }
}
