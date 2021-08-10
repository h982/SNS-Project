package com.web.curation.feed.like;

import com.web.curation.error.CustomException;
import com.web.curation.error.NotFoundDataException;
import com.web.curation.feed.Feed;
import com.web.curation.feed.FeedDao;
import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.Team;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.challenger.TeamChallenger;
import com.web.curation.team.challenger.TeamChallengerDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.web.curation.error.ErrorCode.*;

@Service
@AllArgsConstructor
public class FeedlikeService {
    private FeedlikeDao feedLikeDao;
    private FeedDao feedDao;
    private MemberDao memberDao;
    private TeamChallengerDao teamChallengerDao;

    @Transactional
    public FeedlikeDto likeFeed(FeedlikeDto feedlikeDto) {
        Member member = memberDao.findById(feedlikeDto.getMemberId())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        Feed feed = feedDao.findById(feedlikeDto.getFeedId())
                .orElseThrow(() -> new CustomException(FEED_NOT_FOUND));

        feedLikeDao.findFeedlikeByMemberAndFeed(member, feed)
                .ifPresent(feedlike -> {
                    throw new CustomException(FEEDLIKE_DUPLICATE_RESOURCE);
                });

        Feedlike feedlike = Feedlike.builder()
                .member(member)
                .feed(feed)
                .feedLike(feedlikeDto.getFeedLike())
                .build();
        Feedlike savedFeedlike = feedLikeDao.saveAndFlush(feedlike);
        feedlikeDto.setFeedlikeId(savedFeedlike.getFeedlikeId());

        TeamChallenge teamChallenge = feed.getTeamchallenge();
        if(teamChallenge != null){
            checkTeamChallenge(feed);
        }
        return feedlikeDto;
    }

    private void checkTeamChallenge(Feed feed){
        List<Feedlike> feedlikes = feedLikeDao.findFeedlikeByFeed(feed);
        Team team = feed.getTeam();
        int member_count = team.getMemberCount();
        if(feedlikes.size() < member_count / 3)
            return;

        TeamChallenger teamChallenger = teamChallengerDao.findTeamChallengerByTeamChallengeAndMember(
                feed.getTeamchallenge(), feed.getMember()
        ).orElseThrow(() -> new CustomException(TEAM_CHALLENGER_NOT_FOUND));
        teamChallenger.setDone(true);
        teamChallengerDao.save(teamChallenger);
    }

    public List<FeedlikeDto> getfeedlikeList(int feedId) {
        Feed feed = feedDao.findById(feedId)
                .orElseThrow(() -> new CustomException(FEED_NOT_FOUND));
        List<Feedlike> feedlikeList = feedLikeDao.findFeedlikeByFeed(feed);

        List<FeedlikeDto> feedlikeDtos = new ArrayList<>();
        for (Feedlike feedlike : feedlikeList) {
            feedlikeDtos.add(FeedlikeDto.builder()
                    .feedlikeId(feedlike.getFeedlikeId())
                    .memberId(feedlike.getMember().getMemberId())
                    .feedId(feedlike.getFeed().getFeedId())
                    .feedLike(feedlike.getFeedLike())
                    .build()
            );
        }

        return feedlikeDtos;
    }

    public List<FeedlikeDto> getMyFeedlikes(int memberId) {
        Member member = memberDao.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        List<Feedlike> feedlikeList = feedLikeDao.findFeedlikeByMember(member);

        List<FeedlikeDto> feedlikeDtos = new ArrayList<>();
        for (Feedlike feedlike : feedlikeList) {
            feedlikeDtos.add(FeedlikeDto.builder()
                    .feedlikeId(feedlike.getFeedlikeId())
                    .memberId(feedlike.getMember().getMemberId())
                    .feedId(feedlike.getFeed().getFeedId())
                    .feedLike(feedlike.getFeedLike())
                    .build()
            );
        }

        return feedlikeDtos;
    }

    @Transactional
    public void updateFeedlike(FeedlikeDto feedlikeDto) {
        Feedlike feedlike = feedLikeDao.findById(feedlikeDto.getFeedlikeId())
                .orElseThrow(() -> new CustomException(FEEDLIKE_NOT_FOUND));

        feedlike.setFeedLike(feedlikeDto.getFeedLike());
        feedLikeDao.save(feedlike);
    }

    @Transactional
    public void deleteFeedlike(FeedlikeDto feedlikeDto) {
        Feedlike feedlike = feedLikeDao.findById(feedlikeDto.getFeedlikeId())
                .orElseThrow(() -> new CustomException(FEEDLIKE_NOT_FOUND));

        feedLikeDao.delete(feedlike);
    }
}