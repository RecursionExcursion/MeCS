package com.foofinc.MeCS.repository.cfb_api;

import com.foofinc.MeCS.util.AppProps;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

class DataRetriever {

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

    static String getPostSeasonJsonString(int year) {
        String postSeasonUrl = UrlBuilder.getGameStatsUrl(year, 1, UrlConstants.postSeason);
        return getJSONFromAPI(postSeasonUrl);
    }

    private static String getJSONFromAPI(String url) {
        HttpResponse<String> response = getResponse(url, AppProps.getBearerToken());
        return response.body();
    }

    private static HttpResponse<String> getResponse(String url, String bearer) {
        try {
            return APIAccessor.accessAPI(url, bearer);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
