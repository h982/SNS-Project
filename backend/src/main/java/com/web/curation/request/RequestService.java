package com.web.curation.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.web.curation.error.CustomException;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.Team;
import com.web.curation.team.TeamDao;
import org.springframework.transaction.annotation.Transactional;

import static com.web.curation.error.ErrorCode.*;

@Service
@AllArgsConstructor
public class RequestService {
    RequestDao requestDao;
    MemberDao memberdao;
    TeamDao teamDao;
    JoinTeamDao joinTeamDao;

    public void makeRequest(RequestDto requestDto) {
        Team team = teamDao.findById(requestDto.getTeam().getTeamId())
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));
        Member member = memberdao.findById(requestDto.getMember().getMemberId())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        requestDao.getRequestByTeamAndMember(team, member)
                .ifPresent(request1 -> {
                    throw new CustomException(REQUEST_DUPLICATE_RESOURCE);
                });

        Request request = RequestAdapter.createDtoToEntity(requestDto);
        requestDao.save(request);
    }

    public List<RequestDto> getRequestList(int teamId) {
        Team team = teamDao.findById(teamId)
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));

        List<Request> entityList = requestDao.findAllByTeam(team);
        if (entityList == null) {
            return Collections.emptyList();
        }

        List<RequestDto> dtoList = new ArrayList<RequestDto>();
        for (Request request : entityList) {
            dtoList.add(RequestAdapter.EntityToDto(request));
        }

        return dtoList;
    }

    @Transactional
    public RequestDto acceptRequest(int requestId) {
        Request request = requestDao.findById(requestId)
                .orElseThrow(() -> new CustomException(REQUEST_NOT_FOUND));
        if (request.getStatus() != Status.WAITING) {
            throw new CustomException(REQUEST_PROCESSED_RESOURCE);
        }

        RequestDto requestDto = RequestAdapter.EntityToDto(request);
        requestDto.setStatus(Status.ACCEPTED);
        requestDao.save(RequestAdapter.updateDtoToEntity(requestDto));
        increaseMemberCount(requestDto.getTeam().getTeamId());
        makeJoinTeam(requestDto);

        return requestDto;
    }

    private void increaseMemberCount(int teamId) {
        Team team = teamDao.findById(teamId)
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));

        team = Team.builder()
                .teamId(team.getTeamId())
                .name(team.getName())
                .member(team.getMember())
                .sport(team.getSport())
                .createDate(team.getCreateDate())
                .introduction(team.getIntroduction())
                .leader(team.getLeader())
                .imgPath(team.getImgPath())
                .memberCount(team.getMemberCount() + 1)     // member 수 증가
                .photo(team.getPhoto())
                .build();

        teamDao.save(team);
    }

    private void makeJoinTeam(RequestDto requestDto){
        Member member = memberdao.findById(requestDto.getMember().getMemberId())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        Team team = teamDao.findById(requestDto.getTeam().getTeamId())
                .orElseThrow(() -> new CustomException(TEAM_NOT_FOUND));

        joinTeamDao.findByMemberAndTeam(member, team)
                .ifPresent(joinTeam -> {
                    throw new CustomException(JOIN_TEAM_PROCESSED_RESOURCE);
                });
        JoinTeam joinTeam = JoinTeam.builder()
                .member(member)
                .team(team)
                .build();
        joinTeamDao.save(joinTeam);
    }

    public RequestDto rejectRequest(int requestId) {
        Request request = requestDao.findById(requestId)
                .orElseThrow(() -> new CustomException(REQUEST_NOT_FOUND));
        if (request.getStatus() != Status.WAITING) {
            throw new CustomException(REQUEST_PROCESSED_RESOURCE);
        }
        RequestDto requestDto = RequestAdapter.EntityToDto(request);
        requestDto.setStatus(Status.REJECTED);
        requestDao.save(RequestAdapter.updateDtoToEntity(requestDto));

        return requestDto;
    }
}
