package com.foofinc.MeCS.controller;

import com.foofinc.MeCS.repository.TeamsRepository;
import com.foofinc.MeCS.service.TeamsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureMockMvc
@ContextConfiguration(classes = {TeamsController.class, TeamsService.class, TeamsRepository.class})
@WebMvcTest
class TeamsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTeamsTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/teams"))
                                  .andReturn();

        String respAsString = result.getResponse().getContentAsString();

        assertEquals("Teams", respAsString);
    }
}