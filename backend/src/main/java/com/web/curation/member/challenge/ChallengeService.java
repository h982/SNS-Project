package com.web.curation.member.challenge;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.curation.feed.FeedDao;
import com.web.curation.feed.comment.CommentDao;
import com.web.curation.member.Member;
import com.web.curation.team.TeamDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChallengeService {
	private ChallengeDao challengeDao;
	private JoinTeamDao joinTeamDao;
	private FeedDao feedDao;
	private CommentDao commentDao;
	
	public void createChallenge(int memberId) {
		challengeDao.save(ChallengeAdapter.createEntity(memberId));
	}
	
	public void attend(int memberId) {
		Challenge entity = challengeDao.getOneByMember(new Member(memberId));
		ChallengeDto dto = ChallengeAdapter.entityToDto(entity);
		
		if(!dto.getAttendanceDate().isEqual(LocalDate.now())) {
			dto.setAttendance(dto.getAttendance() + 1);
			dto.setAttendanceDate(LocalDate.now());
		}
		
		challengeDao.save(ChallengeAdapter.dtoToEntity(dto));
	}
	
	public ChallengeDto getChallenge(int memberId) {
		Challenge entity = challengeDao.getOneByMember(new Member(memberId));
		System.out.println(entity.getChallengeId());
		ChallengeDto dto = updateInfo(ChallengeAdapter.entityToDto(entity));
		
		challengeDao.save(ChallengeAdapter.dtoToEntity(dto));
		
		return dto;
	}

	private ChallengeDto updateInfo(ChallengeDto challengeDto) {
		System.out.println("update");
		int cnt = 0;
		cnt += feedDao.countByMember(new Member(challengeDto.getMemberId()));

		
		challengeDto.setTeamCount(joinTeamDao.countByMember(new Member(challengeDto.getMemberId())));
		challengeDto.setCommentCount(commentDao.countByMember(new Member(challengeDto.getMemberId())));
		challengeDto.setFeedCount(cnt);
		
		return challengeDto;
	}
}
