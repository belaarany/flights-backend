package dev.flights.controller.flight;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import dev.flights.entity.flight.FlightRepository;
import dev.flights.entity.flight.FlightService;
import dev.flights.entity.flight.Flight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Faker faker = new Faker();

    // @Disabled
    // @Test
    // void canListFlights() throws Exception {
    //     // given
    //     // Flight f = flightRepository.save(Flight.builder().build());
    //     // List<Flight> fs = flightRepository.findAll();
    //     // when(flightRepository.findAll()).thenReturn(Arrays.asList(f));
    //     // flightService.createFlight(Flight.builder().build());

    //     // Map<String, String> body = new HashMap<>();
    //     // body.put("name", faker.company().name());
    //     // body.put("logo_url", faker.company().logo());

    //     // when
    //     ResultActions resultAction = mockMvc.perform(
    //             get("/api/flights")
    //                     .accept(MediaType.APPLICATION_JSON));

    //     // then
    //     resultAction.andExpect(status().isOk());

    //     String result = resultAction.andReturn().getResponse().getContentAsString();
    //     // DocumentContext jp = JsonPath.parse(result);

    //     // assertThat(jp.read("$.[0].kind").toString()).isEqualTo("flight");

    //     return;
    // }
}
