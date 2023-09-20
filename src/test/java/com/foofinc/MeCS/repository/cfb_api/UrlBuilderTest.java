package com.foofinc.MeCS.repository.cfb_api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlBuilderTest {



    @Test
    void givenYear_whenGetSchoolsUrl_thenReturnUrlString() {
        String expected2022 = "https://api.collegefootballdata.com/teams/fbs?year=2022";
        String expected2000 = "https://api.collegefootballdata.com/teams/fbs?year=2000";

        assertEquals(expected2022, UrlBuilder.getSchoolsUrl(2022));

        assertEquals(expected2000, UrlBuilder.getSchoolsUrl(2000));
    }

//    @Test
//    void givenYear_whenGetRegularSeasonGamesUrl_thenReturnUrlString() {
//        String expected2022 = "https://api.collegefootballdata.com/games?year=2022&seasonType=regular&division=fbs";
//        assertEquals(expected2022, UrlCreator.getRegularSeasonGamesUrl(2022));
//    }
//
//    @Test
//    void givenYear_whenGetPostSeasonGamesUrl_thenReturnUrlString() {
//        String expected2022 = "https://api.collegefootballdata.com/games?year=2022&seasonType=postseason&division=fbs";
//        assertEquals(expected2022, UrlCreator.getPostSeasonGamesUrl(2022));
//    }

    @Test
    void givenYearWeekSeason_whenGetGameStatsUrl_thenReturnUrlString() {
        String expected2022Week5RegularSeason =
                "https://api.collegefootballdata.com/games/teams?year=2022&week=5&seasonType=regular&classification" +
                        "=fbs";

        String expected2019Week1PostSeason =
                "https://api.collegefootballdata.com/games/teams?year=2019&week=1&seasonType=postseason" +
                        "&classification=fbs";

        assertEquals(expected2022Week5RegularSeason,
                     UrlBuilder.getGameStatsUrl(2022, 5, UrlConstants.regularSeason));

        assertEquals(expected2019Week1PostSeason,
                     UrlBuilder.getGameStatsUrl(2019, 1, UrlConstants.postSeason));
    }
}