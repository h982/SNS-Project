package com.web.curation.request;

import org.springframework.beans.factory.annotation.Autowired;

import com.web.curation.member.MemberAdapter;
import com.web.curation.member.MemberDao;
import com.web.curation.team.TeamAndDtoAdapter;
import com.web.curation.team.TeamDao;

public class RequestAdapter {
	@Autowired
	private static MemberDao memberdao;
	
	@Autowired
	private static TeamDao teamDao;
	
	public static Request DtoToEntity(RequestDto requestDto) {
		return new Request.RequestBuilder()
				.team(teamDao.findById(requestDto.getTeam().getTeamId()).get())
				.member(memberdao.findById(requestDto.getMember().getMemberId()).get())
				.status(Status.WAITING)
				.build();
	}
	
	public static RequestDto EntityToDto(Request request) {
		return new RequestDto.RequestDtoBuilder()
				.team(TeamAndDtoAdapter.entityToDto(request.getTeam()))
				.member(MemberAdapter.entityToDto(request.getMember()))
				.status(request.getStatus())
				.build();
	}
}
