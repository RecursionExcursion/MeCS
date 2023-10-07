package com.foofinc.MeCS.service;

import com.foofinc.MeCS.repository.TeamsRepository;
import com.foofinc.MeCS.repository.cfb_api.SeasonRecord;
import com.foofinc.MeCS.service.ranking.RankingFacade;
import com.foofinc.MeCS.service.ranking.RankingParams;
import com.foofinc.MeCS.service.ranking.SeasonRankings;
import com.foofinc.MeCS.service.stats.SeasonStatCompiler;
import com.foofinc.MeCS.service.season.SeasonData;
import com.foofinc.MeCS.service.stats.SeasonStats;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {

    private final TeamsRepository repository;

    public TeamsService(TeamsRepository repository) {
        this.repository = repository;
    }

    public String getTeams() {
        return "Teams";
    }

    public SeasonRankings getRankings(RankingParams rankingParams) {

        SeasonStats seasonStats = compileSeason(rankingParams.getYear());
        SeasonRankings seasonRankings = new RankingFacade(seasonStats, rankingParams).rankSeason();

        return seasonRankings;
    }

    private SeasonStats compileSeason(int year) {
        SeasonRecord seasonRecord = repository.getSeason(year);
        SeasonData seasonData = new SeasonData(seasonRecord);
        return SeasonStatCompiler.compileSeason(seasonData);
    }
}
