package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.TeamStats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StatRanker {

    static List<TeamStats> sortTeamsAgainstStat(List<TeamStats> teams, RankingCategory rankingCategory) {
        List<TeamStats> sortedTeams = new ArrayList<>(teams);
        switch (rankingCategory) {
            //These need to be reversed (more wins is better than fewer wins)
            case WINS -> sortedTeams.sort(Comparator.comparingInt(TeamStats::getWins).reversed());
            case POINTS_FOR_PG ->
                    sortedTeams.sort(Comparator.comparingDouble(TeamStats::getPointsForPerGame).reversed());
            case OFFENSE_PG -> sortedTeams.sort(Comparator.comparingDouble(TeamStats::getOffensePerGame).reversed());

            //These do not (Fewer losses is better than more losses)
            case LOSSES -> sortedTeams.sort(Comparator.comparingInt(TeamStats::getLosses));
            case DEFENSE_PG -> sortedTeams.sort(Comparator.comparingDouble(TeamStats::getDefensePerGame));
            case POINTS_ALLOWED_PG -> sortedTeams.sort(Comparator.comparingDouble(TeamStats::getPointsAllowedPerGame));

            default -> throw new NullPointerException();
        }
        return sortedTeams;
    }

    static List<TeamStats> sortTeamsAgainstWeight(List<TeamStats> teams) {
        List<TeamStats> sortedTeams = new ArrayList<>(teams);
        sortedTeams.sort(Comparator.comparingDouble(TeamStats::getWeight));
        return sortedTeams;
    }
}
