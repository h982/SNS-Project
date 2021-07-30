package com.web.curation.feed.comment;

import java.util.List;

public interface CommentDao {
	// 피드의 댓글 찾기
	List<Comment> findCommentByFeedId(int feedId);

	// 대댓글 찾기
	List<Comment> findCommentByParentId(int parentId);
}
