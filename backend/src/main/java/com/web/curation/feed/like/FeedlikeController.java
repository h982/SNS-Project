package com.web.curation.feed.like;

import com.web.curation.model.BasicResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@AllArgsConstructor
public class FeedlikeController {
    private FeedlikeService feedlikeService;

    @PostMapping("/feedlike")
    ResponseEntity<?> createFeedlike(@RequestBody @Valid FeedlikeDto feedlikeDto){

        int feedlikeId = feedlikeService.likeFeed(feedlikeDto);
        feedlikeDto.setFeedlikeId(feedlikeId);

        final BasicResponse result = new BasicResponse();
        if(feedlikeId == -1){
            result.status = false;
            result.data = "fail";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        result.status = true;
        result.data = "success";
        result.object = feedlikeDto;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/feedlike/{feed_id}")
    ResponseEntity<?> getFeedlikes(@PathVariable(value = "feed_id")int feedId){

        Optional<List<FeedlikeDto>> feedlikeList = feedlikeService.getfeedlikeList(feedId);

        final BasicResponse result = new BasicResponse();
        if(!feedlikeList.isPresent()){
            result.status = false;
            result.data = "fail";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        result.status = true;
        result.data = "success";
        result.object = feedlikeList;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/feedlike")
    ResponseEntity<?> updateFeedlike(@RequestBody @Valid FeedlikeDto feedlikeDto){

        boolean isOk = feedlikeService.updateFeedlike(feedlikeDto);

        final BasicResponse result = new BasicResponse();
        if(!isOk){
            result.status = false;
            result.data = "fail";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        result.status = true;
        result.data = "success";
        result.object = feedlikeDto;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/feedlike")
    ResponseEntity<?> deleteFeedlike(@RequestBody @Valid FeedlikeDto feedlikeDto){

        boolean isOk = feedlikeService.deleteFeedlike(feedlikeDto);

        final BasicResponse result = new BasicResponse();
        if(!isOk){
            result.status = false;
            result.data = "fail";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        result.status = true;
        result.data = "success";
        result.object = feedlikeDto;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
