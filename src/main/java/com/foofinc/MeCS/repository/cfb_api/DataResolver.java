package com.foofinc.MeCS.repository.cfb_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.RankingsDTO;
import com.foofinc.MeCS.repository.models.SchoolDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class DataResolver {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static List<SchoolDTO> getTeams(int year) {
        String teamsJsonString = DataRetriever.getSchoolsJsonString(year);
        return mapJsonToObject(teamsJsonString, new TypeReference<>() {});
    }

    static List<List<GameDTO>> getGames(int year) {

        List<String> allWeeks = new ArrayList<>(DataRetriever.getRegularSeasonWeeksJsonStrings(year));
        allWeeks.add(DataRetriever.getPostSeasonJsonString(year));

        return allWeeks.stream()
                       .map(json -> mapJsonToObject(json, new TypeReference<List<GameDTO>>() {}))
                       .collect(Collectors.toList());
    }

    static List<RankingsDTO> getPreseasonRankings(int year) {
        String preSeasonRankingsJsonString = DataRetriever.getPreSeasonRankings(year);
        return mapJsonToObject(preSeasonRankingsJsonString, new TypeReference<>() {});
    }

    private static <T> T mapJsonToObject(String jsonString, TypeReference<T> typeRef) {
        try {
            return MAPPER.readValue(jsonString, typeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
