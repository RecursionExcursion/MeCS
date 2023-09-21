package com.foofinc.MeCS.service.season;

import com.foofinc.MeCS.repository.TeamsRepository;
import com.foofinc.MeCS.service.season.model.CompleteSchool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration(classes = {TeamsRepository.class})
@WebMvcTest
class SeasonCompilerTest {

    @Autowired
    private TeamsRepository repository;

    @Test
    void compileSeason() {

        SeasonManager seasonManager = SeasonCompiler.compileSeason(repository.getSeason(2022));

        Map<Long, CompleteSchool> schoolMap = seasonManager.getSchoolMap();

        assertEquals(131,schoolMap.size());
        assertEquals(14,schoolMap.get(130L).getSchedule().size());
    }
}