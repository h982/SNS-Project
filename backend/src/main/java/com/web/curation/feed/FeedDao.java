package com.web.curation.feed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedDao extends JpaRepository<Feed, Long>{
	Feed getFeedByFeedId(int feedId);

	@Query("SELECT f FROM Feed f join fetch f.photos")
	List<Feed> findAllJoinFetch();
	
//	List<Feed> findFeedByMemberId(int memberId);
//	List<Feed> findFeedByMemberIdAndTeamId(int memberId, int teamId);
}
