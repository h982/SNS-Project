package com.web.curation.team.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.team.Team;
import com.web.curation.team.challenger.TeamChallengerDto;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.TeamDao;
import com.web.curation.team.challenger.TeamChallenger;
import com.web.curation.team.challenger.TeamChallengerDao;

@Service
@AllArgsConstructor
public class TeamChallengeService {

    private TeamChallengeDao teamChallengeDao;
    private TeamDao teamDao;
    private TeamChallengerDao teamChallengerDao;
    private MemberDao memberDao;
    private JoinTeamDao joinTeamDao;

    public boolean addTeamChallenge(TeamChallengeDto teamChallengeDto) {

        TeamChallenge teamChallenge = TeamChallenge.builder()
                .contents(teamChallengeDto.getContents())
                .title(teamChallengeDto.getTitle())
                .memberCount(1)
                .status(Status.BEGIN)
                .team(teamDao.findById(teamChallengeDto.getTeamId()).get())
                .startDate(teamChallengeDto.getStartDate())
                .endDate(teamChallengeDto.getEndDate())
                .build();

        teamChallengeDao.save(teamChallenge);

        return true;
    }

    public List<TeamChallenge> updateTeamChallenge(TeamChallengeDto teamChallengeDto) {
        TeamChallenge teamChallenge = TeamChallenge.builder()
                .teamChallengeId(teamChallengeDto.getTeamChallengeId())
                .contents(teamChallengeDto.getContents())
                .title(teamChallengeDto.getTitle())
                .memberCount(1)
                .status(Status.BEGIN)
                .team(teamDao.getOne(teamChallengeDto.getTeamId()))
                .startDate(teamChallengeDto.getStartDate())
                .endDate(teamChallengeDto.getEndDate())
                .build();

        List<TeamChallenge> list = new ArrayList<>();
        TeamChallenge result = teamChallengeDao.save(teamChallenge);
        if (result != null) {
            Optional<List<TeamChallenge>> chkList = teamChallengeDao.findTeamChallengeByTeam(teamChallenge.getTeam());
            if (chkList.isPresent()) {
                list = chkList.get();
            }
        }
        return list;
    }

    public Optional<List<TeamChallengeDto>> getTeamChallengeList(@Valid int memberId) {
        Optional<Member> chkMember = memberDao.findById(memberId);
        if (!chkMember.isPresent()) {
            return Optional.empty();
        }

        Member member = chkMember.get();
        Optional<List<JoinTeam>> chkJoinTeams = Optional.ofNullable(joinTeamDao.findJoinTeamByMember(member));
        if (!chkJoinTeams.isPresent()) {
            return Optional.empty();
        }

        List<JoinTeam> joinTeams = chkJoinTeams.get();
        List<TeamChallengeDto> tcList = new ArrayList<>();
        for (JoinTeam join : joinTeams) {
            Optional<List<TeamChallenge>> teamChallenges = findTeamChallenges(join.getTeam().getTeamId());
            if (!teamChallenges.isPresent()) {
                continue;
            }
            for (TeamChallenge tc : teamChallenges.get()) {
                tcList.add(TeamChallengeDto.builder()
                        .teamChallengeId(tc.getTeamChallengeId())
                        .teamId(tc.getTeam().getTeamId())
                        .title(tc.getTitle())
                        .contents(tc.getContents())
                        .startDate(tc.getStartDate())
                        .endDate(tc.getEndDate())
                        .build()
                );
            }
        }
        return Optional.of(tcList);
    }

    private Optional<List<TeamChallenge>> findTeamChallenges(int teamId) {
        Optional<Team> chkTeam = teamDao.findById(teamId);
        if (!chkTeam.isPresent()) {
            return Optional.empty();
        }

        Optional<List<TeamChallenge>> teamChallenges = teamChallengeDao.findTeamChallengeByTeam(chkTeam.get());
        return teamChallenges;
    }

    public boolean participateTeamChallenge(@Valid TeamChallengerDto teamChallengerDto) {
        TeamChallenge teamChallenge = teamChallengeDao.findById(teamChallengerDto.getTeamChallengeId()).get();
        Member member = memberDao.findById(teamChallengerDto.getMemberId()).get();

        TeamChallenger teamChallenger = TeamChallenger.builder()
                .done(false)
                .teamChallenge(teamChallenge)
                .member(member)
                .build();

        TeamChallenger isExist = teamChallengerDao.findTeamChallengerByTeamChallengeAndMember(teamChallenge, member);
        if (isExist != null) {
            return false;
        }

        teamChallengerDao.save(teamChallenger);
        return true;
    }

    public boolean giveupTeamChallenge(@Valid TeamChallengerDto teamChallengerDto) {
        TeamChallenge teamChallenge = teamChallengeDao.findById(teamChallengerDto.getTeamChallengeId()).get();
        Member member = memberDao.findById(teamChallengerDto.getMemberId()).get();

        TeamChallenger isExist = teamChallengerDao.findTeamChallengerByTeamChallengeAndMember(teamChallenge, member);
        if (isExist == null) {
            return false;
        }

        teamChallengerDao.delete(isExist);
        return true;
    }
}