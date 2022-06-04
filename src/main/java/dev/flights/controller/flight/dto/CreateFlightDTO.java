package dev.flights.controller.flight.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateFlightDTO {
    @NotNull
    @JsonProperty("departure_at")
    private LocalDateTime departureAt;

    @NotNull
    @JsonProperty("arrival_at")
    private LocalDateTime arrivalAt;

    @NotNull
    @JsonProperty("airplane_id")
    private UUID airplaneId;

    @NotNull
    @JsonProperty("airline_id")
    private UUID airlineId;
}
