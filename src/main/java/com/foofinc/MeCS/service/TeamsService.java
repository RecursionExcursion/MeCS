package com.foofinc.MeCS.service;

import com.foofinc.MeCS.repository.TeamsRepository;
import com.foofinc.MeCS.repository.cfb_api.Season;
import com.foofinc.MeCS.service.season.SeasonCompiler;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {

    private final TeamsRepository repository;

    public TeamsService(TeamsRepository repository) {
        this.repository = repository;
    }

    public String getTeams(){
        return "Teams";
    }

    private void getSeason(int year){
        Season season = repository.getSeason(year);
        SeasonCompiler.compileSeason(season);
        //TODO Stopped here
    }
}
