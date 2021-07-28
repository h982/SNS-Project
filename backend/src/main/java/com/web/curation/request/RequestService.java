package com.web.curation.request;

import java.util.List;
import java.util.Optional;

import com.web.curation.member.Member;
import com.web.curation.team.TeamDto;

public interface RequestService {
	boolean checkDuplication(Request request);
	void makeRequest(Request request);
	List<Request> getRequestList(int teamId);
	Request acceptRequest(int requestId);
	Request rejectRequest(int requestId);
}
