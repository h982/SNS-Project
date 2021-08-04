package com.web.curation.feed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.team.join.JoinTeam;

import java.util.List;

public interface FeedDao extends JpaRepository<Feed, Integer>{
	@Query("SELECT f FROM Feed f join fetch f.photos ORDER BY f.writeDate DESC")
	List<Feed> findAllJoinFetch();

	int countByJoinTeam(JoinTeam jointeam);
}
