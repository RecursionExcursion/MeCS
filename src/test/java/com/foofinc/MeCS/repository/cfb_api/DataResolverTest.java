package com.foofinc.MeCS.repository.cfb_api;

import com.foofinc.MeCS.repository.models.GameDTO;
import com.foofinc.MeCS.repository.models.SchoolDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DataResolverTest {

    @Test
    void givenYear_whenGetTeams_thenReturnListOfTeams() {
        List<SchoolDTO> teams = DataResolver.getTeams(2022);

        assertEquals(131, teams.size());
    }

    @Test
    void givenYear_whenGetGames_thenReturnNonEmptyList() {
        List<List<GameDTO>> weeks = DataResolver.getGames(2022);

        assertEquals(16,weeks.size());

        weeks.forEach(w -> assertFalse(w.isEmpty()));
    }
}