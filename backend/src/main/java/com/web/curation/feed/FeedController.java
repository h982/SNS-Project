package com.web.curation.feed;

import com.web.curation.amazonS3.S3Uploader;
import com.web.curation.files.PhotoDto;
import com.web.curation.files.PhotoService;
import com.web.curation.model.BasicResponse;
import com.web.curation.team.challenge.TeamChallenge;
import com.web.curation.team.join.JoinTeam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController("/feed")
@AllArgsConstructor
public class FeedController {
	private FeedService feedService;
    private S3Uploader s3Uploader;
    private PhotoService photoService;
	
//	@Autowired
//	private MemberDao memberDao;
//	
//	@Autowired
//	private TeamDao teamDao;

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

        //사진 등록
        List<PhotoDto> photoList = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFileList){
            PhotoDto uploadPhoto = s3Uploader.upload(multipartFile,"static");
            photoList.add(photoService.addPhoto(uploadPhoto));
        }

        Feed resultFeed = feedService.registerFeed(feed);

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
        List<Feed> feedList = feedService.getFeedList();

        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedList;
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }
	
//    @PostMapping("/createfeed")
//    @ApiOperation(value = "피드 생성하기")
//    public Object createFeed(@Valid @RequestBody FeedCreationRequest feedCreation) {
//        // 팀 챌런지 id 없을 수도 있음
//    	// member_id, team_id 체크
//    	// team_challenge_id 체크
//    	final BasicResponse result = new BasicResponse();
//    	/*
//    	int memberId = feedCreation.getMemberId();
//    	int teamId = feedCreation.getTeamId();
//    	int teamChallengeId = feedCreation.getTeamChallengeId();
//
//    	Member member = memberService.findMemberByMemberId(int memberId);
//    	Team team = teamService.findTeamByTeamId(int teamId);
//
//    	ResponseEntity response = null;
//    	if(member == null || team == null) {
//    		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
//    	}
//    	*/
//
//        Feed feed = new Feed();
////        feed.setMemberId(memberId);
////        feed.setTeamId(teamId);
////        if(team_challenge_id == -1) {					// team_challenge 선택 안할시 -1값 보내주기
////        	feed.setTeamChallengeId(teamChallengeId);
////        }
//        feed.setContents(feedCreation.getContents());
//        feed.setTeamName(feedCreation.getTeamName());
//        feed.setWriter(feedCreation.getWriter());
//        feed.setFilepath(feedCreation.getFilepath());	// FilePath 만들어야함
//
//        Feed ret = feedService.save(feed);
//
//        ResponseEntity response = null;
//        if(ret == null) {
//        	result.status =false;
//        	result.data = "fail";
//        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//        }else {
//        	 result.status = true;
//             result.data = "success";
//             response = new ResponseEntity<>(result, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
    
    /*
    @PostMapping("/getfeed")
    public Object getFeed(@RequestBody int memberId) {
    	List<Feed> feed = feedService.findFeedByMemberId(memeberId);
    	
    	final BasicResponse result = new BasicResponse();
    	ResponseEntity response = null;
    	if(feed == null) {
        	result.status =false;
        	result.data = "fail";
        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }else {
        	 result.status = true;
             result.data = "success";
             response = new ResponseEntity<>(result, HttpStatus.OK);
        }
    	
    	return response;
    }
    */
	
}
