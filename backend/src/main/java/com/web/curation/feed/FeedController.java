package com.web.curation.feed;

import com.web.curation.model.BasicResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController()
@AllArgsConstructor
public class FeedController {
    private FeedService feedService;

    @PostMapping("/feed")
    @ApiOperation(value = "피드 생성")
    public ResponseEntity<?> addFeed(FeedDto feedDto) throws IOException {

        feedService.registerFeed(feedDto);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/feed")
    @ApiOperation(value = "피드 조회")
    public ResponseEntity<?> getFeedList() {

        List<Feed> feedList = feedService.getFeedList();
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedList;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/feed")
    @ApiOperation(value = "피드 수정")
    public ResponseEntity<?> updateFeed(FeedDto feedDto) throws IOException {

        feedService.updateFeed(feedDto);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedDto;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/feed")
    @ApiOperation(value = "피드 삭제")
    public ResponseEntity<?> deleteFeed(FeedDto feedDto) throws IOException {

        feedService.deleteFeed(feedDto);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedDto;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/feed/team/{team_id}")
    @ApiOperation(value = "팀별 피드 받기")
    public ResponseEntity<?> getTeamFeeds(@PathVariable(name = "team_id") int teamId) {

        List<Feed> feedList = feedService.getTeamFeeds(teamId);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedList;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/feed/member/{member_id}")
    @ApiOperation(value = "멤버별 피드 받기")
    public ResponseEntity<?> getMemberFeeds(@PathVariable(name = "member_id") int memberId) {

        List<Feed> feedList = feedService.getMemberFeeds(memberId);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedList;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}