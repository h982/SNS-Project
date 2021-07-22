package com.web.curation.team;

import com.web.curation.model.BasicResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    //그룹 리스트 조회
    @GetMapping("/team")
    @ApiOperation(value = "팀 목록 조회")
    public ResponseEntity<?> getTeamlist() {
        List<TeamDto> teamList = teamService.getTeamlist();
        ResponseEntity response = null;

        if (teamList.size() > 0) {
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            result.object = teamList;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/team")
    @ApiOperation(value = "팀 등록")
    public ResponseEntity<?> addTeam(@RequestBody TeamDto teamDto) {
        TeamDto resultTeamDto = teamService.registerTeam(teamDto);

        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = resultTeamDto;
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @GetMapping("/team/{name}/exists")
    @ApiOperation(value = "팀 이름 중복검사")
    public ResponseEntity<Boolean> checkNameDuplicate(@PathVariable String name) {
        return ResponseEntity.ok(teamService.checkNameDuplicate(name));
    }


}
