package com.web.curation.feed.like;

import com.web.curation.model.BasicResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

        if(feedlikeId == -1){
            final BasicResponse result = new BasicResponse();
            result.status = false;
            result.data = "fail";

            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        feedlikeDto.setFeedlikeId(feedlikeId);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = feedlikeDto;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
