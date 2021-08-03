package com.web.curation.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberAdapter;
import com.web.curation.member.MemberDao;
import com.web.curation.team.Team;
import com.web.curation.team.TeamAndDtoAdapter;
import com.web.curation.team.TeamDao;
import com.web.curation.team.TeamDto;

@Service
public class RequestService {
	@Autowired
	RequestDao requestDao;

	@Autowired
	MemberDao memberdao;
	
	@Autowired
	TeamDao teamDao;

	public void makeRequest(RequestDto requestDto) {
		Request request = RequestAdapter.DtoToEntity(requestDto);
		requestDao.save(request);
	}

	public List<RequestDto> getRequestList(int teamId) {
		List<RequestDto> dtoList = new ArrayList<RequestDto>();
		List<Request> entityList = requestDao.findAllByTeam(new Team(teamId));
		
		for(Request request : entityList){
			dtoList.add(RequestAdapter.EntityToDto(request));
		}
		
		return dtoList;
	}

	public RequestDto acceptRequest(int requestId) {
		Request request = requestDao.getOne(requestId);
		
		RequestDto requestDto = RequestAdapter.EntityToDto(request);
		requestDto.setStatus(Status.ACCEPTED);
		
		requestDao.save(RequestAdapter.DtoToEntity(requestDto));
		return requestDto;
	}

	public RequestDto rejectRequest(int requestId) {
		Request request = requestDao.getOne(requestId);
		
		RequestDto requestDto = RequestAdapter.EntityToDto(request);
		requestDto.setStatus(Status.REJECTED);
		
		requestDao.save(RequestAdapter.DtoToEntity(requestDto));
		return requestDto;
	}

	public boolean checkDuplication(RequestDto requestDto) {
		Team team = TeamAndDtoAdapter.dtoToEntity(requestDto.getTeam());
		Member member = MemberAdapter.dtoToEntity(requestDto.getMember());
		
		Optional<Request> responseRequest = requestDao.getRequestByTeamAndMember(team, member);
		
		if(responseRequest.isPresent()) return true;
		return false;
	}

}
