package com.web.curation.feed.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {
	// 피드의 댓글 찾기
//	List<Comment> findCommentByFeedId(int feedId);

	// 대댓글 찾기
//	List<Comment> findCommentByParentId(int parentId);
//	@Query("SELECT c FROM Comment c join fetch c.coComments")
	@Query("select c from Comment c where c.feed.feedId = :feedId")
	List<Comment> findAllByFeed_Id(int feedId);
}
