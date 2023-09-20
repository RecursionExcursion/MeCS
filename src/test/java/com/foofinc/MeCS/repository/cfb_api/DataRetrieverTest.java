package com.foofinc.MeCS.repository.cfb_api;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DataRetrieverTest {

    @Test
    void givenYear_whenRetrieveSchoolsJson_thenReturnJsonAsString() {

        String data = DataRetriever.getSchoolsJsonString(2023);

        assertNotEquals("[]", data);
    }

    @Test
    void givenYear_whenGetRegularSeasonWeeks_thenReturnListOfJsonAsString() {
        List<String> regularSeasonWeeks = DataRetriever.getRegularSeasonWeeksJsonStrings(2022);

        assertEquals(15, regularSeasonWeeks.size());
    }

    @Test
    void getPostSeasonJson() {
        String postSeasonJson = DataRetriever.getPostSeasonJsonString(2022);
        assertNotEquals("[]", postSeasonJson);
    }
}