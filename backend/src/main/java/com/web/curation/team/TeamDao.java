package com.web.curation.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamDao extends JpaRepository<Team,Long> {

    //모든 팀 조회
    List<Team> findAll();

    //팀 이름 중복검사
    boolean existsByName(String name);

	Team getOne(long teamId);

    //팀 등록
//    TeamDto save(TeamDto teamDto);
}
