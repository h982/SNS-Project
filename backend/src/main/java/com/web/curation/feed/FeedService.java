package com.web.curation.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

	@Autowired
	FeedDao feedDao;
	
	public Feed save(Feed feed) {
		Feed ret = feedDao.save(feed);
		
		return ret;
	}
	
}
