package com.web.curation.feed;

import com.google.common.base.Optional;
import com.web.curation.amazonS3.S3Uploader;
import com.web.curation.error.CustomException;
import com.web.curation.files.Photo;
import com.web.curation.files.PhotoAndDtoAdapter;
import com.web.curation.files.PhotoDao;
import com.web.curation.files.PhotoDto;
import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.Team;
import com.web.curation.team.TeamDao;
import com.web.curation.team.challenge.TeamChallengeDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.web.curation.error.ErrorCode.*;

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

    @Transactional
    public void registerFeed(FeedDto feedDto) throws IOException {
        Member member = memberDao.findById(feedDto.getMemberId())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        Team team = teamDao.findById(feedDto.getTeamId())
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
        JoinTeam joinTeam = joinTeamDao.findByMemberAndTeam(member, team)
                .orElseThrow(() -> new CustomException(JOIN_TEAM_NOT_FOUND));
        feedDto.setMember(member);
        feedDto.setTeam(team);

        Feed feed = FeedAdaptor.dtoToEntity(feedDto);
        if (feedDto.getTeamchallengeId() != 0) {
            feed.setTeamchallenge(teamChallengeDao.findById(feedDto.getTeamchallengeId())
                    .orElseThrow(() -> new CustomException(TEAM_CHALLENGE_NOT_FOUND)));
        }
        Feed resultFeed = feedDao.save(feed);

        //사진 등록
//		List<Photo> photoList = new ArrayList<>();
//		for(MultipartFile multipartFile : feedDto.getImages()){
//			Photo uploadPhoto = s3Uploader.upload(multipartFile,"static");
//			uploadPhoto.setFeed(resultFeed);
//			Photo photo = PhotoAndDtoAdapter.dtoToEntity(uploadPhoto);
//			photoList.add(photoDao.save(photo));
//		}
        PhotoDto uploadPhoto = s3Uploader.upload(feedDto.getImage(), "static");
        uploadPhoto.setFeed(resultFeed);
        Photo photo = PhotoAndDtoAdapter.dtoToEntity(uploadPhoto);
        photoDao.save(photo);
    }

    public List<Feed> getFeedList(int memberId, int page) {
        PageRequest pageRequest = PageRequest.of(page, 3, Sort.by("feedId").descending());
        Member chkMember = memberDao.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        List<JoinTeam> joinTeamList = joinTeamDao.findJoinTeamByMember(chkMember);
        List<Team> teamForSearch = new ArrayList<>();
//        for (JoinTeam joinTeam : joinTeamList) {
//            teamForSearch.add(joinTeam.getTeam());
//        }
        joinTeamList.forEach(joinTeam -> teamForSearch.add(joinTeam.getTeam()));
        List<Feed> feeds = feedDao.findAllJoinFetch(teamForSearch, pageRequest);
        if (feeds == null) {
            return Collections.emptyList();
        }
        return feeds;
    }

    @Transactional
    public void updateFeed(FeedDto feedDto) throws IOException {
        Feed oldFeed = feedDao.findById(feedDto.getFeedId())
                .orElseThrow(() -> new CustomException(FEED_NOT_FOUND));

        for (Photo photo : oldFeed.getPhotos()) {
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

        Member member = memberDao.findById(feedDto.getMemberId())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        Team team = teamDao.findById(feedDto.getTeamId())
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
        JoinTeam joinTeam = joinTeamDao.findByMemberAndTeam(member, team)
                .orElseThrow(() -> new CustomException(JOIN_TEAM_NOT_FOUND));
        Feed feed = FeedAdaptor.dtoToEntity(feedDto);
        if (feedDto.getTeamchallengeId() != 0) {
            feed.setTeamchallenge(teamChallengeDao.findById(feedDto.getTeamchallengeId())
                    .orElseThrow(() -> new CustomException(TEAM_CHALLENGE_NOT_FOUND)));
        }

        feedDao.save(feed);
    }

    @Transactional
    public void deleteFeed(FeedDto feedDto) {
        Feed feed = feedDao.findById(feedDto.getFeedId())
                .orElseThrow(() -> new CustomException(FEED_NOT_FOUND));
        for (Photo photo : feed.getPhotos()) {
            s3Uploader.deleteFile(photo.getImageName());
            photoDao.delete(photo);
        }
        feedDao.delete(feed);
    }

    public List<Feed> getTeamFeeds(int teamId) {
        Team team = teamDao.findById(teamId)
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
//        List<JoinTeam> joinTeams = joinTeamDao.findJoinTeamsByTeam(team)
//                .orElseThrow(() -> new CustomException(JOIN_TEAM_NOT_FOUND));

        List<Feed> teamFeeds = feedDao.findByTeam(team);
        if (teamFeeds == null) {
            return Collections.emptyList();
        }
        return teamFeeds;
    }

    public List<Feed> getMemberFeeds(int memberId) {
        Member member = memberDao.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
//        List<JoinTeam> joinMembers = joinTeamDao.findJoinTeamByMember(member);

        List<Feed> teamFeeds = feedDao.findByMember(member);
        if (teamFeeds == null) {
            return Collections.emptyList();
        }
        return teamFeeds;
    }
}
