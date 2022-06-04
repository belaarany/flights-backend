package dev.flights.controller.airline;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.flights.entity.airline.AirlineService;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class AirlineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirlineService airlineService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Faker faker = new Faker();

    // @Test
    @Disabled
    void canCreateAirline() throws Exception {
        // // given
        // Map<String, String> body = new HashMap<>();
        // body.put("name", faker.company().name());
        // body.put("logo_url", faker.company().logo());

        // // when
        // ResultActions resultAction = mockMvc.perform(
        //     post("/api/airlines")
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .content(objectMapper.writeValueAsString(body))
        //         .accept(MediaType.APPLICATION_JSON));
        // // MvcResult result = resultAction.andReturn();

        // // then
        // resultAction
        //     .andExpect(status().isOk());
    }
}
