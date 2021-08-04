package com.web.curation.feed.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.feed.Feed;
import com.web.curation.feed.FeedDao;
import com.web.curation.member.Member;

public interface CommentDao extends JpaRepository<Comment, Integer>{
	// 피드의 댓글 찾기
	List<Comment> findCommentByFeed(Feed feed);

	// 대댓글 찾기
	List<Comment> findCommentByParent(Comment comment);

	int countByMember(Member member);
}
