package com.foofinc.MeCS.repository;

import com.foofinc.MeCS.repository.cfb_api.Season;
import com.foofinc.MeCS.repository.cfb_api.SeasonBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class TeamsRepository {
    public Season getSeason(int year) {
        return SeasonBuilder.buildSeason(year);
    }
}
