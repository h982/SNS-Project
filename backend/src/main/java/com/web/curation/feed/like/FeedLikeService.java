package com.web.curation.feed.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedLikeService {
	@Autowired
	FeedLikeDao feedLikeDao;
	
	public boolean likeFeed(FeedLike feedLike ) {
		FeedLike ret = feedLikeDao.save(feedLike);
		if(ret == null) {
			return false;
		}
		return true;
	}
	
	public boolean modifyFeedLike(FeedLike feedLike) {
		FeedLike ret = feedLikeDao.save(feedLike);
		if(ret == null) {
			return false;
		}
		return true;
	}
}