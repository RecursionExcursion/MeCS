package com.foofinc.MeCS.service;

import com.foofinc.MeCS.repository.TeamsRepository;
import com.foofinc.MeCS.repository.cfb_api.SeasonRecord;
import com.foofinc.MeCS.service.stats.SeasonStatCompiler;
import com.foofinc.MeCS.service.season.SeasonData;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {

    private final TeamsRepository repository;

    public TeamsService(TeamsRepository repository) {
        this.repository = repository;
    }

    public String getTeams() {
        getSeason(2022);
        return "Teams";
    }

    private void getSeason(int year) {
        SeasonRecord seasonRecord = repository.getSeason(year);
        SeasonData seasonData = new SeasonData(seasonRecord);
        SeasonStatCompiler.compileSeason(seasonData);
        //TODO Stopped here
    }
}
