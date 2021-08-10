package com.web.curation.feed;

import com.web.curation.member.Member;
import com.web.curation.team.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.team.join.JoinTeam;

import java.util.List;
import java.util.Optional;

public interface FeedDao extends JpaRepository<Feed, Integer>{
	@Query("SELECT f FROM Feed f join fetch f.photos where f.team in ?1 ORDER BY f.writeDate DESC")
	List<Feed> findAllJoinFetch(List<Team> teamList, Pageable pageable);

	int countByMember(Member member);

//	List<Feed> findFeedsByJoinTeamIn(List<JoinTeam> joinTeams);
	List<Feed> findByMember(Member member);
	List<Feed> findByTeam(Team team);
}
// where f.joinTeam in ?1 ORDER BY f.writeDate DESC
//Pageable pageable, List<JoinTeam> joinIdList