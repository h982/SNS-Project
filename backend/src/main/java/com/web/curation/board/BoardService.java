package com.web.curation.board;

import com.web.curation.error.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardDao boardDao;

    public List<Board> getBoardlist() {
        return boardDao.findAll();
    }

    public BoardDto getBoardOne(int boardId) {
        Optional<Board> chkBoard = Optional.ofNullable(boardDao.findById(boardId).orElseThrow(NotFoundDataException::new));
        return BoardAndDtoAdapter.entityToDto(chkBoard.get());
    }

    public boolean getBoardExist(int boardId) {
        return boardDao.existsById(boardId);
    }

    public BoardDto writeBoard(BoardDto boardDto) {
        return BoardAndDtoAdapter.entityToDto(boardDao.save(boardDto.toEntity()));
    }

    public boolean deleteBoard(int boardId) {
        Optional<Board> chkBoard = Optional.ofNullable(boardDao.findById(boardId).orElseThrow(NotFoundDataException::new));
        boardDao.deleteById(boardId);
        return true;
    }

    public BoardDto modifyBoard(BoardDto boardDto) {
        Optional<Board> chkBoard = Optional.ofNullable(boardDao.findById(boardDto.getBoardId()).orElseThrow(NotFoundDataException::new));
        return BoardAndDtoAdapter.entityToDto(boardDao.save(BoardAndDtoAdapter.dtoToEntity(boardDto)));
    }
}
