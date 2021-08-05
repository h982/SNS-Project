package com.web.curation.team.challenge;

import java.util.List;

import javax.validation.Valid;

import com.web.curation.team.challenger.TeamChallengerDto;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.curation.model.BasicResponse;

import io.swagger.annotations.ApiOperation;

@ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@AllArgsConstructor
public class TeamChallengeController {
    private TeamChallengeService teamChallengeService;

    @GetMapping("/my_teamchallenge_list/{member_id}")
    @ApiOperation(value = "내 팀 챌린지 리스트")
    public ResponseEntity findTeamChallenges(@Valid @RequestParam(name = "member_id") int memberId) {

        List<TeamChallengeDto> teamChallengeDtos = teamChallengeService.getTeamChallengeList(memberId);

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = teamChallengeDtos;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/team_challenge_enroll")
    @ApiOperation(value = "팀 챌런지 생성하기")
    public ResponseEntity createTeamChallenge(@Valid @RequestBody TeamChallengeDto teamChallengeDto) {

        TeamChallengeDto retDto = teamChallengeService.addTeamChallenge(teamChallengeDto);
        BasicResponse result = new BasicResponse();
        result.status = true;
        result.object = retDto;
        result.message = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/team_challenge_participate")
    @ApiOperation(value = "팀 챌런지 참여하기")
    public ResponseEntity participateTeamChallenge(@Valid @RequestBody TeamChallengerDto teamChallengerDto) {

        teamChallengeService.participateTeamChallenge(teamChallengerDto);
        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/team_challenge_giveup")
    @ApiOperation("챌린지 포기하기")
    public ResponseEntity giveUpTeamChallenge(@Valid @RequestBody TeamChallengerDto teamChallengerDto) {

        teamChallengeService.giveUpTeamChallenge(teamChallengerDto);
        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/team_challenge")
    @ApiOperation(value = "팀 챌린지 수정하기")
    public ResponseEntity updateTeamChallenge(@Valid @RequestBody TeamChallengeDto teamChallengeDto) {

        teamChallengeService.updateTeamChallenge(teamChallengeDto);
        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/team_challenge")
    @ApiOperation(value = "팀 챌린지 삭제하기")
    private ResponseEntity<?> deleteTeamChallenge(@Valid @RequestBody TeamChallengeDto teamChallengeDto){

        teamChallengeService.deleteTeamChallenge(teamChallengeDto);
        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}