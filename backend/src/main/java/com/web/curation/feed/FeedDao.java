package com.web.curation.feed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedDao extends JpaRepository<Feed, Long>{
	@Query("SELECT f FROM Feed f join fetch f.photos ORDER BY f.writeDate DESC")
	List<Feed> findAllJoinFetch();
}
