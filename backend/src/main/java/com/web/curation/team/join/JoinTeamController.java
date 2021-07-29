package com.web.curation.team.join;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
@RestController
public class JoinTeamController {
	@Autowired
	JoinTeamService joinTeamService;
	
    @PostMapping("/jointeam")
    @ApiOperation(value = "팀 가입")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "팀 가입 성공")})
    public Object getMyTeamList(@RequestBody JoinTeam joinTeam) {
    	if(!joinTeamService.getSameContent(joinTeam).isPresent()) {
    		joinTeamService.createJoin(joinTeam);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	}
    		
    	return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
