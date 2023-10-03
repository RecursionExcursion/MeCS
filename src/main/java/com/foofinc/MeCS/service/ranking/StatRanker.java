package com.foofinc.MeCS.service.ranking;

import com.foofinc.MeCS.service.stats.SeasonStats;
import com.foofinc.MeCS.service.stats.TeamStats;

import java.util.*;

public class StatRanker {

    public static RankedSeasonStats calculateStatRanksForEachWeek(SeasonStats seasonStats) {

        RankedSeasonStats rankedSeasonStats = new RankedSeasonStats();

        List<List<TeamStats>> weeklyStats = seasonStats.getWeeklyStats();

        for (List<TeamStats> weeklyStat : weeklyStats) {
            rankedSeasonStats.addWeekMap(sortAllStats(weeklyStat));
        }

        return rankedSeasonStats;
    }

//    public static List<Map<RankingCategory, List<TeamStats>>> calculateStatRanksForEachWeek(SeasonStats seasonStats) {
//
//        List<Map<RankingCategory, List<TeamStats>>> sortedSeasonStatsMap = new ArrayList<>();
//
//        List<List<TeamStats>> weeklyStats = seasonStats.getWeeklyStats();
//
//        for (List<TeamStats> weeklyStat : weeklyStats) {
//            sortedSeasonStatsMap.add(sortAllStats(weeklyStat));
//        }
//
//        return sortedSeasonStatsMap;
//    }

    private static Map<RankingCategory, List<TeamStats>> sortAllStats(List<TeamStats> teams) {

        Map<RankingCategory, List<TeamStats>> statMap = new HashMap<>();

        statMap.put(RankingCategory.WINS, sortTeamsAgainstStat(teams, RankingCategory.WINS));
        statMap.put(RankingCategory.LOSSES, sortTeamsAgainstStat(teams, RankingCategory.LOSSES));

        statMap.put(RankingCategory.OFFENSE_PG, sortTeamsAgainstStat(teams, RankingCategory.OFFENSE_PG));
        statMap.put(RankingCategory.DEFENSE_PG, sortTeamsAgainstStat(teams, RankingCategory.DEFENSE_PG));

        statMap.put(RankingCategory.POINTS_FOR_PG, sortTeamsAgainstStat(teams, RankingCategory.POINTS_FOR_PG));
        statMap.put(RankingCategory.POINTS_ALLOWED_PG, sortTeamsAgainstStat(teams, RankingCategory.POINTS_ALLOWED_PG));

        return statMap;
    }

    private static List<TeamStats> sortTeamsAgainstStat(List<TeamStats> teams, RankingCategory rankingCategory) {
        List<TeamStats> sortedTeams = new ArrayList<>(teams);
        switch (rankingCategory) {
            //These need to be reversed
            case WINS -> sortedTeams.sort(Comparator.comparingInt(TeamStats::getWins).reversed());
            case POINTS_FOR_PG -> sortedTeams.sort(Comparator.comparingDouble(TeamStats::getPointsForPerGame).reversed());
            case OFFENSE_PG -> sortedTeams.sort(Comparator.comparingDouble(TeamStats::getOffensePerGame).reversed());
            //These do not
            case LOSSES -> sortedTeams.sort(Comparator.comparingInt(TeamStats::getLosses));
            case DEFENSE_PG -> sortedTeams.sort(Comparator.comparingDouble(TeamStats::getDefensePerGame));
            case POINTS_ALLOWED_PG -> sortedTeams.sort(Comparator.comparingDouble(TeamStats::getPointsAllowedPerGame));

            default -> throw new NullPointerException();
        }
        return sortedTeams;
    }
}
