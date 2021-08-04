package com.web.curation.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDao extends JpaRepository<Board, Integer> {
    Board findById(int boardId);
}
