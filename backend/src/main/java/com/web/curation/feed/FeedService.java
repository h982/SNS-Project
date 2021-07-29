package com.web.curation.feed;

import com.web.curation.files.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

	@Autowired
	FeedDao feedDao;
	
	public Feed registerFeed(Feed feed) {
		return feedDao.save(feed);
	}
	
}
