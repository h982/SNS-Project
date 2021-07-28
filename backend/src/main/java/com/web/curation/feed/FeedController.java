package com.web.curation.feed;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;

import io.swagger.annotations.ApiOperation;

@RestController("/feed")
public class FeedController {
	@Autowired
	private FeedService feedService;
	
//	@Autowired
//	private MemberDao memberDao;
//	
//	@Autowired
//	private TeamDao teamDao;
	
    @PostMapping("/createfeed")
    @ApiOperation(value = "피드 생성하기")
    public Object createFeed(@Valid @RequestBody FeedCreationRequest feedCreation) {
        // 팀 챌런지 id 없을 수도 있음
    	// member_id, team_id 체크
    	// team_challenge_id 체크
    	final BasicResponse result = new BasicResponse();
    	/*
    	int memberId = feedCreation.getMemberId();
    	int teamId = feedCreation.getTeamId();
    	int teamChallengeId = feedCreation.getTeamChallengeId();
    	
    	Member member = memberService.findMemberByMemberId(int memberId);
    	Team team = teamService.findTeamByTeamId(int teamId);
    	
    	ResponseEntity response = null;
    	if(member == null || team == null) {
    		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    	}
    	*/	
        
        Feed feed = new Feed();
//        feed.setMemberId(memberId);
//        feed.setTeamId(teamId);
//        if(team_challenge_id == -1) {					// team_challenge 선택 안할시 -1값 보내주기
//        	feed.setTeamChallengeId(teamChallengeId);
//        }
        feed.setContents(feedCreation.getContents());
        feed.setTeamName(feedCreation.getTeamName());
        feed.setWriter(feedCreation.getWriter());
        feed.setFilepath(feedCreation.getFilepath());	// FilePath 만들어야함
        
        Feed ret = feedService.save(feed);
        
        ResponseEntity response = null;
        if(ret == null) {
        	result.status =false;
        	result.data = "fail";
        	response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }else {
        	 result.status = true;
             result.data = "success";
             response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
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
