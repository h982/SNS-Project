package com.web.curation.sport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SportDao extends JpaRepository<SportDto,Integer> {
    SportDto findById(int sportId);
}
