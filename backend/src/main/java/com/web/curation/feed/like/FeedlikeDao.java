package com.web.curation.feed.like;

import com.web.curation.feed.Feed;
import com.web.curation.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedlikeDao extends JpaRepository<Feedlike, Integer>{
//	List<Integer> findFeedLikeByMemberId(int memberId);
//	List<Integer> findFeedLikeByFeed(Feed feed);

    Optional<Feedlike> findFeedlikeByMemberAndFeed(Member member, Feed feed);

    Optional<List<Feedlike>> findFeedlikeByFeed(Feed feed);
}