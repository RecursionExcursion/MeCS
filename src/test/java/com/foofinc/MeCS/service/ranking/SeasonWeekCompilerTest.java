package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.repository.TeamsRepository;
import com.foofinc.MeCS.repository.cfb_api.SeasonRecord;
import com.foofinc.MeCS.service.season.SeasonData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {TeamsRepository.class})
@WebMvcTest
class SeasonWeekCompilerTest {

    @Autowired
    private TeamsRepository repository;

    @Test
    void compileSeason() {
        SeasonRecord season = repository.getSeason(2022);
        SeasonData seasonData = new SeasonData(season);
        RankedSeason rankedSeason = SeasonWeekCompiler.compileSeason(seasonData);

        List<List<TeamStats>> weeklyTeams = rankedSeason.getWeeklyStats();

        int michTotalOff = 3345 + 3078;
        int michTotalDef = 1371 + 2719;

        TeamStats michFinalWeekStats = weeklyTeams.get(weeklyTeams.size() - 1)
                                                  .stream()
                                                  .filter(team -> team.getSchool().getId() == 130)
                                                  .findFirst()
                                                  .orElseThrow();

        int totalOffense = michFinalWeekStats.getTotalOffense();
        int totalDefense = michFinalWeekStats.getTotalDefense();

        assertEquals(michTotalOff, totalOffense, 1);
        assertEquals(michTotalDef, totalDefense, 1);
    }
}