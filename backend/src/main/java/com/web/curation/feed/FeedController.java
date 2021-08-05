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

        System.out.println(feedDto.toString());
        //피드삽입
        Feed resultFeed = feedService.registerFeed(feedDto);

        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @GetMapping("/feed")
    @ApiOperation(value = "피드 조회")
    public ResponseEntity<?> getFeedList() {
        //피드 하나조회해서 사진테이블에 피드아이디가 맞는 것들 가져와서 add
        System.out.println("피드 조회");
        List<Feed> feedList = feedService.getFeedList();
        System.out.println(feedList.size());

        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedList;
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @PutMapping("/feed")
    @ApiOperation(value = "피드 수정")
    public ResponseEntity<?> updateFeed(FeedDto feedDto) throws IOException {
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        boolean ret = feedService.updateFeed(feedDto);
        if (ret) {
            result.status = true;
            result.data = "success";
            result.object = feedDto;
        } else {
            result.status = false;
            result.data = "fail";
        }

        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @DeleteMapping("/feed")
    @ApiOperation(value = "피드 삭제")
    public ResponseEntity<?> deleteFeed(FeedDto feedDto) throws IOException {
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        boolean ret = feedService.deleteFeed(feedDto);
        if (ret) {
            result.status = true;
            result.data = "success";
            result.object = feedDto;
        } else {
            result.status = false;
            result.data = "fail";
        }

        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @GetMapping("/feed/team/{team_id}")
    @ApiOperation(value = "팀별 피드 받기")
    public ResponseEntity<?> getTeamFeeds(@PathVariable(name = "team_id")int teamId){
        final BasicResponse result = new BasicResponse();

        List<Feed> feedList = feedService.getTeamFeeds(teamId);
        result.status = true;
        result.data = "success";
        result.object = feedList;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}