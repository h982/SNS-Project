package com.web.curation.team.challenger;

import com.web.curation.member.Member;
import com.web.curation.member.MemberDao;
import com.web.curation.team.Team;
import com.web.curation.team.TeamDao;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.challenge.TeamChallengeDao;
import com.web.curation.team.join.JoinTeam;
import com.web.curation.team.join.JoinTeamDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamChallengerService {

    private TeamChallengerDao teamChallengerDao;
    private TeamChallengeDao teamChallengeDao;
    private MemberDao memberDao;
    private TeamDao teamDao;


    public List<TeamChallenger> getTeamChallengerList(@Valid int memberId, int teamId) {
        Member member = memberDao.findById(memberId).get();
        Team team = teamDao.findById(teamId).get();

        List<TeamChallenge> teamChallenges = teamChallengeDao.findTeamChallengeByTeam(team);
        List<TeamChallenger> teamChallengers = teamChallengerDao.findTeamChallengerByMember(member);
        List<TeamChallenger> res = new ArrayList<>();

        for (TeamChallenger teamChallenger : teamChallengers) {
            int id = teamChallenger.getTeamChallenge().getTeamChallengeId();
            boolean flag = false;
            for (TeamChallenge teamChallenge : teamChallenges) {
                if (teamChallenge.getTeamChallengeId() == id) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                res.add(teamChallenger);
            }
        }
        return res;
    }
}

