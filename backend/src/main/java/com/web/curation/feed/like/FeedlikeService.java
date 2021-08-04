package com.web.curation.feed.like;

import com.web.curation.feed.Feed;
import com.web.curation.feed.FeedDao;
import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedlikeService {
	FeedlikeDao feedLikeDao;
	FeedDao feedDao;
	MemberDao memberDao;
	
	public int likeFeed(FeedlikeDto feedlikeDto) {
		Optional<Member> chkMember = memberDao.findById(feedlikeDto.getMemberId());
		Optional<Feed> chkFeed = feedDao.findById(feedlikeDto.getFeedId());
		if(!chkFeed.isPresent() || !chkMember.isPresent()){
			return -1;
		}

		Member member = chkMember.get();
		Feed feed = chkFeed.get();
		Optional<Feedlike> chkFeedlike = feedLikeDao.findFeedlikeByMemberAndFeed(member, feed);
		if(chkFeedlike.isPresent()){
			return -1;
		}

		Feedlike feedlike = Feedlike.builder()
				.member(member)
				.feed(feed)
				.feedLike(feedlikeDto.getFeedLike())
				.build();
		feedLikeDao.save(feedlike);
		Feedlike savedFeedlike = feedLikeDao.findFeedlikeByMemberAndFeed(member, feed).get();
		return savedFeedlike.getFeedlikeId();
	}

	public Optional<List<FeedlikeDto>> getfeedlikeList(int feedId){
		Optional<Feed> chkFeed = feedDao.findById(feedId);
		if(!chkFeed.isPresent()){
			return Optional.empty();
		}
		List<Feedlike> feedlikeList = feedLikeDao.findFeedlikeByFeed(chkFeed.get()).get();
		List<FeedlikeDto> feedlikeDtos = new ArrayList<>();
		for(Feedlike feedlike: feedlikeList){
			feedlikeDtos.add(FeedlikeDto.builder()
					.feedlikeId(feedlike.getFeedlikeId())
					.memberId(feedlike.getMember().getMemberId())
					.feedId(feedlike.getFeed().getFeedId())
					.feedLike(feedlike.getFeedLike())
					.build()
			);
		}

		return Optional.of(feedlikeDtos);
	}

	public boolean updateFeedlike(FeedlikeDto feedlikeDto){
		Optional<Feedlike> chkFeedlike = feedLikeDao.findById(feedlikeDto.getFeedlikeId());
		if(!chkFeedlike.isPresent()){
			return false;
		}
		Feedlike feedlike = chkFeedlike.get();
		feedlike.setFeedLike(feedlikeDto.getFeedLike());
		feedLikeDao.save(feedlike);
		return true;
	}

	public boolean deleteFeedlike(FeedlikeDto feedlikeDto){
		Optional<Feedlike> chkFeedlike = feedLikeDao.findById(feedlikeDto.getFeedlikeId());
		if(!chkFeedlike.isPresent()){
			return false;
		}
		feedLikeDao.delete(chkFeedlike.get());
		return true;
	}
}