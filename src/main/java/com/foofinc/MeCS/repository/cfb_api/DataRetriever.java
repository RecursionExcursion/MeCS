package com.foofinc.MeCS.repository.cfb_api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    static String getSchoolsJsonString(int year) {
        String schoolsUrl = UrlBuilder.getSchoolsUrl(year);
        return getJSONFromAPI(schoolsUrl);
    }

    static List<String> getRegularSeasonWeeksJsonStrings(int year) {

        List<String> seasonWeeksJson = new ArrayList<>();

        for (int week = 1; ; week++) {
            String gamesUrl = UrlBuilder.getGameStatsUrl(year, week, UrlConstants.regularSeason);
            String gamesJsonString = getJSONFromAPI(gamesUrl);

            if (gamesJsonString.equals("[]")) {
                break;
            }
            seasonWeeksJson.add(gamesJsonString);
        }

        return seasonWeeksJson;
    }

    public static String getPostSeasonJsonString(int year) {
        String postSeasonUrl = UrlBuilder.getGameStatsUrl(year, 1, UrlConstants.postSeason);
        return getJSONFromAPI(postSeasonUrl);
    }

    private static String getJSONFromAPI(String url) {
        String bearer = "gLQdG5n7YtiTjzu/bxxxd+rdzzrhWftHTtIH7PAGVWlAQMOAA7h2ria3ai2Dl9zc";

        HttpResponse<String> response;
        try {
            response = APIAccessor.INSTANCE.accessAPI(url, bearer);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
