package com.web.curation.feed.like;

import com.web.curation.error.CustomException;
import com.web.curation.error.NotFoundDataException;
import com.web.curation.feed.Feed;
import com.web.curation.feed.FeedDao;
import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.web.curation.error.ErrorCode.*;

@Service
@AllArgsConstructor
public class FeedlikeService {
    FeedlikeDao feedLikeDao;
    FeedDao feedDao;
    MemberDao memberDao;

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

        return feedlikeDto;
    }

    public List<FeedlikeDto> getfeedlikeList(int feedId) {
        Feed feed = feedDao.findById(feedId)
                .orElseThrow(() -> new CustomException(FEED_NOT_FOUND));
        List<Feedlike> feedlikeList = feedLikeDao.findFeedlikeByFeed(feed)
                .orElse(Collections.emptyList());

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
        List<Feedlike> feedlikeList = feedLikeDao.findFeedlikeByMember(member)
                .orElse(Collections.emptyList());

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