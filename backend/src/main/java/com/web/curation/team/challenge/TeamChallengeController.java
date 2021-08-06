package com.web.curation.team.challenge;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.team.challenger.TeamChallenger;
import com.web.curation.team.challenger.TeamChallengerDto;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.curation.model.BasicResponse;

import io.swagger.annotations.ApiOperation;

@ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@AllArgsConstructor
public class TeamChallengeController {
	private TeamChallengeService teamChallengeService;

	@GetMapping("/my_teamchallenge_list/{member_id}")
	@ApiOperation(value = "내 팀 챌린지 리스트")
	public ResponseEntity findTeamChallenges(@Valid @RequestParam(name = "member_id") int memberId) {
		System.out.println("내 팀 챌린지 리스트");
		Optional<List<TeamChallengeDto>> list = teamChallengeService.getTeamChallengeList(memberId);
		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
		if (!list.isPresent()) {
			result.status = false;
			result.data = "fail";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} else {
			result.status = true;
			result.data = "success";
			result.object = list.get();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/my_teamchalleging_list")
	@ApiOperation(value = "내 팀 진행중인 챌린지 리스트")
	public ResponseEntity findTeamChallenging(@Valid @RequestParam(name = "member_id") int memberId) {
		System.out.println("내 팀 진행중인 챌린지 리스트");
		List<TeamChallenger> list = teamChallengeService.getTeamChallengingList(memberId);
		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
		if (list.isEmpty()) {
			result.status = false;
			result.data = "fail";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} else {
			result.status = true;
			result.data = "success";
			result.object = list;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}

		return response;
	}

	@PostMapping("/team_challenge_enroll")
	@ApiOperation(value = "팀 챌런지 생성하기")
	public ResponseEntity createTeamChallenge(@Valid @RequestBody TeamChallengeDto teamChallengeDto) {

		boolean ret = teamChallengeService.addTeamChallenge(teamChallengeDto);

		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
		if(ret) {
			result.status = true;
			result.message = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);

		}else {
			result.status =false;
			result.message = "fail";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PostMapping("/team_challenge_participate")
	@ApiOperation(value = "팀 챌런지 참여하기")
	public ResponseEntity participateTeamChallenge(@Valid @RequestBody TeamChallengerDto teamChallengerDto) {

		boolean ret = teamChallengeService.participateTeamChallenge(teamChallengerDto);

		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
		if(ret) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);

		}else {
			result.status =false;
			result.data = "fail";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PostMapping("/team_challenge_giveup")
	@ApiOperation("챌린지 포기하기")
	public ResponseEntity giveupTeamChallenge(@Valid @RequestBody TeamChallengerDto teamChallengerDto) {

		boolean ret = teamChallengeService.giveupTeamChallenge(teamChallengerDto);

		BasicResponse result = new BasicResponse();
		ResponseEntity response = null;
		if(ret) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			result.status =false;
			result.data = "fail";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

//	@PutMapping("/updatechallenge")
//	@ApiOperation(value = "팀 챌린지 수정하기")
//	public ResponseEntity updateTeamChallenge(@Valid @RequestBody TeamController updation) {
//		
//		List<TeamChallenge> list = teamChallengeService.updateTeamChallenge(updation);
//		BasicResponse result = new BasicResponse();
//		ResponseEntity response = null;
//        if(list == null) {
//        	result.status =false;
//        	result.data = "fail";
//        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//        }else {
//        	 result.status = true;
//             result.data = "success";
//             result.object = list;
//             response = new ResponseEntity<>(result, HttpStatus.OK);
//        }
//	}
//	

}
