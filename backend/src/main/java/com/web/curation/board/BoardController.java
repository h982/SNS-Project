package com.web.curation.board;

import com.web.curation.model.BasicResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiResponses(value = {@ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)})

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    @ApiOperation(value = "게시글 조회")
    public ResponseEntity<?> getBoardlist() {
        List<Board> boardList = boardService.getBoardlist();
        ResponseEntity response = null;
        if (boardList.size() > 0) {
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            result.object = boardList;
            response = new ResponseEntity(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @GetMapping("/board/{boardId}")
    @ApiOperation(value = "선택 게시글 보기")
    public ResponseEntity<?> getBoardOne(@PathVariable int boardId) {
        Board board = boardService.getBoardOne(boardId);
        ResponseEntity response = null;
        if (board != null) {
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            result.object = board;
            response = new ResponseEntity(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @PostMapping("/board")
    @ApiOperation(value = "게시글 작성")
    public ResponseEntity<?> writeBoard(@Valid @RequestBody BoardDto boardDto) {
        Board writeBoard = boardService.writeBoard(boardDto);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = writeBoard;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/board/{boardId}")
    @ApiOperation(value = "게시글 삭제")
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/board")
    @ApiOperation(value = "게시글 수정")
    public ResponseEntity<?> modifyBoard(@RequestBody BoardDto boardDto) {
        if (boardService.getBoardExist(boardDto.getBoardId())) {
            Board modifyBoard = boardService.modifyBoard(boardDto);
            return new ResponseEntity<>(modifyBoard, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("FAIL", HttpStatus.NOT_FOUND);
        }
    }
}
