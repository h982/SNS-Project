package com.web.curation.feed.like;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.feed.Feed;

public interface FeedLikeDao extends JpaRepository<FeedLike, Integer>{
//	List<Integer> findFeedLikeByMemberId(int memberId);
//	List<Integer> findFeedLikeByFeed(Feed feed);
}