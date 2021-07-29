package com.web.curation.request;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.TeamDao;
import com.web.curation.team.TeamDto;

@Service
public class RequestServiceImpl implements RequestService {
	@Autowired
	RequestDao requestDao;

	@Autowired
	MemberDao memberdao;
	
	@Autowired
	TeamDao teamDao;

	@Override
	public void makeRequest(Request request) {
		request.setStatus(Status.WAITING);
		requestDao.save(request);
	}

	@Override
	public List<Request> getRequestList(int teamId) {
		return requestDao.findAllByTeam(new TeamDto(teamId));
	}

	@Override
	public Request acceptRequest(int requestId) {
		Request request = requestDao.getOne(requestId);
		request.setStatus(Status.ACCEPTED);
		requestDao.save(request);
		return request;
	}

	@Override
	public Request rejectRequest(int requestId) {
		Request request = requestDao.getOne(requestId);
		request.setStatus(Status.REJECTED);
		requestDao.save(request);
		return request;
	}

	@Override
	public boolean checkDuplication(Request request) {
		Optional<Request> responseRequest = requestDao.getRequestByTeamAndMember(request.getTeam(), request.getMember());
		
		if(responseRequest.isPresent()) return true;
		return false;
	}

}
