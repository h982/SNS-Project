package com.web.curation.feed;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedService {

	FeedDao feedDao;
	
	public Feed registerFeed(Feed feed) {
		return feedDao.save(feed);
	}

	public List<Feed> getFeedList(){
		return feedDao.findAllJoinFetch();
	}

	
}
