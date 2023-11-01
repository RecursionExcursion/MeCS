package com.foofinc.MeCS.service;

import com.foofinc.MeCS.repository.TeamsRepository;
import com.foofinc.MeCS.service.ranking.RankingParams;
import com.foofinc.MeCS.service.ranking.SeasonRankings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {TeamsService.class, TeamsRepository.class})
@WebMvcTest
class TeamsServiceTest {

    @Autowired
    private TeamsService service;


    @Test
    void getTeams() {

    }

    @Test
    void getRankings() {
        SeasonRankings rankings = service.getRankings(getParams());

        System.out.println(rankings);
    }

    private RankingParams getParams() {
        return new RankingParams(2022,
                                 1, 1,
                                 1, 1,
                                 1, 1,
                                 1, 1,
                                 false);
    }
}