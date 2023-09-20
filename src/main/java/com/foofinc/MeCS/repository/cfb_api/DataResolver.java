package com.foofinc.MeCS.repository.cfb_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.SchoolDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DataResolver {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static List<SchoolDTO> getTeams(int year) {
        String teamsJsonString = DataRetriever.getSchoolsJsonString(year);
        return mapSchools(teamsJsonString);
    }

    private static List<SchoolDTO> mapSchools(String teamsJsonString) {
        try {
            return MAPPER.readValue(teamsJsonString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    public static List<List<GameDTO>> getGames(int year) {

//  List<String> regularSeasonWeeksJsonStrings = DataRetriever.getRegularSeasonWeeksJsonStrings(year);
//        String postSeasonJsonString = DataRetriever.getPostSeasonJsonString(year);
//        List<List<GameDTO>> seasonGames = new ArrayList<>();
//
//        regularSeasonWeeksJsonStrings.forEach(json -> {
//            List<GameDTO> week = mapWeek(json);
//            seasonGames.add(week);
//        });
//
//        seasonGames.add(mapWeek(postSeasonJsonString));
//
//        return seasonGames;
//    }

    public static List<List<GameDTO>> getGames(int year) {

        List<String> regularSeasonWeeksJsonStrings = DataRetriever.getRegularSeasonWeeksJsonStrings(year);
        String postSeasonJsonString = DataRetriever.getPostSeasonJsonString(year);

        List<List<GameDTO>> seasonGames = regularSeasonWeeksJsonStrings.stream()
                                                                       .map(DataResolver::mapWeek)
                                                                       .collect(Collectors.toList());

        seasonGames.add(mapWeek(postSeasonJsonString));

        return seasonGames;
    }

    private static List<GameDTO> mapWeek(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
