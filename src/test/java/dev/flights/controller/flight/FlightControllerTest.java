package dev.flights.controller.flight;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.flights.entity.flight.FlightService;

public class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Faker faker = new Faker();

    @Test
    @Disabled
    void canListFlights() throws Exception {
        // // given
        // Map<String, String> body = new HashMap<>();
        // body.put("name", faker.company().name());
        // body.put("logo_url", faker.company().logo());

        // // when
        // ResultActions resultAction = mockMvc.perform(
        //         post("/api/airlines")
        //                 .contentType(MediaType.APPLICATION_JSON)
        //                 .content(objectMapper.writeValueAsString(body))
        //                 .accept(MediaType.APPLICATION_JSON));
        // // MvcResult result = resultAction.andReturn();

        // // then
        // resultAction
        //         .andExpect(status().isOk());
    }
}
