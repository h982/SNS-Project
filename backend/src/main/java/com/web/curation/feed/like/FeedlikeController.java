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
        feedlikeService.likeFeed(feedlikeDto);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/feedlike/feed/{feed_id}")
    ResponseEntity<?> getFeedlikes(@PathVariable(value = "feed_id")int feedId){
        List<FeedlikeDto> feedlikeList = feedlikeService.getfeedlikeList(feedId);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedlikeList;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/feedlike/member/{member_id}")
    ResponseEntity<?> getMyFeedlikes(@PathVariable(value = "member_id")int memberId){
        List<FeedlikeDto> feedlikeList = feedlikeService.getMyFeedlikes(memberId);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedlikeList;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/feedlike")
    ResponseEntity<?> updateFeedlike(@RequestBody @Valid FeedlikeDto feedlikeDto){
        feedlikeService.updateFeedlike(feedlikeDto);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/feedlike")
    ResponseEntity<?> deleteFeedlike(@RequestBody @Valid FeedlikeDto feedlikeDto){
        feedlikeService.deleteFeedlike(feedlikeDto);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
