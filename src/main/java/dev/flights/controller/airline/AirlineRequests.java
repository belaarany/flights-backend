package dev.flights.controller.airline;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.URL;

import lombok.Value;

public class AirlineRequests {
    @Value
    public static class CreateAirlineRequest {
        @NotNull
        @JsonProperty("name")
        private final String name;

        @NotNull
        @URL
        @JsonProperty("logo_url")
        private final String logoUrl;
    }
}
