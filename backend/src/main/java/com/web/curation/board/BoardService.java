package com.web.curation.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardDao boardDao;

    public List<Board> getBoardlist() {
        return boardDao.findAll();
    }

    public Board getBoardOne(int boardId) {
        return boardDao.findById(boardId);
    }

    public boolean getBoardExist(int boardId) {
        return boardDao.existsById(boardId);
    }

    public Board writeBoard(BoardDto boardDto) {
        return boardDao.save(boardDto.toEntity());
    }

    public void deleteBoard(int boardId) {
        boardDao.deleteById(boardId);
    }

    public Board modifyBoard(BoardDto boardDto) {
        return boardDao.save(boardDto.toEntity());
    }
}
