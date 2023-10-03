package com.foofinc.MeCS.service.stats;

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
class SeasonStatCompilerTest {

    @Autowired
    private TeamsRepository repository;

    @Test
    void compileSeason() {
        SeasonRecord season = repository.getSeason(2022);
        SeasonData seasonData = new SeasonData(season);
        SeasonStats seasonStats = SeasonStatCompiler.compileSeason(seasonData);
        List<List<TeamStats>> weeklyTeams = seasonStats.getWeeklyStats();

        //From ESPN
        Stats realMichStats = new Stats(3345 + 3078, 1371 + 2719,
                                        566, 225,
                                        13, 1, 14);

        Stats realGeorgiaStats = new Stats(7517, 4452,
                                           616, 214,
                                           15, 0, 15);

        //Post season stats
        Stats michStats = getStats(weeklyTeams.get(weeklyTeams.size() - 1), 130);
        Stats georgiaStats = getStats(weeklyTeams.get(weeklyTeams.size() - 1), 61);

        teamsAreEqual(realMichStats, michStats);
        teamsAreEqual(realGeorgiaStats, georgiaStats);
    }

    private static Stats getStats(List<TeamStats> teamStatsWeek, int teamId) {
        return new Stats(findTeam(teamStatsWeek, teamId));
    }

    private static TeamStats findTeam(List<TeamStats> teamStatsWeek, int teamId) {
        return teamStatsWeek
                .stream()
                .filter(team -> team.getSchool().getId() == teamId)
                .findFirst()
                .orElseThrow();
    }

    private record Stats(int totalOffense, int totalDefense,
                         int pointsFor, int pointsAllowed,
                         int wins, int losses,
                         int gamesPlayed) {

        private Stats(TeamStats teamStats) {
            this(teamStats.getTotalOffense(), teamStats.getTotalDefense(),
                 teamStats.getPointsFor(), teamStats.getPointsAllowed(),
                 teamStats.getWins(), teamStats.getLosses(),
                 teamStats.getNumberOfGamesPlayed()
            );
        }
    }

    private void teamsAreEqual(Stats team1, Stats team2) {
        assertEquals(team1.totalOffense, team2.totalOffense,1);
        assertEquals(team1.totalDefense, team2.totalDefense,1);
        assertEquals(team1.wins, team2.wins);
        assertEquals(team1.losses, team2.losses);
        assertEquals(team1.gamesPlayed, team2.gamesPlayed);
    }
}