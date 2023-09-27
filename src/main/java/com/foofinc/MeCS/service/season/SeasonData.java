package com.foofinc.MeCS.service.season;

import com.foofinc.MeCS.repository.cfb_api.SeasonRecord;
import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.SchoolDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SeasonData {

    private final List<SchoolDTO> schools;
    private final Map<Long, SchoolDTO> schoolMap;
    private final List<List<GameDTO>> regularSeason;
    private final List<GameDTO> postSeason;

    public SeasonData(SeasonRecord seasonRecord) {
        this(seasonRecord.teams(), seasonRecord.weeks());
    }

    public SeasonData(List<SchoolDTO> schools, List<List<GameDTO>> weeks) {
        this.schools = schools;

//        schoolMap = new HashMap<>();
//        schools.forEach(s -> schoolMap.put(s.getId(), s));

        schoolMap = schools.stream()
                           .collect(Collectors.toMap(SchoolDTO::getId, Function.identity()));

        regularSeason = new ArrayList<>();

        for (int i = 0; i < weeks.size() - 1; i++) {
            List<GameDTO> week = weeks.get(i);
            regularSeason.add(week);
        }

        postSeason = weeks.get(weeks.size() - 1);
    }

    public List<SchoolDTO> getSchools() {
        return schools;
    }

    public List<List<GameDTO>> getRegularSeason() {
        return regularSeason;
    }

    public List<GameDTO> getPostSeason() {
        return postSeason;
    }

    public Map<Long, SchoolDTO> getSchoolMap() {
        return schoolMap;
    }
}
