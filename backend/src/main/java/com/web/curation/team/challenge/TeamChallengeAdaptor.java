package com.web.curation.team.challenge;

public class TeamChallengeAdaptor {
    static public TeamChallengeDto entityToDto(TeamChallenge t){
        return TeamChallengeDto.builder()
                .teamChallengeId(t.getTeamChallengeId())
                .teamId(t.getTeam().getTeamId())
                .title(t.getTitle())
                .contents(t.getContents())
                .startDate(t.getStartDate())
                .endDate(t.getEndDate())
                .build();
    }

    static public TeamChallenge dtoToEntity(TeamChallengeDto dto){
        return TeamChallenge.builder()
                .teamChallengeId(dto.getTeamChallengeId())
                .title(dto.getTitle())
                .contents(dto.getContents())
                .status(Status.BEGIN)
                .memberCount(1)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
