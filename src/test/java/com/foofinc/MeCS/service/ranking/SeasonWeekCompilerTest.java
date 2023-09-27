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

        //TODO Organize Below into separate tests

        //From ESPN
        int realMichTotalOff = 3345 + 3078;
        int realMichTotalDef = 1371 + 2719;

        //Mich post season stats [0]= Off, [1]= Def
        int[] michStats = getTotalYards(weeklyTeams.get(weeklyTeams.size() - 1), 130);

        assertEquals(realMichTotalOff, michStats[0], 1);
        assertEquals(realMichTotalDef, michStats[1], 1);


        //From ESPN
        int realGeorgiaTotalOff = 7517;
        int realGeorgiaTotalDef = 4452;

        int[] georgiaStats = getTotalYards(weeklyTeams.get(weeklyTeams.size() - 1), 61);

        assertEquals(realGeorgiaTotalOff, georgiaStats[0], 1);
        assertEquals(realGeorgiaTotalDef, georgiaStats[1], 1);
    }

    private static int[] getTotalYards(List<TeamStats> teamStatsWeek, int teamId) {

        TeamStats teamStats = teamStatsWeek
                .stream()
                .filter(team -> team.getSchool().getId() == teamId)
                .findFirst()
                .orElseThrow();

        return new int[]{teamStats.getTotalOffense(), teamStats.getTotalDefense()};
    }
}