package com.web.curation.feed.like;

import com.web.curation.feed.Feed;
import com.web.curation.feed.FeedDao;
import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedlikeService {
	FeedlikeDao feedLikeDao;
	FeedDao feedDao;
	MemberDao memberDao;
	
	public int likeFeed(FeedlikeDto feedlikeDto) {
		Optional<Member> chkMember = memberDao.findById(feedlikeDto.getFeedlikeId());
		Optional<Feed> chkFeed = feedDao.findById(feedlikeDto.getFeedId());

		if(!chkFeed.isPresent() || !chkMember.isPresent()){
			return -1;
		}
		Member member = chkMember.get();
		Feed feed = chkFeed.get();

		Feedlike feedlike = feedLikeDao.findFeedlikeByMemberAndFeed(member, feed)
				.orElse(Feedlike.builder()
						.member(member)
						.feed(feed)
						.build());
		feedlike.setFeedLike(feedlikeDto.getFeedLike());
		Feedlike ret = feedLikeDao.save(feedlike);
		return ret.getFeedlikeId();
	}
}