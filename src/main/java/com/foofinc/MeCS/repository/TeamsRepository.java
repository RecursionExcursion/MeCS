package com.foofinc.MeCS.repository;

import com.foofinc.MeCS.repository.cfb_api.SeasonRecord;
import com.foofinc.MeCS.repository.cfb_api.SeasonBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class TeamsRepository {
    public SeasonRecord getSeason(int year) {
        return SeasonBuilder.buildSeason(year);
    }
}
