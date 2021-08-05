package com.web.curation.feed.comment;

import com.web.curation.error.NotFoundDataException;
import com.web.curation.feed.Feed;
import com.web.curation.feed.FeedDao;
import com.web.curation.member.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao commentDao;
    private final FeedDao feedDao;
    private final MemberDao memberDao;

    public List<CommentDto> getCommentOfFeed(int feedId) {
        //모든 댓글 가져오기
        List<Comment> commentList = commentDao.findAllByFeed_Id(feedId);
        List<CommentDto> parentComment = new ArrayList<>();
        List<CommentDto> childComment = new ArrayList<>();

        for (Comment comment : commentList) {
            if (comment.getParent() == null) {
                parentComment.add(CommentAndDtoAdapter.entityToDto(comment));
            } else {
                childComment.add(CommentAndDtoAdapter.entityToDto(comment));
            }
        }
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (int i = 0; i < parentComment.size(); i++) {
            for (int j = 0; j < childComment.size(); j++) {
                if (childComment.get(j).getParentId() == parentComment.get(i).getCommentId()) {
                    parentComment.get(i).getCoComments().add(childComment.get(j));
                }
            }
            commentDtoList.add(parentComment.get(i));
        }
        return commentDtoList;
    }

    public CommentDto addCommentOfFeed(CommentDto commentDto) {
        Optional<Feed> chkFeed = Optional.ofNullable(feedDao.findById(commentDto.getFeedId()).orElseThrow(NotFoundDataException::new));

        commentDto.setFeed(feedDao.findById(commentDto.getFeedId()).get());
        commentDto.setMember(memberDao.findById(commentDto.getMemberId()).get());
        Comment resultComment = new Comment();
        //댓글인지 대댓글인지 확인
        if (commentDto.getParentId() != 0) {
            //대댓글이면 댓글이 존재하는지 확인
            Optional<Comment> chkComment = Optional.ofNullable(commentDao.findById(commentDto.getParentId()).orElseThrow(NotFoundDataException::new));
            //댓글이 존재하면 추가
            commentDto.setParent(chkComment.get());
            resultComment = commentDao.save(CommentAndDtoAdapter.dtoToEntityCoComment(commentDto));
        }else{
            System.out.println(CommentAndDtoAdapter.dtoToEntityComment(commentDto).getMember().toString());
            resultComment = commentDao.save(CommentAndDtoAdapter.dtoToEntityComment(commentDto));
        }
        return CommentAndDtoAdapter.entityToDto(resultComment);
    }

    public boolean removeComment(int commentId){
        Optional<Comment> chkComment = Optional.ofNullable(commentDao.findById(commentId).orElseThrow(NotFoundDataException::new));
        commentDao.deleteById(commentId);
        return true;
    }

}
