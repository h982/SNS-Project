package com.web.curation.board;

public class BoardAndDtoAdapter {
    public static BoardDto entityToDto(Board board){
        return BoardDto.builder()
                .boardId(board.getBoardId())
                .contents(board.getContents())
                .title(board.getTitle())
                .writer(board.getWriter())
                .writeDate(board.getWriteDate())
                .build();
    }

    public static Board dtoToEntity(BoardDto boardDto){
        Board board = Board.builder()
                .boardId(boardDto.getBoardId())
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .writer(boardDto.getWriter())
                .build();
        return board;
    }
}
