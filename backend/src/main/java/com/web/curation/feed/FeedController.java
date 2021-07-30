package com.web.curation.feed;

import com.web.curation.amazonS3.S3Uploader;
import com.web.curation.files.PhotoDto;
import com.web.curation.files.PhotoService;
import com.web.curation.model.BasicResponse;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.join.JoinTeam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController("/feed")
@AllArgsConstructor
public class FeedController {
	private FeedService feedService;
    private S3Uploader s3Uploader;
    private PhotoService photoService;

    @PostMapping("/feed")
    @ApiOperation(value = "피드 생성")
    public ResponseEntity<?> addFeed(@RequestParam(value = "teamChallengeId", required = false) int teamChallengeId,
                                     @RequestParam(value = "joinTeamId") int joinTeamId,
                                     @RequestParam(value = "teamName") String teamName,
                                     @RequestParam(value = "contents") String contents,
                                     @RequestParam(value = "writer") String writer,
                                     @RequestParam(value = "images") List<MultipartFile> multipartFileList) throws IOException {
        //Feed 객체 생성하기
        Feed feed = Feed.builder()
                .joinTeam(new JoinTeam(joinTeamId))
                .teamName(teamName)
                .contents(contents)
                .writer(writer)
                .build();
        if(teamChallengeId != -1){
            feed.setTeamchallenge(new TeamChallenge(teamChallengeId));
        }
        //피드삽입
        Feed resultFeed = feedService.registerFeed(feed);

        //사진 등록
        List<PhotoDto> photoList = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFileList){
            PhotoDto uploadPhoto = s3Uploader.upload(multipartFile,"static");
            uploadPhoto.setFeed(resultFeed);
            photoList.add(photoService.addPhoto(uploadPhoto));
        }


        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = resultFeed;
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

    @GetMapping("/feed")
    @ApiOperation(value = "피드 조회")
    public ResponseEntity<?> getFeedList(){
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
	
}
