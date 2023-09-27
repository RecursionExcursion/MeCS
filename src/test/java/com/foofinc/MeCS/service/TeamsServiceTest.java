package com.foofinc.MeCS.service;

import com.foofinc.MeCS.repository.TeamsRepository;
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
        service.getTeams();

    }
}