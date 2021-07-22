package com.web.curation.sport;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SportService {
    private SportDao sportDao;

    public List<SportDto> getSportList(){
        return sportDao.findAll();
    }

    public SportDto getSportOne(int sportId){
        return sportDao.findById(sportId);
    }

}
