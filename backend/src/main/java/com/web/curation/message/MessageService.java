package com.web.curation.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.curation.feed.FeedDao;
import com.web.curation.feed.comment.CommentDao;
import com.web.curation.member.challenge.ChallengeDao;
import com.web.curation.team.Team;
import com.web.curation.team.join.JoinTeamDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {
	private MessageDao messageDao;
	
	public MessageDto insertMessage(MessageDto messageDto) {
		Message message = messageDao.save(MessageAdapter.dtoToEntity(messageDto));
		return MessageAdapter.entityToDto(message);
	}
	
	public List<MessageDto> getMessages(int teamId, int pageNum) {
		PageRequest pageRequest = PageRequest.of(pageNum, 5, Sort.by("messageId"));
		List<Message> messageList = messageDao.findByTeam(new Team(teamId), pageRequest);
		List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
		
		for(Message m : messageList) {
			messageDtoList.add(MessageAdapter.entityToDto(m));
		}
		
		return messageDtoList;
	}
}
