package com.web.curation.feed;

import com.web.curation.amazonS3.S3Uploader;
import com.web.curation.files.Photo;
import com.web.curation.files.PhotoAndDtoAdapter;
import com.web.curation.files.PhotoDao;
import com.web.curation.files.PhotoDto;
import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.Team;
import com.web.curation.team.TeamDao;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.challenge.TeamChallengeDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedService {
	private FeedDao feedDao;
	private PhotoDao photoDao;
	private S3Uploader s3Uploader;
	private JoinTeamDao joinTeamDao;
	private TeamChallengeDao teamChallengeDao;
	private MemberDao memberDao;
	private TeamDao teamDao;

	public Feed registerFeed(FeedDto feedDto) throws IOException {
		Member member = memberDao.findById(feedDto.getMemerId()).get();
		Team team = teamDao.findById(feedDto.getTeamId()).get();
		JoinTeam joinTeam = joinTeamDao.findByMemberAndTeam(member, team).get();
		//Feed 객체 생성하기
		Feed feed = Feed.builder()
				.joinTeam(joinTeam)
				.teamchallenge(null)
				.teamName(feedDto.getTeamName())
				.contents(feedDto.getContents())
				.writer(feedDto.getWriter())
				.build();
		if(feedDto.getTeamchallengeId() != 0){
			feed.setTeamchallenge(new TeamChallenge(feedDto.getTeamchallengeId()));
		}
		System.out.println(feed);
		Feed resultFeed = feedDao.save(feed);

		//사진 등록
		List<Photo> photoList = new ArrayList<>();
//		for(MultipartFile multipartFile : feedDto.getImages()){
//			Photo uploadPhoto = s3Uploader.upload(multipartFile,"static");
//			uploadPhoto.setFeed(resultFeed);
//			Photo photo = PhotoAndDtoAdapter.dtoToEntity(uploadPhoto);
//			photoList.add(photoDao.save(photo));
//		}
		PhotoDto uploadPhoto = s3Uploader.upload(feedDto.getImage(), "static");
		Photo photo = PhotoAndDtoAdapter.dtoToEntity(uploadPhoto);
		uploadPhoto.setFeed(resultFeed);
		photoList.add(photoDao.save(photo));
		feed.setPhotos(photoList);

		return feedDao.save(feed);
	}

	public List<Feed> getFeedList(){
		return feedDao.findAllJoinFetch();
	}

	public boolean updateFeed (FeedDto feedDto)throws IOException{
		Optional<Feed> chkFeed = feedDao.findById(feedDto.getFeedId());
		if(!chkFeed.isPresent()){
			return false;
		}
		Feed oldFeed = chkFeed.get();
		for(Photo photo : oldFeed.getPhotos()) {
			s3Uploader.deleteFile(photo.getImageName());
			photoDao.delete(photo);
		}

		List<Photo> photoList = new ArrayList<>();
//		for(MultipartFile multipartFile : feedDto.getImages()){
//			PhotoDto uploadPhoto = s3Uploader.upload(multipartFile,"static");
//			Photo photo = PhotoAndDtoAdapter.dtoToEntity(uploadPhoto);
//			uploadPhoto.setFeed(resultFeed);
//			photoList.add(photoDao.save(uploadPhoto));
//		}
		PhotoDto uploadPhoto = s3Uploader.upload(feedDto.getImage(), "static");
		Photo photo = PhotoAndDtoAdapter.dtoToEntity(uploadPhoto);
		uploadPhoto.setFeed(oldFeed);
		photoList.add(photoDao.save(photo));

		Member member = memberDao.findById(feedDto.getMemerId()).get();
		Team team = teamDao.findById(feedDto.getTeamId()).get();
		JoinTeam joinTeam = joinTeamDao.findByMemberAndTeam(member, team).get();
		Feed feed = Feed.builder()
				.feedId(feedDto.getFeedId())
				.contents(feedDto.getContents())
				.joinTeam(joinTeam)
				.teamName(feedDto.getTeamName())
				.writer(feedDto.getWriter())
				.photos(photoList)
				.teamchallenge(teamChallengeDao.findById(feedDto.getTeamchallengeId()).get())
				.build();

		feedDao.save(feed);
		return true;
	}

	public boolean deleteFeed(FeedDto feedDto){
		Optional<Feed> chkFeed = feedDao.findById(feedDto.getFeedId());
		if(!chkFeed.isPresent()){
			return false;
		}
		Feed feed = chkFeed.get();
		for(Photo photo : feed.getPhotos()){
			s3Uploader.deleteFile(photo.getImageName());
			photoDao.delete(photo);
		}

		feedDao.delete(feed);
		return true;
	}

}
